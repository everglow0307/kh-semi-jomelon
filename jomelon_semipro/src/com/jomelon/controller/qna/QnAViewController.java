package com.jomelon.controller.qna;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jomelon.dao.QnADAO;
import com.jomelon.dao.impl.QnADAOImpl;
import com.jomelon.domain.QnABoardVO;

@WebServlet("/QnAView.do")
public class QnAViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int QnAID = 0;
		if(request.getParameter("QnAID") != null) {
			QnAID = Integer.parseInt(request.getParameter("QnAID"));
		}
		QnABoardVO qnaVO = new QnADAOImpl().getQnA(QnAID);
		request.setAttribute("qnaVO", qnaVO);
		request.setAttribute("contentPage","/view/QnA/QnAView.jsp");

		request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("contentPage","/view/QnA/QnAView.jsp");
		request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);
		
		int QnAID = 0;
		if(request.getParameter("QnAID") != null) {
			QnAID = Integer.parseInt(request.getParameter("QnAID"));
		}
		QnABoardVO qnaVO = new QnADAOImpl().getQnA(QnAID);
		
		
	}

}
