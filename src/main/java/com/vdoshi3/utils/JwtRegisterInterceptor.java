package com.vdoshi3.utils;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.vdoshi3.dao.UserDao;
import com.vdoshi3.dao.UserDaoImp;
import com.vdoshi3.entity.User;
import com.vdoshi3.exception.InvalidCredentialsException;
import com.vdoshi3.exception.NotLoggedInException;
import com.vdoshi3.exception.ResourceAlreadyExistsException;
import com.vdoshi3.service.UserService;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

@Component
@PropertySource("classpath:app.properties")
public class JwtRegisterInterceptor extends HandlerInterceptorAdapter {
	// @Autowired
	// Environment env;

//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		System.out.println("Executed Post Handle after Login or Register");
//		JwtToken jwt = new JwtToken();
//		String newToken = jwt.createJWT(UUID.randomUUID().toString(), dtoken.getIssuer(), dtoken.getSubject(), 80000);
//		response.addHeader("Authorization", "Bearer " + newToken);
//		return true;
//
//	}
}