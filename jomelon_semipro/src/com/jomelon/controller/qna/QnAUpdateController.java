package com.jomelon.controller.qna;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jomelon.domain.QnABoardVO;
import com.jomelon.dao.QnADAO;
import com.jomelon.dao.impl.QnADAOImpl;

@WebServlet("/QnAUpdate.do")
public class QnAUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int QnAID = 0;
		if(request.getParameter("QnAID") != null) {
			QnAID = Integer.parseInt(request.getParameter("QnAID"));
		}
		
		QnABoardVO qnaVO = new QnADAOImpl().getQnA(QnAID);
		
		request.setAttribute("qnaVO", qnaVO);
		
		request.setAttribute("contentPage","/view/QnA/QnAUpdate.jsp");
		request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int QnAID = 0;
		if(request.getParameter("QnAID") != null) {
			QnAID = Integer.parseInt(request.getParameter("QnAID"));
		}
		
		String QnATitle = request.getParameter("QnATitle");
		String QnAContent = request.getParameter("QnAContent");
		

		QnADAO qnaDAO = new QnADAOImpl();
		int result = qnaDAO.update(QnAID, QnATitle, QnAContent);
		
		if(result == -1) {
			request.setAttribute("contentPage","/view/QnA/QnAUpdate.jsp");
			request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);
			System.out.println("실패");
			}
			else {
				request.setAttribute("contentPage","/view/QnA/QnAList.jsp");
				request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);
			}

	}
}
