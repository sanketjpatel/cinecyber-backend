package com.vdoshi3.utils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@WebFilter(urlPatterns = "/api/*")
public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        if(request.getMethod().equals("OPTIONS")){
        	System.out.println("Options method called: " + request.getMethod());
        	chain.doFilter(req, res);
        	return;
        }
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException("Missing or invalid Authorization header.");
        }

        final String token = authHeader.substring(7);
        try {
        	JwtToken jwt = new JwtToken();
    		DecodedToken dtoken;
    		dtoken = jwt.parseJWT(token);
			request.setAttribute("requestor", dtoken);
        }
        catch (final SignatureException e) {
            throw new ServletException("Invalid token.");
        }
        chain.doFilter(req, res);
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