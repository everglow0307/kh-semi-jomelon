package com.jomelon.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jomelon.domain.UserVO;
import com.jomelon.service.UserService;
import com.jomelon.service.impl.UserServiceImpl;

@WebServlet("/idCheck.do")
public class IdCheckController extends HttpServlet {

	private UserService userService = new UserServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String inputId = request.getParameter("inputId").trim();
		System.out.println("입력값: "+inputId);
		
		
		int result = userService.idCheck(inputId);
		System.out.println(result);
		
		PrintWriter out = response.getWriter();
		out.print(result+"");

	}

}
