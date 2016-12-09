/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.exams;


import com.starktech.services.DatabaseConnection;
import com.starktech.services.ExamBuilder;
import com.starktech.services.ExamQuestion;
import com.starktech.services.LogData;
import com.starktech.services.Query;
import com.starktech.services.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jolaade Adewale
 */
public class GetAllQuestionsGetAllPeople {
 
    public ArrayList<ExamQuestion> getExamQuestions(ArrayList<ExamBuilder> allExams) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement getQuestions
                = DatabaseConnection.queryDB(connection, Query.ALL_EXAMS, "Get Questions for all exams")) {
           
            ArrayList<ExamQuestion> examsList = new ArrayList();
            allExams.forEach(exam -> {
                if (Integer.parseInt(exam.getRegisteredCandidates()) > 0) {
                    DatabaseConnection.setData(getQuestions, exam.getExamName(), "String", 1);
                    try (ResultSet result = Utility.execQuery(getQuestions)) {
                        ExamQuestion setQuestion = new ExamQuestion();
                        while (result.next()) {
                            setQuestion.setExamName(exam.getExamName());
                            setQuestion.setAmountOfQuestions(exam.getAmountOfQuestions());
                            setQuestion.setQuestion(Utility.parseString(DatabaseConnection.getData(result, "String", 
                                "Question"))); 
                            setQuestion.setFeedback(exam.getFeedback());
                            examsList.add(setQuestion);
                        }
                    }
                    catch(SQLException e) {
                        LogData.Log(e.getMessage(), "GetALLQuestions");
                    }
                }
               
            });

            return examsList;
            
        } catch (SQLException e) {
            LogData.Log(e.getMessage(), "Populating all Exams");
        }

        return null;
    }

}
