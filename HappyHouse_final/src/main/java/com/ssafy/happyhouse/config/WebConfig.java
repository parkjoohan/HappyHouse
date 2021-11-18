package com.ssafy.happyhouse.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.happyhouse.interceptor.JwtInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	//로그인이 필요한 기능들
	private final List<String> patterns1 = Arrays.asList("/user", "/notice/register");
	private final List<String> patterns2 = Arrays.asList("/user", "/notice/modify");
	private final List<String> patterns3 = Arrays.asList("/user", "/notice/delete");
	
	@Autowired
	private JwtInterceptor jwtInterCeptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterCeptor).addPathPatterns("/user/**") //적용 경로
		.excludePathPatterns(Arrays.asList("/user/login", "/user/idCheck/**", "/user/join")); //적용 제외 경로
		
		registry.addInterceptor(jwtInterCeptor).addPathPatterns(patterns1);
		registry.addInterceptor(jwtInterCeptor).addPathPatterns(patterns2);
		registry.addInterceptor(jwtInterCeptor).addPathPatterns(patterns3);
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		System.out.println("CORS Setting");
//		default 설정.
//		Allow all origins.
//		Allow "simple" methods GET, HEAD and POST.
//		Allow all headers.
//		Set max age to 1800 seconds (30 minutes).
		
		registry.addMapping("/**")
			.allowedOrigins("*")
//			.allowedOrigins("http://localhost:8080", "http://localhost:8081")
			.allowedMethods("GET", "POST", "PUT", "DELETE")
//			.maxAge(6000);
			.exposedHeaders("access-token");
	}

}
