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
public class JwtInterceptor extends HandlerInterceptorAdapter {
//	@Autowired
//	Environment env;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String newToken = "";
		String token = request.getHeader("Authorization");
		System.out.println("Got request with authorization header :" + token);
		if (token == null || !token.startsWith("Bearer ")) {
			throw new NotLoggedInException();
		}
		JwtToken jwt = new JwtToken();
		DecodedToken dtoken = jwt.parseJWT(token.substring(7));

		if (validateUser(dtoken)) {
			if (refreshToken(dtoken.getExpiration())) {
				newToken = jwt.createJWT(UUID.randomUUID().toString(), dtoken.getIssuer(), dtoken.getSubject(),
						80000);
			}
			request.setAttribute("requestor", dtoken);
			response.addHeader("Authorization", "Bearer "+newToken);
			return true;
		} else {
			throw new NotLoggedInException();
		}

	}

	private boolean validateUser(DecodedToken dtoken) {
		System.out.println("DTOKEN recived to validate:" + dtoken);
		long nowMillis = System.currentTimeMillis();
		if (dtoken.getExpiration() > nowMillis) {
			return true;
		}
		return false;
	}

	public boolean refreshToken(long exp) {
		boolean flag = false;
		long nowMillis = System.currentTimeMillis();
		if (nowMillis - exp <= 86400) {
			flag = true;
		}
		System.out.println("Refresh the token:" + flag);
		return flag;
	}

}