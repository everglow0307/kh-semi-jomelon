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


@WebServlet("/authNoConfirm.do")
public class authNoConfirmController extends HttpServlet {
	
	UserService uservice = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("contentPage", "/view/login/findPasswordCompl.jsp");
		request.getRequestDispatcher("/view/template/main.jsp").forward(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//메일 보낸 인증번호
		int ranAuthNo = Integer.parseInt(request.getParameter("ranAuthNo"));
		//사용자가 입력한 인증번호
		int inputAuthNo = Integer.parseInt(request.getParameter("inputAuthNo"));
		
		JSONObject json = new JSONObject();
		
		if(ranAuthNo==inputAuthNo) {
			String userEmail = request.getParameter("inputEmail").trim();
			//인증번호 일치 시 이메일 값으로 dao연결해서 저장된 비밀번호 가져와서 결과값 보내기
			UserVO user = uservice.emailCheck(userEmail);

			//session에 고객정보 저장
			HttpSession session = request.getSession();
			session.setAttribute("u",user);
			json.put("confirm", true);
		}else {
			json.put("confirm", false);
		}
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().append(json.toJSONString());
		
	}

}
