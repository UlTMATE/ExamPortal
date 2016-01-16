/*
 *Online Exam Portal
 *Bodacious Assignment
 *
 *@author Parth Khandelwal
 *
 *Servlet to STORE ANSWER of current question
 *and CHANGE the QUESTION according to the button pressed by user
 */
 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

public class ChangeQuestion extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Integer currentQues = (Integer)session.getAttribute("currentQues");
		ServletContext context = getServletContext();
		if(currentQues == null) {
			RequestDispatcher reqDis = context.getRequestDispatcher("/");
			reqDis.forward(request, response);
		} else {
			String userAnswers[] = request.getParameterValues("option");
			String answers = "";
			if(userAnswers != null) {
				for(int i=0; i<userAnswers.length; i++) {
					answers += userAnswers[i]+" ";
				}
			}
			ArrayList userAnsList = (ArrayList)session.getAttribute("userAnsList");
			if(userAnsList.size()>currentQues.intValue()){
				userAnsList.set(currentQues.intValue(), answers.trim());
			} else {
				userAnsList.add(currentQues.intValue(), answers.trim());
			}
			System.out.println("Added "+ answers.trim()+ "at " +currentQues.intValue());
			session.setAttribute("userAnsList", userAnsList);
			
			String btnPressed = request.getParameter("btn");
			if(btnPressed.equalsIgnoreCase("Previous")) {
				session.setAttribute("currentQues", currentQues.intValue() - 1);
			} else {
				session.setAttribute("currentQues", currentQues.intValue() + 1);
			}
			RequestDispatcher reqDis = context.getRequestDispatcher("/Question");
			reqDis.forward(request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		doGet(request, response);
	}
}
