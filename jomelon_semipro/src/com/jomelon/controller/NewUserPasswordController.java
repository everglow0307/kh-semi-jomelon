package com.jomelon.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jomelon.service.UserService;
import com.jomelon.service.impl.UserServiceImpl;

@WebServlet("/newUserPassword.do")
public class NewUserPasswordController extends HttpServlet{

	UserService uservice = new UserServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//새로운 비밀번호로 변경
		int result = uservice.userPasswordUpdate(request);
		
		if(result>0) {
			request.setAttribute("contentPage", "/view/login/login.jsp");
			request.getRequestDispatcher("/view/template/main.jsp").forward(request,response);
		}
	}
	
	
}
