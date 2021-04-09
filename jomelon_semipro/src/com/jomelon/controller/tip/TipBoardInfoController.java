package com.jomelon.controller.tip;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/tipBoardInfo.do")
public class TipBoardInfoController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		execute(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		execute(request,response);
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
	
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println(num);
		
		request.setAttribute("num", num);
		request.setAttribute("contentPage", "/tipboard/BoardInfo.jsp");
		request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);
	}
	
}
