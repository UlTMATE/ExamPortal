/*
 *Online Exam Portal
 *Bodacious Assignment
 *
 *@author Parth Khandelwal
 *
 *Database Creation and Interaction
 */
 
import java.sql.*;
import java.util.*;

class Database {
	
//	public Database() {
//		try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/?", "root", "root");
//            Statement stmt = conn.createStatement();
//            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS examSimulator");
//            stmt.close();
//            conn.close();
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/examSimulator", "root", "root");
//            stmt = conn.createStatement();
//            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS questions(Qno int AUTO_INCREMENT, question varchar(100), A varchar(50), B varchar(50), "
//                    + "C varchar(50), D varchar(50), answer varchar(10), multiple varchar(10), CHECK (multiple in('true','false')), PRIMARY KEY (QNo))");
//            stmt.close();
//            stmt = conn.createStatement();
//            ResultSet rst = stmt.executeQuery("Select * from questions");
//            if (!rst.next()) {
//                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO questions(question, A, B, C, D, answer, multiple) values(?,?,?,?,?,?,?)");
//                for (int i = 1; i <= 100; i++) {
//                    pstmt.setString(1, "Answer of question " + i + " is ?");
//                    pstmt.setString(2, "Choose Me");
//                    pstmt.setString(3, "What About Me");
//                    pstmt.setString(4, "Don't Leave Me");
//                    pstmt.setString(5, "Not Selecting Me?");
//                    if (i % 2 == 0) {
//                        pstmt.setString(6, "A C");
//                        pstmt.setString(7, "true");
//                    } else {
//                        pstmt.setString(6, "D");
//                        pstmt.setString(7, "false");
//                    }
//                    pstmt.executeUpdate();
//                }
//                pstmt.close();
//                pstmt = conn.prepareStatement("UPDATE questions set answer=?, multiple=? where Qno=?");
//                Random rand = new Random();
//                for (int i = 0; i < 20; i++) {
//                    int qNum = rand.nextInt(101);
//                    if (qNum != 0) {
//                        pstmt.setString(1, "B");
//                        pstmt.setString(2, "false");
//                        pstmt.setInt(3, qNum);
//                        pstmt.executeUpdate();
//                    }
//                }
//                for (int i = 0; i < 20; i++) {
//                    int qNum = rand.nextInt(101);
//                    if (qNum != 0) {
//                        pstmt.setString(1, "B D");
//                        pstmt.setString(2, "true");
//                        pstmt.setInt(3, qNum);
//                        pstmt.executeUpdate();
//                    }
//                }
//            }
//            stmt.close();
//            conn.close();
//        } catch (SQLException | ClassNotFoundException excep) {
//            excep.printStackTrace();
//        }
//	}
//	
	public static LinkedList getQuestion(int quesNum) {
		LinkedList linkList = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/examSimulator", "root", "root");
            Statement stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery("SELECT * FROM questions WHERE QNo=" +quesNum);) {
            	rst.next();
			linkList = new LinkedList();
			linkList.add(rst.getInt("QNo"));
			linkList.add(rst.getString("question"));
			linkList.add(rst.getString("A"));
			linkList.add(rst.getString("B"));
			linkList.add(rst.getString("C"));
			linkList.add(rst.getString("D"));
			linkList.add(rst.getString("answer"));
			linkList.add(rst.getString("multiple"));
		} catch (SQLException sqlExc){
			sqlExc.printStackTrace();
		}
		return linkList;
	}
}