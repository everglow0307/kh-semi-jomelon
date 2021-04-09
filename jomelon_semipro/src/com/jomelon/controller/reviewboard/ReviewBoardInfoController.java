package com.jomelon.controller.reviewboard;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jomelon.domain.ReviewBoardVO;
import com.jomelon.service.ReviewService;
import com.jomelon.service.impl.ReviewServiceImpl;

@WebServlet("/reviewBoardInfo.do")
public class ReviewBoardInfoController extends HttpServlet{

	ReviewService review = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//리뷰글 1개 정보 가져옴.
		ReviewBoardVO reviewVo = review.getOneReview(request);
		request.setAttribute("review", reviewVo);
		
		request.setAttribute("contentPage", "/view/reviewBoard/reviewBoardView.jsp");
		request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
}