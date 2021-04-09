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

@WebServlet("/QnADelete.do")
public class QnADeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int QnAID = 0;
		if(request.getParameter("QnAID") != null) {
			QnAID = Integer.parseInt(request.getParameter("QnAID"));
		}
		
		QnABoardVO qnaVO = new QnADAOImpl().getQnA(QnAID);
		
				QnADAO qnaDAO = new QnADAOImpl();
				int result = qnaDAO.delete(QnAID);
				if(result == -1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('글 삭제에 실패했습니다.')");
					script.println("history.back()");
					script.println("</script>");
				} else {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("location.href = 'QnA.do'");
					script.println("</script>");
					request.setAttribute("contentPage","/view/QnA/QnAList.jsp");
					request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);
				}
	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") != null) {
			userId = (String)session.getAttribute("userId");
		}
		int QnAID = 0;
		if(request.getParameter("QnAID") != null) {
			QnAID = Integer.parseInt(request.getParameter("QnAID"));
		}
		if(QnAID == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 글입니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
		QnABoardVO qnaVO = new QnADAOImpl().getQnA(QnAID);
		if(!userId.equals(qnaVO.getUserId())) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('권한이 없습니다.')");
			script.println("history.back()");
			script.println("</script>");
		} else {
				QnADAO qnaDAO = new QnADAOImpl();
				int result = qnaDAO.delete(QnAID);
				if(result == -1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('글 삭제에 실패했습니다.')");
					script.println("history.back()");
					script.println("</script>");
				} else {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("location.href = 'QnA.do'");
					script.println("</script>");
				}
			}
		}
	}
