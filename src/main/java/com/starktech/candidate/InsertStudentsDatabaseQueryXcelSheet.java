/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.candidate;


import com.starktech.services.DatabaseConnection;
import com.starktech.services.LogData;
import com.starktech.services.QueryStudents;
import com.starktech.services.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
/**
 *
 * @author Jolaade Adewale
 */
public class InsertStudentsDatabaseQueryXcelSheet {

    public int insert(String first, String last, String middle, String gender, 
            String matric) { 
        try(Connection connection = DatabaseConnection.getConnection(); PreparedStatement register = 
                DatabaseConnection.queryDB(connection, QueryStudents.INSERT_STUDENTS, "Inserting " + matric)) {
            
             DatabaseConnection.setData(register, first, "String", 1);
             DatabaseConnection.setData(register, last, "String", 2);
             DatabaseConnection.setData(register, middle, "String", 3);
             DatabaseConnection.setData(register, gender, "String", 4);
             DatabaseConnection.setData(register, matric, "String", 5);
             
             int result = Utility.execUpdate(register);
             
             return result;
        }
        catch(SQLException e) {
            LogData.Log(e.getMessage(), " Create candidate " + matric);
        }
        
        return 0;
    }

    /*public String excelSheetInsertExam(HashMap<String, StudentsObject> map, String exam) {
        StringBuilder totalStudents = new StringBuilder();
        System.out.println("insert db " + map.size());
        Set<String> keys = map.keySet();
        for (String lookUp : keys) {
            if (lookUp == null) {
                continue;
            }

            if (lookUp.length() < 1) {
                continue;
            }

            try {
                double n = Double.parseDouble(lookUp);
            } catch (NumberFormatException e) {

                continue;
            }

            totalStudents.append(map.get(lookUp).getStudentNumber()).append(",");
        }
        RegisterExamStudents register = new RegisterExamStudents();
        register.insertNewStudentsTable();
        return register.insertNewStudentsTableQuery(totalStudents.toString(), exam);
    } */

}
