/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.candidate;
import com.starktech.services.DatabaseConnection;
import com.starktech.services.LogData;
import com.starktech.services.QueryStudents;
import com.starktech.services.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.starktech.endpoint.ExamResource;

/**
 *
 * @author user
 */
public class CandidateRegisterExam {
 
    public String insert(ArrayList<String> students, String exam) { 
          
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement insertStudent
                = DatabaseConnection.queryDB(connection, QueryStudents.INSERT_INTO_NEW_EXAM, "Inserting students into "
                        + "the database " + students); PreparedStatement setCount
                = DatabaseConnection.queryDB(connection, QueryStudents.STUDENT_EXAM_TOTAL,
                        "Get registered candidates"); PreparedStatement updateCandidates
                = DatabaseConnection.queryDB(connection, QueryStudents.UPDATE_REGISTERED_CANDIDATES,
                        "Update Registered Candidates")) {
 
            DatabaseConnection.setAutoCommit(connection);
            students.forEach(student -> {
                DatabaseConnection.setData(insertStudent, student + "***" + exam, "String", 1);
                DatabaseConnection.setData(insertStudent, exam, "String", 2);
                DatabaseConnection.setData(insertStudent, student, "String", 3);
                Utility.execUpdate(insertStudent);

                DatabaseConnection.setData(setCount, exam, "String", 1);
                try (ResultSet result = Utility.execQuery(setCount)) {
                    int count = 0;
                    while (result.next()) {
                        // TO DO get the count instead of loop
                        ++count;
                    }

                    if (count > 0) {
                        DatabaseConnection.setData(updateCandidates, count, "String", 1);
                    } else {
                        DatabaseConnection.setData(updateCandidates, 1, "String", 1);
                    }

                    DatabaseConnection.setData(updateCandidates, exam, "String", 2);
                    int response = Utility.execUpdate(updateCandidates);
                }
                catch(SQLException e) {
                    LogData.Log(e.getMessage(), "Update Registered Students in StudentResourceRegister");
                }
                ArrayList<String> exams = null;//new ArrayList<>(1);  
                if (ExamResource.registeredCourses != null && ExamResource.registeredCourses.containsKey(student)) {
                    exams = ExamResource.registeredCourses.get(student);
                    exams.add(exam);
                    ExamResource.registeredCourses.put(student, exams); 
                }
            });

            DatabaseConnection.commit(connection);
            
            return "Registered Candidates";  

        } catch (SQLException | ArrayIndexOutOfBoundsException e) {
            LogData.Log(e.getMessage(), "Insert new student");
        }
        
        return null;
    }

    public String insertNewStudentTable(String student, String exam) throws ArrayIndexOutOfBoundsException {
        throw new IllegalStateException("Please reuse method to removed static prepared statement");
        /*
        ResultSet results = null;
        try {
            String[] splitExam = exam.split(",");
            for (String subjectCode : splitExam) {
                String value = "";
                try {
                    System.out.println(" adding " + subjectCode + " matric :" + student);
                    this.setPrimary.setString(1, student + "***" + subjectCode);
                    this.setPrimary.setString(2, subjectCode);
                    this.setPrimary.setString(3, student);
                    this.setPrimary.executeUpdate();
                    this.setCount.setString(1, subjectCode);
                    results = setCount.executeQuery();
                    boolean candidate = false;
                    int count = 0;

                    while (results.next()) {
                        ++count;
                    }
                    System.out.println(" count is " + count + " ");
                    if (candidate == false) {
                        Exam.updateRegisteredCandidates.setInt(1, 1);
                    } else {
                        Exam.updateRegisteredCandidates.setInt(1, count);
                    }
                    Exam.updateRegisteredCandidates.setString(2, subjectCode);
                    Exam.updateRegisteredCandidates.executeUpdate();
                } catch (SQLException sqlException) {
                    DatabaseConnection.rollBack(connection);
                    LogData.Log(sqlException.getMessage(), "Course registeration");
                    sqlException.printStackTrace();
                    String count = new Gson().toJson((Object) "Courses not registered successfully... Please try again");
                    try {
                        if (connection != null) {
                            connection.close();
                        }

                        if (results != null) {
                            results.close();
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return count;
                }
                if (Exam.registeredCourses.containsKey(student)) {
                    StringBuilder builder = new StringBuilder();
                    builder.append((String) Exam.registeredCourses.get(student)).append(",").append(subjectCode);
                    Exam.registeredCourses.put(student, builder.toString());
                } else {
                    Exam.registeredCourses.put(student, subjectCode);
                    Exam.getNewRegisteredStudents = !Exam.getNewRegisteredStudents.equals("") ? Exam.getNewRegisteredStudents + "," + student : student;
                    Exam.registerStudentsExam.setString(1, student);
                    ResultSet getResults2 = Exam.registerStudentsExam.executeQuery();
                    while (getResults2.next()) {
                        StringBuilder builder = new StringBuilder();
                        builder.append("#");
                        builder.append(getResults2.getString("LastName").concat(","));
                        builder.append(getResults2.getString("FirstName").concat(","));
                        builder.append(getResults2.getString("MiddleName").concat(","));
                        builder.append(getResults2.getString("Gender").concat(","));
                        builder.append(getResults2.getString("StudentNumber"));
                        StartUpClassOngoingExams.studentsRegistered.put(getResults2.getString("StudentNumber"), getResults2.getString("LastName") + " " + getResults2.getString("FirstName"));
                        Exam.registeredStudents.put(getResults2.getString("StudentNumber"), builder.toString());
                    }
                }
                DatabaseConnection.commit(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.connection != null) {
                    this.connection.close();
                }
                if (results != null) {
                    results.close();
                }
                DatabaseConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new Gson().toJson((Object) "Courses Succesfully registered"); */
    }
}
