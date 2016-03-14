package com.vdoshi3.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.vdoshi3.exception.NotLoggedInException;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
	// @Autowired
	// Environment env;

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
			if (refreshToken(80000, dtoken.getExpiration())) {

				newToken = jwt.createJWT(dtoken.getId(), dtoken.getIssuer(), dtoken.getSubject(),
						86400);
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

	// private Properties getProperties(){
	// Properties props = new Properties();
	// InputStream is = null;
	// try{
	// is =
	// JwtInterceptor.class.getResourceAsStream("/resources/app.properties");
	// props.load(is);
	// }catch(IOException fe){
	// System.out.println(fe);
	// }finally{
	// if(is!=null){
	// try {
	// is.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	// return props;
	// }

}