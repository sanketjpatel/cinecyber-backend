package com.vdoshi3.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.vdoshi3.exception.NotLoggedInException;


@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
	// @Autowired
	// Environment env;

	public static final String CREDENTIALS_NAME = "Access-Control-Allow-Credentials";
	public static final String ORIGIN_NAME = "Access-Control-Allow-Origin";
	public static final String METHODS_NAME = "Access-Control-Allow-Methods";
	public static final String HEADERS_NAME = "Access-Control-Allow-Headers";
	public static final String MAX_AGE_NAME = "Access-Control-Max-Age";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String s = request.getMethod();
		if (s.equals("OPTIONS")) {
			System.out.println("Options method called");
			return false;
		}
		String newToken = "";
		String token = request.getHeader("Authorization");
		System.out.println("Got request with authorization header :" + token);
		if (token == null || !token.startsWith("Bearer ")) {
			System.out.println("Throwing Not Logged In Exception");
			throw new NotLoggedInException();
		}
		JwtToken jwt = new JwtToken();
		DecodedToken dtoken;
		
		dtoken = jwt.parseJWT(token.substring(7));

		if (validateUser(dtoken)) {
			if (refreshToken(80000, dtoken.getExpiration())) {

				newToken = jwt.createJWT(dtoken.getId(), dtoken.getIssuer(), dtoken.getSubject(), 86400);
			}
			request.setAttribute("requestor", dtoken);
			response.addHeader("Authorization", "Bearer " + newToken);
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

	public boolean refreshToken(long ttr, long exp) {
		boolean flag = false;
		long nowMillis = System.currentTimeMillis();
		if (nowMillis - exp <= ttr) {
			flag = true;
		}
		System.out.println("Refresh the token:" + flag);
		return flag;
	}

}