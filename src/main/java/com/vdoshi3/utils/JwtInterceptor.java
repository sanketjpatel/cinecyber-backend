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

import com.vdoshi3.exception.InvalidSignatureException;
import com.vdoshi3.exception.NotLoggedInException;

import io.jsonwebtoken.SignatureException;

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
		
		
		response.setHeader(CREDENTIALS_NAME, "true");
		  response.setHeader(ORIGIN_NAME, "http://localhost:3045");
		  response.setHeader(METHODS_NAME, "GET, OPTIONS, POST, PUT, DELETE");
		  response.setHeader(HEADERS_NAME, "Origin, X-Requested-With, Content-Type, Accept,Access-Control-Expose-Headers,Access-Control-Allow-Headers,Authorization");
		  response.setHeader(MAX_AGE_NAME, "3600");
		
		String s = request.getMethod();
		if(s == "OPTIONS"){
			System.out.println("Options method called");
			return true;	
		}
//		response.addHeader("Access-Control-Allow-Origin", "*");
		String newToken = "";
		String token = request.getHeader("Authorization");
		System.out.println("Got request with authorization header :" + token);
		if (token == null || !token.startsWith("Bearer ")) {
			System.out.println("Throwing Not Logged In Exception");
			throw new NotLoggedInException();
		}
		JwtToken jwt = new JwtToken();
		DecodedToken dtoken;
//		try{
		dtoken = jwt.parseJWT(token.substring(7));
//		}catch(SignatureException sg){
//			throw new InvalidSignatureException();
//		}
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