package com.vdoshi3;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
@ComponentScan
public class AppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new JwtInterceptor())
//				.addPathPatterns("/users/api/**", "/movies/api/**", "/comments/api/**", "/ratings/api/**")
//				.excludePathPatterns("/users/register/**", "/users/login/**");
//		;
//	}

//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**").allowedOrigins("http://localhost:3045").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//				.allowedHeaders(
//						"x-requested-with, Content-Type,accept,Origin,Access-Control-Request-Method, Access-Control-Request-Headers,Authorization,Access-Control-Allow-Origin")
//				.maxAge(3600);
//	}
}
