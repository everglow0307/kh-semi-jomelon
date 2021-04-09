package com.jomelon.controller.reviewboard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jomelon.domain.ReviewBoardVO;
import com.jomelon.service.ReviewService;
import com.jomelon.service.impl.ReviewServiceImpl;

@WebServlet("/reviewBoardList.do")
public class ReviewBoardListController extends HttpServlet{

	ReviewService review = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request,response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request,response);
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//화면에 보여질 게시글의 개수를 지정
		int pageSize = 10;
		
		//현재 보여지고 있는 페이지의 넘버값을 읽어드림.
		String pageNum = request.getParameter("pageNum");
		
		//검색필터랑 검색어저장
		String searchFilter = request.getParameter("searchFilter");
		String search = request.getParameter("search");
		
		//검색필터와 검색어 기본값 저장
		if(searchFilter==null||searchFilter.equals("")) {
			searchFilter="subject";
		}
		if(search==null||search.equals("")) {
			search="";
		}
		
		//현재 페이지 null처리(기본 첫화면/리뷰 등록/수정/삭제 후 리스트 화면 보여질 때)
		if(pageNum==null) {
			pageNum="1";
		}
		
		//전체 게시글의 갯수
		int count = 0;
		
		//jsp페이지 내에서 보여질 글번호
		int number=0;
		
		//현재 보여지고 있는 페이지 문자를 숫자로 변환
		int currentPage = Integer.parseInt(pageNum);
		
		//전체 리뷰글 개수 받아오기
		count = review.getAllReviewCnt(searchFilter, search);

		//현재 보여질 페이지의 글 시작번호와 끝번호 설정
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = currentPage*pageSize;
		
		//리뷰글 10개 가져오기(startRow, endRow기준)
		ArrayList<ReviewBoardVO> reviewList = review.getAllReview(startRow,endRow,searchFilter,search);
		
		number = count-(currentPage-1)*pageSize;
		
		//수정,삭제시 비밀번호가 틀렸다면
		String msg = (String)request.getAttribute("msg");
		
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("count", count);
		request.setAttribute("currentPage", currentPage);
		if(msg!=null) {
			request.setAttribute("msg", msg);
		}
		
		request.setAttribute("searchFilter", searchFilter);
		request.setAttribute("search", search);
		
		request.setAttribute("contentPage", "/view/reviewBoard/reviewBoardList.jsp");
		request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);
		
		
	}
}
