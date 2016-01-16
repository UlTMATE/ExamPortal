/*
 *Online Exam Portal
 *Bodacious Assignment
 *
 *@author Parth Khandelwal
 *
 *Servlet to set-up a question and display
 */
 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

public class Question extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Integer currentQues = (Integer)session.getAttribute("currentQues");
		if(currentQues==null){
			ServletContext context = getServletContext();
			RequestDispatcher reqDis = context.getRequestDispatcher("/");
			reqDis.forward(request, response);
		} else {
			response.setContentType("text/html");
			int quesNum = (int)((ArrayList)session.getAttribute("questionList")).get(currentQues.intValue());
			LinkedList question = Database.getQuestion(quesNum);
			
			
			ArrayList correctAnsList = (ArrayList)session.getAttribute("correctAnsList");
			//System.out.println((correctAnsList==null) + " and " +(currentQues==null));
			if(correctAnsList.size()>currentQues.intValue()){
				correctAnsList.set(currentQues.intValue(), question.get(6));
			} else {
				correctAnsList.add(currentQues.intValue(), question.get(6));
			}
			session.setAttribute("correctAnsList", correctAnsList);
			
			ArrayList userAnsList = (ArrayList)session.getAttribute("userAnsList");
			
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head> <title>Exam</title>"
						+ "<link rel='StyleSheet' type='text/css' href='css/QuestionPage.css' />");
	//System.out.println("ArraySize: "+userAnsList.size()+" Ques-"+currentQues.intValue());

			if(userAnsList.size()>currentQues.intValue()){
				ArrayList userAns = new ArrayList();
				String userStr = userAnsList.get(currentQues.intValue())+"";
				StringTokenizer stroker = new StringTokenizer(userStr);
				stroker = new StringTokenizer(userStr);
				while(stroker.hasMoreTokens()){
					userAns.add(stroker.nextToken());
				}
			
				out.println("<script>");
				out.println("function set(){");
				String currentOption;
				for(int i=0; i<userAns.size(); i++){
					currentOption = userAns.get(i)+"";
					out.println("document.getElementById('" +currentOption+ "op').checked=true;");
				}
				out.println("}");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("function set(){");
				out.println("}");
				out.println("</script>");
			}
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
			out.println("<span id='A'>A.<input type='" +toggleButton+ "' name='option' id='Aop' value='A' class='option'> " +question.get(2)+" </span><br />");
			out.println("<span id='B'>B.<input type='" +toggleButton+ "' name='option' id='Bop' value='B' class='option'> " +question.get(3)+" </span><br />");
			out.println("<span id='C'>C.<input type='" +toggleButton+ "' name='option' id='Cop' value='C' class='option'> " +question.get(4)+" </span><br />");
			out.println("<span id='D'>D.<input type='" +toggleButton+ "' name='option' id='Dop' value='D' class='option'> " +question.get(5)+" </span><br /><br />");
			out.println("<hr />");
			if(currentQues!=0){
				out.println("<input type='submit' value='Previous' name='btn' class='btn' /> &nbsp;&nbsp;");
			} else {
				out.println("<input type='submit' value='Previous' name='btn' class='btn' disabled/> &nbsp;&nbsp;");
			}
			if(currentQues!=9){
				out.println("<input type='submit' value='Next' name='btn' class='btn' /> &nbsp;&nbsp;");
			} else {
				out.println("<input type='submit' value='Finish' id='finish' formaction='Finish' class='btn' /> &nbsp;&nbsp;");
			}
			out.println("</form> </body>");
			out.println("</html>");
			out.close();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		doGet(request, response);
	}
}