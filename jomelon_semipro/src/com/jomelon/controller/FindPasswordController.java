package com.jomelon.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.jomelon.domain.UserVO;
import com.jomelon.service.UserService;
import com.jomelon.service.impl.UserServiceImpl;

@WebServlet("/findPassword.do")
public class FindPasswordController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("contentPage","/view/login/findPassword.jsp");
		request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);
		
	}
	


}
