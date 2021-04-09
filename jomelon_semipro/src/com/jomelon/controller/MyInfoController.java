package com.jomelon.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jomelon.domain.UserVO;
import com.jomelon.service.UserService;
import com.jomelon.service.impl.UserServiceImpl;

@WebServlet("/myinfo.do")
public class MyInfoController extends HttpServlet {
	private UserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("contentPage","/view/myinfo/myInfoPw.jsp");
		request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션아이디, 입력비밀번호 저장
		UserVO user = userService.getMyInfoPw(request);
		
		//로그인인증
		int result = userService.login(user);
		
		UserVO userInfo = userService.getUserInfo(request);
		request.setAttribute("u", userInfo);
		
		
		//비밀번호 일치시  유저정보가져와서 회원정보페이지로 전달
		if(result ==0) {
			response.setContentType("text/html; charset=UTF-8");	 
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호를 다시한번 확인해주세요.'); location.href='myinfo.do';</script>");
			out.flush();
			
		}else {
			request.setAttribute("contentPage","/view/myinfo/myInfo.jsp");
			request.getRequestDispatcher("/view/template/main.jsp").forward(request,response);
		}
	}

}
