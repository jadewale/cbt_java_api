/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.websocket;

import com.starktech.endpoint.ExamResource;
import com.starktech.exams.StartUpClassOngoingExams;
import com.starktech.services.DatabaseConnection;
import com.starktech.services.QueryStudents;
import com.starktech.services.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jolaadeadewale
 */
public class SocketHelper {
    
    public static void getStudents() {
        try(Connection connection = DatabaseConnection.getConnection();
                PreparedStatement getAllRegisteredStudents
                    = DatabaseConnection.queryDB(connection, QueryStudents.GET_EXAM_STUDENTS,
                            "Get All Exam candidates");ResultSet resultSet1 = Utility.execQuery(getAllRegisteredStudents);
                    PreparedStatement examRegister = DatabaseConnection.queryDB(connection, QueryStudents.GET_ALL_STUDENTS, "All Students")) {
            
            StartUpClassOngoingExams getExams = new StartUpClassOngoingExams();
            getExams.getExamCandidates(resultSet1, examRegister);
            
        }catch(SQLException e) {
            e.printStackTrace();
        }
            
    }
}
