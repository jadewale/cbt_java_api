/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.administrator;


import com.starktech.services.DatabaseConnection;
import com.starktech.services.LogData;
import com.starktech.services.QueryAdministrator;
import com.starktech.services.ResponseBuilder;
import com.starktech.services.UserDetails;
import com.starktech.services.Users;
import com.starktech.services.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jolaade Adewale
 */
public class LogInAdministrator {
 
    public ResponseBuilder authenticate(String username, String password) {
        ResponseBuilder getResponse = new ResponseBuilder();
        Users adminUser = null;

        try {
            try (Connection connection = DatabaseConnection.getConnection();
                    PreparedStatement login = DatabaseConnection.queryDB(connection,
                            QueryAdministrator.LOG_IN_ADMIN, "Log Admin user");) {
                DatabaseConnection.setData(login, username,
                        "String", 1);
                DatabaseConnection.setData(login, UserDetails.unHashPassword(password),
                        "String", 2);

                try (ResultSet results = login.executeQuery();) {
                    if (results.next()) {
                        adminUser = new Users();
                        adminUser.setUserName(Utility.parseString(DatabaseConnection.getData(results, "String",
                                "UserName")));
                        adminUser.setFirstName(Utility.parseString(DatabaseConnection.getData(results, "String",
                                "FirstName")));
                        adminUser.setLastName(Utility.parseString(DatabaseConnection.getData(results, "String",
                                "LastName")));
                        adminUser.setEmail(Utility.parseString(DatabaseConnection.getData(results, "String",
                                "Email")));
                        adminUser.setMiddleName(Utility.parseString(DatabaseConnection.getData(results, "String",
                                "MiddleName")));
                        adminUser.setGender(Utility.parseString(DatabaseConnection.getData(results, "String",
                                "Gender")));
                        adminUser.setNumber(Utility.parseString(DatabaseConnection.getData(results, "String",
                                "Number")));
                        adminUser.setAddress(Utility.parseString(DatabaseConnection.getData(results, "String",
                                "Address")));
                        adminUser.setInitials(Utility.parseString(DatabaseConnection.getData(results, "String",
                                "Initials")));
                        adminUser.setAddress2(Utility.parseString(DatabaseConnection.getData(results, "String",
                                "Address2")));
                        adminUser.setCourses(Utility.parseString(DatabaseConnection.getData(results, "String",
                                "Courses")));
                        adminUser.setNumber2(Utility.parseString(DatabaseConnection.getData(results, "String",
                                "Number2")));
                        adminUser.setDescription(Utility.parseString(DatabaseConnection.getData(results, "String",
                                "Description")));
                         
                        getResponse.setData(adminUser);
                        getResponse.setToken("token");
                        getResponse.setMessage("success");

                        return getResponse;
                    }
                }

            }
        } catch (SQLException e) {
            LogData.Log(e.getMessage(), "LogIn state");
        }
        return getResponse;
    }
}
  