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

@WebServlet("/emailCheck.do")
public class emailCheckController extends HttpServlet{
	
	UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		execute(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		execute(request, response);
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int result;
		
		String inputEmail = request.getParameter("inputEmail").trim();
		System.out.println("입력값: "+inputEmail);
		
		//디비에 저장된 이메일인지 확인하고 유저정보 가져옴.
		UserVO user = userService.emailCheck(inputEmail);
		
		if(user==null) {
			result = 0;
		}else {
			result = 1;
		}
		PrintWriter out = response.getWriter();
		out.print(result+"");
	}
}
