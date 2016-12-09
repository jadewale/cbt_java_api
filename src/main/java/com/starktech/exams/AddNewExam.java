/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.exams;


import com.starktech.services.DatabaseConnection;
import com.starktech.services.LogData;
import com.starktech.services.Query;
import com.starktech.services.ResponseBuilder;
import com.starktech.services.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Adds and updates exams
 *
 * @author Jolaade Adewale
 */
public class AddNewExam {

    /**
     * Inserts exam not started into the database returns {void}
     */
    public void insertExam() {
        throw new IllegalStateException("Please review");
        /*
        Exam.insertActiveExam = DatabaseConnection.initialiseDatabase(connection, Exam.insertActiveExam, 
            "inserting exam ", "AddNewExam", Query.ADD_EXAM); */
    }

    /**
     * @param subject
     * @param examDate
     * @param examTime
     * @param duration
     * @param amountOfQuestions
     * @param feedback Inserts exam that have started into the database returns
     * {void}
     * @return 
     */
    public ResponseBuilder insertExam(String subject, String examDate, String examTime, String duration,
            String amountOfQuestions, String feedback) {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement insertExam
                = DatabaseConnection.queryDB(connection, Query.ADD_EXAM_START, "Adding " + subject + " Into started Exams")) {
            ResponseBuilder getResponse = new ResponseBuilder(); 
            DatabaseConnection.setData(insertExam, subject,
                    "String", 1);
            DatabaseConnection.setData(insertExam, examDate,
                    "Date", 2);
            DatabaseConnection.setData(insertExam, examTime,
                    "Time", 3);
            DatabaseConnection.setData(insertExam, feedback,
                    "String", 4);
            DatabaseConnection.setData(insertExam, duration,
                    "Short", 5);
            DatabaseConnection.setData(insertExam, amountOfQuestions,
                    "Int", 6);
            int result = Utility.execUpdate(insertExam);  
            if(result >= 0) {
                getResponse.setMessage("Exam was created Successfully");
            }
            else{
                getResponse.setMessage("Error"); 
            }
            
            return getResponse;
        } catch (SQLException | NumberFormatException e) {
            LogData.Log(e.getMessage(), "InsertExam");
        }
        
