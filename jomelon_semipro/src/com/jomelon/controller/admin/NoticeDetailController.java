package com.jomelon.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jomelon.domain.NoticeVO;
import com.jomelon.service.NoticeService;
import com.jomelon.service.impl.NoticeServiceImpl;

@WebServlet("/admin/noticeDetail.do")
public class NoticeDetailController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// id넘겨받음.
		int id= Integer.parseInt(request.getParameter("id"));
		
		NoticeService service = new NoticeServiceImpl();
		NoticeVO notice = service.getNotice(id);
		NoticeVO nextNotice = service.getNextNotice(id);
		NoticeVO prevNotice = service.getPrevNotice(id);
		
		request.setAttribute("n", notice);
		request.setAttribute("nn", nextNotice);
		request.setAttribute("pn", prevNotice);

		//forward
		request.setAttribute("contentPage","/view/admin/notice/detail.jsp");
		request.getRequestDispatcher("/view/template/admin/mainAdmin.jsp").forward(request, response);

	}
}
