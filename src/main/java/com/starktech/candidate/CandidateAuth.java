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
import com.starktech.services.Users;
import com.starktech.services.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import com.starktech.endpoint.ExamResource;


/**
 *
 * @author user
 */
public class CandidateAuth {

    public ResponseBuilder authenticateUser(String matric, String password) {
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement authUser = DatabaseConnection.queryDB(connection, 
                    QueryStudents.AUTH_CANDIDATE, "Candidate log in with I.D "+ matric)) {

            DatabaseConnection.setData(authUser, matric, "String", 1);
            DatabaseConnection.setData(authUser, password, "String", 2);
            DatabaseConnection.setData(authUser, matric, "String", 3);
            DatabaseConnection.setData(authUser, password, "String", 4);
            ResponseBuilder getResponse;
            try (ResultSet results = Utility.execQuery(authUser)) {
                getResponse = new ResponseBuilder();
                while (results.next()) {
                    Users candidate = new Users();
                    String id = Utility.parseString(DatabaseConnection.getData(results, "String",
                            "matric"));
                    candidate.setUserName(id);
                    candidate.setFirstName(Utility.parseString(DatabaseConnection.getData(results, "String",
                            "firstName")));
                    candidate.setLastName(Utility.parseString(DatabaseConnection.getData(results, "String",
                            "lastName")));
                    candidate.setEmail(Utility.parseString(DatabaseConnection.getData(results, "String",
                            "email")));
                    candidate.setMiddleName(Utility.parseString(DatabaseConnection.getData(results, "String",
                            "middleName")));
                    candidate.setGender(Utility.parseString(DatabaseConnection.getData(results, "String",
                            "gender")));
                    candidate.setNumber(Utility.parseString(DatabaseConnection.getData(results, "String",
                            "mobileNumber")));
                    candidate.setAddress(Utility.parseString(DatabaseConnection.getData(results, "String",
                            "Address")));
                    candidate.setInitials(Utility.parseString(DatabaseConnection.getData(results, "String",
                            "institution")));
                    candidate.setAddress2(Utility.parseString(DatabaseConnection.getData(results, "String",
                            "Address2")));
                    candidate.setNumber2(Utility.parseString(DatabaseConnection.getData(results, "String",
                            "Number2")));
                    candidate.setDescription(Utility.parseString(DatabaseConnection.getData(results, "String",
                            "Comment")));
                    if(Utility.isNotNull(ExamResource.registeredCourses)  && 
                            ExamResource.registeredCourses.containsKey(id)) {
                        candidate.setCourseList(ExamResource.registeredCourses.get(id));
                    }   
                    
                    getResponse.setData(candidate);
                    getResponse.setToken("token");
                    getResponse.setMessage("success");

                    return getResponse;
                }
            }

        } catch (SQLException e) {
            LogData.Log(e.getMessage(), "Candidate log in id is " + matric);
        }

        return null;
    }
}
