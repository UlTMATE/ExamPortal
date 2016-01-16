/*
 *Online Exam Portal
 *Bodacious Assignment
 *
 *@author Parth Khandelwal
 *
 *Servlet to DISPLAY the CORRECT ANSWER of current question
 *and CHANGE the QUESTION according to the button pressed by user
 */
 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

public class ReviewQuestion extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Integer currentQues = (Integer)session.getAttribute("currentQues");
		ServletContext context = getServletContext();
		if(currentQues == null) {
			RequestDispatcher reqDis = context.getRequestDispatcher("/");
			reqDis.forward(request, response);
		} else {
			if(currentQues.intValue()==9){
				session.setAttribute("currentQues", 0);
			}
			ArrayList correctAnsList = (ArrayList)session.getAttribute("correctAnsList");
			ArrayList userAnsList = (ArrayList)session.getAttribute("userAnsList");
			ArrayList correctAns = new ArrayList();
			ArrayList userAns = new ArrayList();
			String correctStr = correctAnsList.get(currentQues.intValue())+"";
			String userStr = userAnsList.get(currentQues.intValue())+"";
			StringTokenizer stroker = new StringTokenizer(correctStr);
			while(stroker.hasMoreTokens()){
				correctAns.add(stroker.nextToken());
			}
			stroker = new StringTokenizer(userStr);
			while(stroker.hasMoreTokens()){
				userAns.add(stroker.nextToken());
			}
	//		RequestDispatcher reqDis = context.getRequestDispatcher("/Question");
	//		reqDis.include(request, response);
	
			int quesNum = (int)((ArrayList)session.getAttribute("questionList")).get(currentQues.intValue());
			LinkedList question = Database.getQuestion(quesNum);
			PrintWriter out = response.getWriter();
			out.println("<!doctype html><html lang='en'>");
			out.println("<head> <meta charset='utf-8' /> <title>Review</title>"
						+ "<link rel='StyleSheet' type='text/css' href='css/QuestionPage.css' />");
			out.println("<script>");
			out.println("function set(){");
	//		out.println("document.write('In this Class')");
			out.println("document.getElementById('questionForm').action='ChangeReviewQuestion';");
			out.println("var finishBtn = document.getElementById('finish');");
			out.println("if(finishBtn!=null){ finishBtn.formaction='FinishReview'; }");
			String currentOption;
			for(int i=0; i<userAns.size(); i++){
				currentOption = userAns.get(i)+"";
				out.println("document.getElementById('" +currentOption+ "op').checked=true;");
				if(correctAns.contains(currentOption)){
					out.println("document.getElementById('" +currentOption+ "').style.background='green';");
				} else {
					out.println("document.getElementById('" +currentOption+ "').style.background='red';");
				}
			}
			for(int i=0; i<correctAns.size(); i++){
				currentOption = correctAns.get(i)+"";
				out.println("document.getElementById('" +currentOption+ "').style.background='green';");
			}
			out.println("}");
			out.println("</script>");
			out.println("</head>");
			out.println("<body onload='set()'>");
			out.println("<h3>Q" +(currentQues.intValue()+1)+ ". " +question.get(1)+ "</h3>");
			out.println("<form id='questionForm' method='get' action='ChangeQuestion'>");
			String toggleButton;
			if((question.get(7)+"").equals("true")){
				toggleButton="checkbox";
			} else {
				toggleButton="radio";
			}
			out.println("<span id='A'>A.<input type='" +toggleButton+ "' name='option' value='A' id='Aop' class='option' disabled> " +question.get(2)+" </span><br />");
			out.println("<span id='B'>B.<input type='" +toggleButton+ "' name='option' value='B' id='Bop' class='option' disabled> " +question.get(3)+" </span><br />");
			out.println("<span id='C'>C.<input type='" +toggleButton+ "' name='option' value='C' id='Cop' class='option' disabled> " +question.get(4)+" </span><br />");
			out.println("<span id='D'>D.<input type='" +toggleButton+ "' name='option' value='D' id='Dop' class='option' disabled> " +question.get(5)+" </span><br /><br />");
			out.println("<hr />");
			if(currentQues!=0){
				out.println("<input type='submit' value='Previous' name='btn' class='btn' /> &nbsp;&nbsp;");
			}
			if(currentQues!=9){
				out.println("<input type='submit' value='Next' name='btn' class='btn' /> &nbsp;&nbsp;");
			} else {
				out.println("<input type='submit' value='Finish' id='finish' formaction='FinishReview' class='btn' /> &nbsp;&nbsp;");
			}
			out.println("</form></body></html>");
			out.close();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		doGet(request, response);
	}
}
