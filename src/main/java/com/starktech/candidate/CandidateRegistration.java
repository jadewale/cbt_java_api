/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.candidate;


import com.starktech.services.DatabaseConnection;
import com.starktech.services.LogData;
import com.starktech.services.QueryStudents;
import com.starktech.services.ResponseBuilder;
import com.starktech.services.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.mail.MessagingException;

/**
 *
 * @author user
 */
public class CandidateRegistration {

    public ResponseBuilder startRegistration(String fullName, String password, String email, 
            String number, String amount) {

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement registration
                = DatabaseConnection.queryDB(connection, QueryStudents.REGISTER_NEW_STUDENT, "Register Candidate " + fullName);
                PreparedStatement getStudentCount = DatabaseConnection.queryDB(connection,
                        QueryStudents.GET_TOTAL_CANDIDATE, "Get Total Count"); ResultSet results = Utility.execQuery(getStudentCount)) {
            DatabaseConnection.setAutoCommit(connection);
            ResponseBuilder response = new ResponseBuilder();
            int count = 0;
            while (results.next()) {
                count = results.getInt(1);
            }
            
            String firstName = fullName.split(" ")[0];
            String lastName = fullName.split(" ")[1];
            String matric = String.format("%07d", ++count);
            DatabaseConnection.setData(registration, firstName, "String", 1);
            DatabaseConnection.setData(registration, lastName, "String", 2);
            DatabaseConnection.setData(registration, password, "String", 3);
            DatabaseConnection.setData(registration, email, "String", 4);
            DatabaseConnection.setData(registration, number, "String", 5);
            DatabaseConnection.setData(registration, amount, "String", 6);
            DatabaseConnection.setData(registration, matric, "String", 7);

            int result = Utility.execUpdate(registration);
            if (result == 0 || result > 0) {
                InsertStudentsDatabaseQueryXcelSheet insert = new InsertStudentsDatabaseQueryXcelSheet();
                result = insert.insert(firstName, lastName, "Test", "M", matric);
                if (result >= 0) {
                    DatabaseConnection.commit(connection);
                    try {
                        
                        CandidateMailSender.mailsend(matric, email, "Sts Registration success", "Your Log In Id is ");
                    } catch (MessagingException e) {
                        LogData.Log(e.getMessage(), "Email failed");
                    }
                     
                    response.setMessage("Registration Successfull");
                    response.setData(matric);
                    response.setToken("Token");
                    return response;
                }
            }
        } catch (SQLException | ArrayIndexOutOfBoundsException e) { 
            LogData.Log(e.getMessage(), "Registering candidate " + fullName);
        }
        return null;
    }

}
