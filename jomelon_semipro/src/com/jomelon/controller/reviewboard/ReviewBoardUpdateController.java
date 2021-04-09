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

@WebServlet("/reviewBoardUpdate.do")
public class ReviewBoardUpdateController extends HttpServlet{

	ReviewService review = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//수정페이지에 보여줄 정보 가져오기
		ReviewBoardVO reviewVO = review.getOneUpdateReview(request);
		
		request.setAttribute("review", reviewVO);
		request.setAttribute("contentPage", "/view/reviewBoard/reviewBoardUpdate.jsp");
		request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);;
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String reviewPw = request.getParameter("reviewPw");
		String password = request.getParameter("password");
		
		if(reviewPw.equals(password)) {
			//비밀번호 일치할 경우만 수정
			int result = review.updateReview(request);
			
		}else {
			request.setAttribute("msg", "수정시 비밀번호가 맞지 않습니다.");
		}
		
		request.getRequestDispatcher("reviewBoardList.do").forward(request,response);
	
	}
	
}
