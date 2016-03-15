package com.vdoshi3;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.vdoshi3.utils.JwtInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan
public class AppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(new
		// JwtInterceptor()).excludePathPatterns("/users/register/**",
		// "/users/login/**");
		registry.addInterceptor(new JwtInterceptor())
				.addPathPatterns("/users/**", "/movies/**", "/comments/**", "/ratings/**")
				.excludePathPatterns("/users/register/**", "/users/login/**");
		;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**/*").allowedOrigins("http://localhost:3045").allowedHeaders("*").allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowCredentials(true).maxAge(3600);
	}
}
