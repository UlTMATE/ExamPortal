/*
 *Online Exam Portal
 *Bodacious Assignment
 *
 *@author Parth Khandelwal
 *
 *Servlet to CHANGE the QUESTION in review mode according to the button pressed by user
 */
 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

public class ChangeReviewQuestion extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Integer currentQues = (Integer)session.getAttribute("currentQues");
		ServletContext context = getServletContext();
		if(currentQues == null) {
			RequestDispatcher reqDis = context.getRequestDispatcher("/");
			reqDis.forward(request, response);
		} else {
			String btnPressed = request.getParameter("btn");
			if(btnPressed.equalsIgnoreCase("Previous")) {
				session.setAttribute("currentQues", currentQues.intValue() - 1);
			} else {
				session.setAttribute("currentQues", currentQues.intValue() + 1);
			}
			RequestDispatcher reqDis = context.getRequestDispatcher("/ReviewQuestion");
			reqDis.forward(request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		doGet(request, response);
	}
}
