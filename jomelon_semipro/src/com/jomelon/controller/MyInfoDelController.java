package com.jomelon.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jomelon.domain.UserVO;
import com.jomelon.service.UserService;
import com.jomelon.service.impl.UserServiceImpl;

/**
 * Servlet implementation class MyInfoDelController
 */
@WebServlet("/myinfoDel.do")
public class MyInfoDelController extends HttpServlet {
	private UserService userService = new UserServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션에서 아이디정도 가져욤.
		UserVO user = userService.getMyInfoPw(request);
		
		//회원탈퇴
		int result = userService.userDel(user);
				
		//회원 탈퇴  성공
		if(result>0) {
			HttpSession session = request.getSession();
			session.invalidate();
			
			request.setAttribute("contentPage","/view/myinfo/myInfoDelSuccess.jsp");
			request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);
		}else {
			response.setContentType("text/html; charset=UTF-8");	 
			PrintWriter out = response.getWriter();
			out.println("<script>alert('탈퇴 실패'); location.href='myinfoDel.do';</script>");
			 
			out.flush();
		}
		
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute ("id");
		System.out.println("post세션아이디"+user_id);
		
	}

}
