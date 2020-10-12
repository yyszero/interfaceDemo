package com.stuRedis.only;

import com.stuRedis.only.interceptor.ApiIdempotentInterceptor;
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
//@MapperScan("com.stuRedis.only.dao")
public class OnlyApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(OnlyApplication.class, args);
	}

	/**
	 * 跨域
	 *
	 * @return
	 */
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 接口幂等性拦截器
		registry.addInterceptor(apiIdempotentInterceptor());
//		// 接口防刷限流拦截器
//		registry.addInterceptor(accessLimitInterceptor());

		super.addInterceptors(registry);
	}
	@Bean
	public ApiIdempotentInterceptor apiIdempotentInterceptor() {
		return new ApiIdempotentInterceptor();
	}

}
