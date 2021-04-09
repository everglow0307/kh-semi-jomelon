package com.jomelon.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jomelon.domain.NoticeVO;
import com.jomelon.service.NoticeService;
import com.jomelon.service.impl.NoticeServiceImpl;


@WebServlet("/admin/noticeList.do")
public class NoticeListController extends HttpServlet {
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
	NoticeService service = new NoticeServiceImpl();
		
		List<NoticeVO> list = service.getNoticeList(request);
		int count = service.getNoticeCount(request);
		
		// view에 전달
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		
		request.setAttribute("contentPage","/view/admin/notice/list.jsp");
		request.getRequestDispatcher("/view/template/admin/mainAdmin.jsp").forward(request, response);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//일괄공개, 일괄삭제 구현!

		NoticeService service = new NoticeServiceImpl();
		service.deleteNoticeAll(request);

		
		// 요청후 list페이지로 이동-> get요청 호출 
		response.sendRedirect("noticeList.do");
		
	}

}
