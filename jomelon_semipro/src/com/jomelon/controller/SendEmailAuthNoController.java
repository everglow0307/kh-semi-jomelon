package com.jomelon.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import com.jomelon.util.MailAuth;

@WebServlet("/sendAuthNo.do")
public class SendEmailAuthNoController extends HttpServlet{
	
	UserService uservice = new UserServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException  {
		
		//사용자가 입력한 이메일
		String inputEmail = req.getParameter("inputEmail");
		
		//result[0]-인증번호 , result[1]-메일전송여부
		int[] result = uservice.sendUserMailAuthNo(inputEmail);
		
		//메일전송 성공시
		if(result[1]==1) {
			JSONObject json = new JSONObject();
			json.put("result", "1");
			json.put("authNo", result[0]);
			res.setContentType("application/json; charset=UTF-8");
			res.getWriter().append(json.toJSONString());
		}
		
			
	}
}
