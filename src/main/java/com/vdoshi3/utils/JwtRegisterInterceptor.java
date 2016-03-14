package com.vdoshi3.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



@Component
@PropertySource("classpath:app.properties")
public class JwtRegisterInterceptor extends HandlerInterceptorAdapter {
	// @Autowired
	// Environment env;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Executed Post Handle after Login or Register");
//		JwtToken jwt = new JwtToken();
//		String newToken = jwt.createJWT(UUID.randomUUID().toString(), get user params aput here);
//		response.addHeader("Authorization", "Bearer " + newToken);
	}
}