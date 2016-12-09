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
public class QueryExamDatabase {

    /**
     * Starts query to get all exams scheduled
     *
     * @return A list of Exams
     */
    public ArrayList<ExamBuilder> getSubjectsQuery() {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement examScheduler
                = DatabaseConnection.queryDB(connection, Query.GET_EXAMS, "Get Subject");
                ResultSet result = Utility.execQuery(examScheduler)) {

            ArrayList<ExamBuilder> exams = new ArrayList<>();
            ExamBuilder examsBuilder;
            while (result.next()) {
                examsBuilder = new ExamBuilder();
                examsBuilder.setExamName(Utility.parseString(DatabaseConnection.getData(result,
                        "String", "Subject")));
                exams.add(examsBuilder);
            }
            return exams;
        } catch (SQLException e) {
            LogData.Log(e.getMessage(), "QueryExamDatabase");
        }

        return null;
    }

    public ExamQuestion getSubjectQuestions(String subject) {

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement getResult = DatabaseConnection.queryDB(connection, Query.GET_ALL_EXAMS_SUBJECT,
                "Get Questions of an Exam")) {
            DatabaseConnection.setData(getResult, subject, "String", 1);
            ExamQuestion questions = new ExamQuestion();
            try (ResultSet result = Utility.execQuery(getResult)) {
                while (result.next()) {
                    questions.setQuestion(Utility.parseString(DatabaseConnection.getData(result,
                            "String", "Question")));
                    questions.setHtmlFormat(Utility.parseString(DatabaseConnection.getData(result,
                            "String", "tableT")));
                    questions.setAuthor(Utility.parseString(DatabaseConnection.getData(result,
                            "String", "Author")));
                    questions.setType(Utility.parseString(DatabaseConnection.getData(result,
                            "String", "Type")));
                    questions.setExamName(subject);
                    questions.setTime(Utility.parseString(DatabaseConnection.getData(result,
                            "String", "Date")));

                    return questions;
                }
            }
        } catch (SQLException e) {
            LogData.Log(e.getMessage(), "Query Exam Database");
        }
        return null;
    }

    public ArrayList<ExamQuestion> getAllQuestions() {

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement getResult = DatabaseConnection.queryDB(connection, Query.GET_ALL_EXAMS,
                "Get Questions of an Exam"); ResultSet result = Utility.execQuery(getResult)) {

            ArrayList<ExamQuestion> getAllExams = new ArrayList<>();
            ExamQuestion questions;
            while (result.next()) {
                questions = new ExamQuestion();
                questions.setQuestion(Utility.parseString(DatabaseConnection.getData(result,
                        "String", "Question")));
                questions.setHtmlFormat(Utility.parseString(DatabaseConnection.getData(result,
                        "String", "tableT")));
                questions.setAuthor(Utility.parseString(DatabaseConnection.getData(result,
                        "String", "Author")));
                questions.setType(Utility.parseString(DatabaseConnection.getData(result,
                        "String", "Type")));
                questions.setExamName(Utility.parseString(DatabaseConnection.getData(result,
                        "String", "Subject")));
                questions.setTime(Utility.parseString(DatabaseConnection.getData(result,
                        "String", "Date")));

                getAllExams.add(questions);
            }

            return getAllExams;
        } catch (SQLException e) {
            LogData.Log(e.getMessage(), "Query Exam Database");
        }
        return null;
    }
}
 