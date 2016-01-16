/*
 *Online Exam Portal
 *Bodacious Assignment
 *
 *@author Parth Khandelwal
 *
 *Servlet to Finish the review and redirect to HomePage
 */
 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

public class FinishReview extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		System.out.println("Finishing Review*******************");
		HttpSession session = request.getSession(true);
		Integer currentQues = (Integer)session.getAttribute("currentQues");
		ServletContext context = getServletContext();
		if(currentQues != null) {
			session.invalidate();
		}
		RequestDispatcher reqDis = context.getRequestDispatcher("/");
		reqDis.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		doGet(request, response);
	}
}
