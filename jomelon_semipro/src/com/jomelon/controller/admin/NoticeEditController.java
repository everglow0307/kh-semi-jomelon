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


@WebServlet("/admin/noticeEdit.do")
public class NoticeEditController extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id= Integer.parseInt(request.getParameter("id"));
		
		NoticeService service = new NoticeServiceImpl();
		NoticeVO notice = service.getNotice(id);
		
		request.setAttribute("n", notice);
		
		request.setAttribute("contentPage","/view/admin/notice/edit.jsp");
		request.getRequestDispatcher("/view/template/admin/mainAdmin.jsp").forward(request, response);

		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NoticeService service = new NoticeServiceImpl();
		service.editNotice(request);
		
	
		response.sendRedirect("noticeList.do");
	}

}
