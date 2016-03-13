package com.vdoshi3.utils;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
public class JwtInterceptor extends HandlerInterceptorAdapter {

	// @Autowired
	// UserDao repo;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String token = request.getHeader("Authorization");
		System.out.println("Got request with authorization header :" + token);
		if (token == null || !token.startsWith("Bearer ")) {
			throw new NotLoggedInException();
		}
		JwtToken jwt = new JwtToken();
		DecodedToken dtoken = jwt.parseJWT(token.substring(7));

		// User u = validateUser(dtoken);
		// if (u != null) {
		// request.setAttribute("requestor",u);
		// return true;
		// } else {
		// throw new NotLoggedInException();
		// }
		if (validateUser(dtoken)) {
			request.setAttribute("requestor", dtoken);
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

	// @Transactional
	// private User validateUser(DecodedToken dtoken) {
	// System.out.println("DTOKEN recived to validate:"+dtoken);
	// User u = null;
	// long nowMillis = System.currentTimeMillis();
	// if (dtoken.getExpiration() > nowMillis) {
	// u = new UserDaoImp().findById(dtoken.getIssuer());
	// }
	// return u;
	// }
}