        return null;
    } 

    /**
     * Update exam in the database returns {void}
     */
    public void updateAddNewExam() {
        throw new IllegalStateException("Please review");
        /*
        Exam.updateAddNewExamInDb = DatabaseConnection.initialiseDatabase(connection, Exam.updateAddNewExamInDb, 
            "update new exam", "AddNewExam", Query.UPDATE_EXAM); */
    }

    /**
     * Updates exam data
     *
     * @param question question added to database
     * @param scheduleDate date exam starts
     * @param time time exam starts
     * @param duration time exam lasts
     * @param feedback feedback after exam
     * @param amount amount of questions to be populated
     * @return {String} status of the exam
     */
    public String returnUpdateQuery(String question,
            String scheduleDate, String time, String duration,
            String feedback, String amount) {
        /*
        try{         
            DatabaseConnection.setData(Exam.updateAddNewExamInDb, scheduleDate, 
                "Date", 1);
            DatabaseConnection.setData(Exam.updateAddNewExamInDb, time, 
                "Time", 2);
            DatabaseConnection.setData(Exam.updateAddNewExamInDb, feedback, 
                "String", 3);
            DatabaseConnection.setData(Exam.updateAddNewExamInDb, duration, 
                "Short", 4);
            DatabaseConnection.setData(Exam.updateAddNewExamInDb, amount, 
                "Int", 5);
            DatabaseConnection.setData(Exam.updateAddNewExamInDb, question, 
                "String", 6);     
            int result =  Utility.execUpdate(Exam.updateAddNewExamInDb);
       
            if(result >= 0) {
                LogData.Log(question + " successfull updated", "AddNewExam");
                
                return new Gson().toJson( question + "was successfull updated "
                    + ".... view added exams in schedule exams page");
            }
            else{
                LogData.Log(question + " had an error ", "AddNewExam");
                
                return new Gson().toJson("Error...please reschedule exam");
            }
             
        }
        catch(NumberFormatException e) {
            LogData.Log(e.getMessage(), "AddNewExam");
            
            return new Gson().toJson("Error " + e.getMessage());
        }
            
        finally {
            try{
                if(Utility.isNotNull(connection)) {
                    connection.close();
                }
                DatabaseConnection.closeConnection();
             }
            catch(SQLException e){
                LogData.Log(e.getMessage(), "AddNewExam ");
            }
        }
         */
        return null;

    }

    /**
     * Updates exam data advanced
     *
     * @param question question added to database
     * @param scheduleDate date exam starts
     * @param time time exam starts
     * @param duration time exam lasts
     * @param feedback feedback after exam
     * @param amount amount of questions to be populated
     * @return {String} status of the exam
     */
    public String runInsertExamQueryAdvanced(String question,
            String scheduleDate, String time, String duration, String feedback,
            String amount) {
        /*
        try {
            DatabaseConnection.setData(Exam.insertActiveExam, question, 
                "String", 1);
            DatabaseConnection.setData(Exam.insertActiveExam, scheduleDate, 
                "Date", 2);
            DatabaseConnection.setData(Exam.insertActiveExam, time, 
                "Time", 3);
            DatabaseConnection.setData(Exam.insertActiveExam, feedback, 
                "String", 4);
            DatabaseConnection.setData(Exam.insertActiveExam, duration, 
                "Short", 5);
            DatabaseConnection.setData(Exam.insertActiveExam, amount, 
                "Int", 6); 
            int result =  Utility.execUpdate(Exam.insertActiveExam);
            System.out.println("error"+ result);
            
            if(result >= 0){
                LogData.Log(question + " inserted true .", "AddNewExam");
                
                return new Gson().toJson( question + " inserted true .");
            }
            else{
                LogData.Log(question + " error ... reschedule", "AddNewExam");
                
                return new Gson().toJson("Error...please reschedule exam");
            }  
        }
        catch(NumberFormatException e) {
            LogData.Log(e.getMessage(), "AddNewExam");
            
            return new Gson().toJson("Error: " +e.getMessage());
        }
        finally{
            if(Utility.isNotNull(connection)) {
                Utility.endConnection(connection);
            }
            
            DatabaseConnection.closeConnection();
        }
         */

        return null;

    }

    /**
     * Schedules exam from excel sheet enhanced for loop through exams keys then
     * check if lookup not equal to null and length greater than 1 then fill
     * exam data
     * takes in an excel scheduler 
     * @param exams
     * @return {String} empty string
     */ 
    public String runInsertExamQuery(String test) {
        /*
        Set<String> keys = exams.keySet();
        keys.stream().filter((lookUp) -> !(lookUp == null) || !(lookUp.length() 
                < 1)).forEachOrdered((lookUp) -> {
            String exam = exams.get(lookUp).getExamName();
            String examDate = exams.get(lookUp).getExamDate();
            String examTime = exams.get(lookUp).getExamTime();
            String durationMinutes = exams.get(lookUp).getDurationInMinutes();
            String amountOfQuestions = exams.get(lookUp).getAmountOfQuestions();
            String feedBack = exams.get(lookUp).getFeedBack();
            
            DatabaseConnection.setData(Exam.insertActiveExam, exam, 
                "String", 1);
            DatabaseConnection.setData(Exam.insertActiveExam, examDate, 
                "String", 2);
            DatabaseConnection.setData(Exam.insertActiveExam, feedBack, 
                "String", 3);
            DatabaseConnection.setData(Exam.insertActiveExam, examTime, 
                "Short", 4);
            DatabaseConnection.setData(Exam.insertActiveExam, amountOfQuestions, 
                "Int", 5);                 
            Utility.execUpdate(Exam.insertActiveExam);               
            if(Utility.isNotNull(connection)) {
                Utility.endConnection(connection);
            }
            DatabaseConnection.closeConnection();
        });
         */
        return "success";
    }
}
