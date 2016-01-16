/*
 *Online Exam Portal
 *Bodacious Assignment
 *
 *@author Parth Khandelwal
 *
 *Servlet to set-up and start the exam
 */
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

public class StartExam extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Object obj = session.getAttribute("questionList");
		if(obj == null){
			ArrayList quesNums = new ArrayList();
			Random rand = new Random();
			while(quesNums.size()!=10){
        	    int num = rand.nextInt(101);
            	if(!quesNums.contains(num) & num!=0) {
                	quesNums.add(num);
	            }
    	    }
			session.setAttribute("questionList", quesNums);
			session.setAttribute("currentQues", 0);
			session.setAttribute("correctAnsList", new ArrayList(10));
			session.setAttribute("userAnsList", new ArrayList(10));
		} else {
			ServletContext context = getServletContext();
			RequestDispatcher reqDis = context.getRequestDispatcher("/Question");

			reqDis.forward(request, response);
		}
//		response.setContentType("text/html");
//		try(PrintWriter out = response.getWriter();){
//			out.println("<html>");
//			out.println("<head>");
//			out.println("<title>Work</title>");
//			out.println("<body>hello</body>");
//			out.println("</head>");
//			out.println("</html>");
//		} catch (IOException ioExc){
//			ioExc.printStackTrace();
//		}
		try{
			ServletContext context = getServletContext();
			RequestDispatcher reqDis = context.getRequestDispatcher("/Question");
			reqDis.forward(request, response);
		} catch (ServletException | IOException serveExc){
			serveExc.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	
//	public ArrayList getQuestionNumbers() {
//		ArrayList quesNums = new ArrayList();
//		Random rand = new Random();
//		while(quesNums.size()!=10){
//            int num = rand.nextInt(101);
//            if(!quesNums.contains(num+"") & num!=0) {
//                quesNums.add(num+"");
//            }
//        }
//        return quesNums;
//	}
}