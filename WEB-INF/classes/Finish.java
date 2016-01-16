/*
 *Online Exam Portal
 *Bodacious Assignment
 *
 *@author Parth Khandelwal
 *
 *Servlet to store the answer of last question and then display the result result
 */
 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

public class Finish extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList userAnsList = (ArrayList)session.getAttribute("userAnsList");
		ArrayList correctAnsList = (ArrayList)session.getAttribute("correctAnsList");
		if(correctAnsList==null) {
			ServletContext context = getServletContext();
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
			userAnsList.add(((Integer)session.getAttribute("currentQues")).intValue(), answers.trim());
			session.setAttribute("userAnsList", userAnsList);
			
			session.setAttribute("currentQues", 0);
			response.setContentType("text/html");
			int score = calculateScore(correctAnsList, userAnsList);
			PrintWriter out = response.getWriter();
			out.println("<HTML> <HEAD> <link rel='StyleSheet' href='css/QuestionPage.css' type='text/css' />");
			out.println("<BODY><CENTER>");
			out.println("<H3> You Scored " +score+ "</H3>");
			out.println("<FORM method='post'>"
						+ "<INPUT TYPE='SUBMIT' VALUE='Retest' class=btn FORMACTION='FinishReview' /> &nbsp;&nbsp;"
						+ "<INPUT TYPE='SUBMIT' VALUE='Review' class=btn FORMACTION='ReviewQuestion' /> "
						+ " </FORM>");
			out.println("</CENTER></BODY>");
			out.println("</HEAD></HTML>");
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		doGet(request, response);
	}
	
	public int calculateScore(ArrayList correctAnsList, ArrayList userAnsList) {
		int score=0;
		for(int i=0; i<correctAnsList.size(); i++){
			if(correctAnsList.get(i).equals(userAnsList.get(i))) {
				score+=10;
			} else {
				continue;
			}
		}
		return score;
	}
}