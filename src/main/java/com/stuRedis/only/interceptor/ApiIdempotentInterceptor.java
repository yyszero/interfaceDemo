package com.stuRedis.only.interceptor;

import com.rabbitmq.tools.json.JSONUtil;
import com.stuRedis.only.annonation.ApiIdempotent;
import com.stuRedis.only.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 接口幂等性拦截器
 * @author:Yangying
 * @date:2020-4-21
 */
public class ApiIdempotentInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        ApiIdempotent methodAnnotation = method.getAnnotation(ApiIdempotent.class);
        if (methodAnnotation != null) {
            try{
                check(request);// 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
            }catch (Exception ex){
//                ResultVo failedResult = ResultVo.getFailedResult(101, ex.getMessage());
//                writeReturnJson(response, JSONUtil.toJsonStr(failedResult));
                throw ex;
            }

        }
        return true;
    }
    private void check(HttpServletRequest request) {
        tokenService.checkToken(request);
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
    /**
     * 返回的json值
     * @param response
     * @param json
     * @throws Exception
     */
    private void writeReturnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }


}
