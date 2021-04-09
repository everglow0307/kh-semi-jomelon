package com.jomelon.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class EncodingFilter implements Filter {

	private String charset = "UTF-8";
	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		chain.doFilter(request, response);
	}

	public void destroy() {
		
	}

	
}
