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

@WebServlet("/reviewBoardDelete.do")
public class ReviewBoardDeleteController extends HttpServlet{

	ReviewService review = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			ReviewBoardVO reviewVo = review.getOneUpdateReview(request);
			request.setAttribute("review", reviewVo);
			request.setAttribute("contentPage", "/view/reviewBoard/reviewBoardDelete.jsp");
			request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);;
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String reviewPw = request.getParameter("reviewPw");
		String password = request.getParameter("password");
		
		if(reviewPw.equals(password)) {
			int result = review.deleteReview(request);
			
		}else {
			request.setAttribute("msg", "삭제시 비밀번호가 맞지 않습니다.");
		}
		
		request.getRequestDispatcher("reviewBoardList.do").forward(request,response);
	
	}
	
}