package com.jomelon.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jomelon.domain.UserVO;
import com.jomelon.service.UserService;
import com.jomelon.service.impl.UserServiceImpl;


@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserService userservice = new UserServiceImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("contentPage","/view/login/login.jsp");
		request.getRequestDispatcher("/view/template/admin/mainAdmin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			//입력받은 아이디, 비밀번호값 저장 -로그인정보
			UserVO user = userservice.getLogin(request);
			
			//로그인 인증
			int result = userservice.login(user);
			//결과확인
			System.out.println(result);
			
			HttpSession session = request.getSession();
			
			//로그인 성공 시에만  세션 저장
				// result 2: 관리자로그인 / result 1: 사용자로그인
			if(result==2) {	
				//결과값 2 관리자로그인
				user = userservice.Info(user);
				session.setAttribute("adminlogin", true);
				session.setAttribute("userInfo", user);
				
				request.getRequestDispatcher("/view/template/admin/mainAdmin.jsp").forward(request,response);
				
			}else if (result==1) {
				user = userservice.Info(user);
				session.setAttribute("login", true);
				session.setAttribute("userInfo", user);
				
				session.setAttribute("id", user.getUserId());   

				request.getRequestDispatcher("/view/template/realMain.jsp").forward(request,response);
				
			}else {
				session.setAttribute("loginerror",true);
				request.getRequestDispatcher("/view/login/login.jsp").forward(request,response);
			}
			
			
		}
			
		
		
	
}
