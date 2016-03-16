package com.vdoshi3.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/*")
	public class CORSFilter implements Filter {

		@Override
		public void init(FilterConfig arg0) throws ServletException {
		}

		@Override
		public void doFilter(ServletRequest req, ServletResponse res,
				FilterChain chain) throws ServletException, IOException {
			HttpServletResponse response = (HttpServletResponse) res;
			
			response.setHeader("Access-Control-Allow-Methods",
					"POST, GET, PUT, OPTIONS, DELETE");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers","Access-Control-Allow-Headers,Access-Control-Expose-Headers,x-requested-with, Content-Type,accept, Origin,Access-Control-Request-Method, Access-Control-Request-Headers,Authorization");
			response.setHeader("Access-Control-Allow-Origin", "*");
			chain.doFilter(req, res);
		}

		@Override
		public void destroy() {
			//Do nothing
		}

	}

