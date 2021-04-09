package com.jomelon.controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jomelon.domain.NoticeVO;
import com.jomelon.service.NoticeService;
import com.jomelon.service.impl.NoticeServiceImpl;




@WebServlet("/noticeDetail.do")
public class DetailController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// id넘겨받음
		int id= Integer.parseInt(request.getParameter("id"));
		
		NoticeService service = new NoticeServiceImpl();
		
		NoticeVO notice = service.getNotice(id);
		
		//?
		NoticeVO nextNotice = service.getNextNotice(id);
		NoticeVO prevNotice = service.getPrevNotice(id);
		
		request.setAttribute("n", notice);
		request.setAttribute("nn", nextNotice);
		request.setAttribute("pn", prevNotice);

		//forward
		request.setAttribute("contentPage","/view/notice/detail.jsp");
		request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);
		
	}

	
	

}
