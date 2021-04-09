package com.jomelon.controller.reviewboard;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jomelon.service.ReviewService;
import com.jomelon.service.impl.ReviewServiceImpl;

@WebServlet("/reviewBoardReWrite.do")
public class ReviewBoardReWriteController extends HttpServlet{

	ReviewService review = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ref = Integer.parseInt(request.getParameter("ref"));
		int reStep = Integer.parseInt(request.getParameter("reStep"));
		int reLevel = Integer.parseInt(request.getParameter("reLevel"));
		
		request.setAttribute("ref", ref);
		request.setAttribute("reStep", reStep);
		request.setAttribute("reLevel", reLevel);
		
		request.setAttribute("contentPage", "/view/reviewBoard/reviewBoardReWrite.jsp");
		request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//답변글 등록
		int result = review.reInsertReview(request);
		
		if(result>0) {
			request.getRequestDispatcher("reviewBoardList.do").forward(request, response);
		}
		
	}
	
}