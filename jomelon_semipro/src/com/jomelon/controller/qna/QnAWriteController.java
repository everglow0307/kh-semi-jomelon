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

@WebServlet("/QnAWrite.do")
public class QnAWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("contentPage","/view/QnA/QnAWrite.jsp");
		request.getRequestDispatcher("/view/template/main.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String QnATitle = request.getParameter("QnATitle");
		String QnAContent = request.getParameter("QnAContent");
		String userId = request.getParameter("userId");
		
			response.setContentType("text/html; charset=UTF-8");
			QnABoardVO qnaVO = new QnABoardVO();
			if(QnATitle == null || QnAContent == null || userId == null) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('입력이 안 된 사항이 있습니다.')");
				script.println("history.back()");
				script.println("</script>");
			} else {
				response.setContentType("text/html; charset=UTF-8");
				QnADAO qnaDAO = new QnADAOImpl();
				int result = qnaDAO.write(QnATitle, userId, QnAContent);
				if(result == -1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('글쓰기에 실패했습니다.')");
					script.println("history.back()");
					script.println("</script>");
				} else {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('글쓰기가 완료되었습니다.')");
					script.println("</script>");
					response.sendRedirect("QnAList.do");
				}
			}
		}
	
	

}
