/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.exams;


import com.starktech.services.DatabaseConnection;
import com.starktech.services.LogData;
import com.starktech.services.Query;
import com.starktech.services.Utility;
import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.jws.WebParam;

/**
 *
 * @author Jolaade Adewale
 */
public class PopulateExamAndResult {
    
    public int  populateDatabase(String subject, String author, int type, String questions, String html) {
        try(Connection connection = DatabaseConnection.getConnection(); PreparedStatement insertExam = 
                DatabaseConnection.queryDB(connection, Query.INSERT_EXAMS, "Insert " + subject +" by author "+author )) {
                
            DatabaseConnection.setData(insertExam, subject, "String", 1);
            DatabaseConnection.setData(insertExam, author, "String", 2);
            DatabaseConnection.setData(insertExam, new Date(), "String", 3);
            DatabaseConnection.setData(insertExam, type, "String", 4);
            DatabaseConnection.setData(insertExam, questions, "String", 5);
            DatabaseConnection.setData(insertExam, html, "String", 6);
            return Utility.execUpdate(insertExam);
        }
        catch(SQLException e) {
            LogData.Log(e.getMessage(), "Populate Exam questions for "+ subject + " set by "+author);
        }
        
        return 0;
    }


    public String result(String maps, String subs) { 
/*
        String[] replace = maps.replaceAll("\\{", "").replaceAll("}", "").split(",");

        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        for (String ss : replace) {
            System.out.println(ss);
            String[] sort = ss.split("=");
            map.put(sort[0], Integer.valueOf(sort[1]));
        }

        try {
            
            getStudentName = DatabaseConnection.queryDB(connection, QueryStudents.GET_STUDENT_NAME);

            System.err.println("it has gotten to the server");
            StringBuilder appender = new StringBuilder();
            int count = 0;
            Set<String> keys = map.keySet();
            for (String ss : keys) {
                System.out.println(ss + " in student");
                String trim = ss.trim();
                count++;
                getStudentName.setString(1, trim);

                result = getStudentName.executeQuery();
                while (result.next()) {
                    appender.append("<tr>").append("<td>").append(count).append("</td><td>").append(ss).append("</td><td>").append(result.getString("LastName")).append(" ").append(result.getString("FirstName")).append("</td><td>").append(map.get(ss)).append("</td>");
                    System.err.println(appender);
                }
            }

            DatabaseReportClass mark = new DatabaseReportClass();
            mark.populateResults();
            DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date d = new Date();
            String dates = date.format(d);

            mark.resultQuery(subs, appender.toString(), map.size(), dates);

            System.err.print("worked");
        } catch (SQLException e) {
            e.printStackTrace();
        } */

        return "sent";
    }

    class Question implements Serializable {

        public Question() {

        }

        public Question(ArrayList Question) {
            this.Question = Question;
        }

        private ArrayList Question;

        public ArrayList getQuestion() {
            return Question;
        }

        public void setQuestion(ArrayList Question) {
            this.Question = Question;
        }

    }

    public void getArrayListFile(@WebParam(name = "name") ArrayList<File> files) {
        for (int c = 0; c < files.size(); c++) {
            System.out.println(files.get(c).getAbsolutePath());
        }
    }


    public int updateDatabase(String subject, String question, String table) {
        /*
        try {
           updateQuestion =  DatabaseConnection.initialiseDatabase(connection, updateQuestion, 
            "Insert exam into tables", "PopulateExamAndResult", Query.UPDATE_EXAM_QUESTIONS);
            updateQuestion.setString(1, question);
            updateQuestion.setString(2, table);
            updateQuestion.setString(3, subject);

            return updateQuestion.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                DatabaseConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
   */
        return 1;
    }

}
