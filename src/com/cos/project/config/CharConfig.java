package com.cos.project.config;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharConfig implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8"); // css가 깨질때가 있음.
		
		// requset 한글 확인
//		String username = request.getParameter("username");
//		System.out.println("username : "+username);
		
		// 응답 확인
//		PrintWriter out = response.getWriter();
//		out.println("테스트");
//		out.flush();
		
		chain.doFilter(request, response);

		
	}
	
}
