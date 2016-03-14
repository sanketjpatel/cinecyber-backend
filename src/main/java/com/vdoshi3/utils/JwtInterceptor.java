package com.vdoshi3.utils;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.vdoshi3.exception.NotLoggedInException;


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