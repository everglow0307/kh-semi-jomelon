package com.jomelon.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jomelon.service.UserService;
import com.jomelon.service.impl.UserServiceImpl;



@WebServlet("/join.do")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserService userService = new UserServiceImpl();
  
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("contentPage","/view/join/join.jsp");
		request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		//회원가입
		int result = userService.joinUser(request);
		
		//회원가입성공
		if(result>0) {
			response.sendRedirect("main.do");
		}else {
			PrintWriter out = response.getWriter();
			out.println("회원가입 실패");
		}
		
	}

}
