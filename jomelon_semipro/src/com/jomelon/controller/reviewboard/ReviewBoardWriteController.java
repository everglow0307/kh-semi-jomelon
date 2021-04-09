package com.jomelon.controller.reviewboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jomelon.service.ReviewService;
import com.jomelon.service.impl.ReviewServiceImpl;

@WebServlet("/reviewBoardWrite.do")
public class ReviewBoardWriteController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	ReviewService review = new ReviewServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setAttribute("contentPage","/view/reviewBoard/reviewBoardWrite.jsp");
		request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//리뷰등록
		int result = review.insertReview(request);
		
		if(result>0) {
			request.getRequestDispatcher("reviewBoardList.do").forward(request, response);
		}
	}
	
}
