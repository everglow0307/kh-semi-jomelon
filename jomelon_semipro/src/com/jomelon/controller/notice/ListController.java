package com.jomelon.controller.notice;

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


@WebServlet("/noticeList.do")
public class ListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		NoticeService service = new NoticeServiceImpl();
		
		List<NoticeVO> list = service.getNoticePubList(request);
		int count = service.getNoticeCount(request);
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		
		request.setAttribute("contentPage","/view/notice/list.jsp");
		request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);

	}

}
