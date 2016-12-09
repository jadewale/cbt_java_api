/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.services;

/**
 *
 * @author jolaadeadewale
 */
public class QueryStudents {
    public static final String DELETE_ALL_STUDENTS = "DELETE  FROM newstudenttable  "
        + "WHERE studentnumber = ?";
    
    public static final String DELETE_FROM_ACTIVESTUDENTS = "DELETE  FROM activeexamstudents"
        + "  WHERE matric = ?";
    
    public static final String EDIT_STUDENT_PROFILE = "UPDATE newstudenttable SET FirstName =?, LastName=?, "
        + "MiddleName=?, Gender=? WHERE StudentNumber = ?";
    
    public static final String GET_STUDENT_DATA = "SELECT * from candidateData WHERE matric =?";
    
    public static final String UPDATE_STUDENT = "UPDATE candidateData SET firstName=?, lastName=?, middleName=?, gender=?, "
        + "email=?, mobileNumber=?, address=?, Number2=?, address2=?, comment=? Where matric=?";
    
    public static final String COMPLETED_EXAMS = "SELECT * FROM examstatus WHERE status =3 AND matric =?";
    
    public static final String MISSED_EXAMS = "SELECT * FROM examstatus WHERE status= 0 AND matric =?";
    
    public static final String ALL_MISSED_EXAMS = "SELECT * FROM examstatus WHERE status =0 ";
    
    public static final String ACTIVE_STUDENTS = "SELECT * FROM activeexamstudents where matric = ? And ExamName = ?";
    
    public static final String INSERT_STUDENTS = "INSERT INTO newstudenttable(FirstName, LastName, "
        + "MiddleName, Gender, StudentNumber) VALUES(?, ?, ?, ?, ?);";
    
    public static final String INSERT_STUDENTS_CANDIDATE = "INSERT INTO CourseRegister (matricExam,ExamName) VALUES (?,?)";
    
    public static final String PENDING_EXAM_STUDENTS = "SELECT * FROM examstudenttable INNER JOIN newstudenttable ON "
        + "examstudenttable.matric=newstudenttable.StudentNumber WHERE ExamName=?";
    
    public static final String GET_STUDENT_NAME = "SELECT * FROM newstudenttable WHERE StudentNumber = ?";
    
    public static final String GET_ALL_STUDENTS = "SELECT * FROM newstudenttable";
    
    public static final String GET_ALL_CANDIDATES = "SELECT * FROM candidatedata";
    
    public static final String GET_FROM_CANDIDATES = "SELECT lastname, firstname, email, comment from candidatedata where matric= ?";
    
    public static final String INSERT_INTO_EXAM = "INSERT INTO examstudenttable(matric, ExamName) VALUES (?, ?); ";
    
    public static final String INSERT_INTO_NEW_EXAM = "INSERT INTO examstudenttable(matricExam,ExamName,matric) VALUES (?,?,?)";
    
    public static final String UPDATE_REGISTERED_STUDENTS = "UPDATE activeexam SET RegisteredCandidates=RegisteredCandidates + ? "
        + "WHERE ExamName=?";
    
   
    public static final String UPDATE_REGISTERED_CANDIDATES = "UPDATE activeexam SET RegisteredCandidates=? WHERE ExamName=?";
    
    public static final String STUDENT_EXAM_TOTAL = "SELECT * from examstudenttable where examName =?";
    
    public static final String GET_ALL_REGISTERED = "SELECT * FROM examstudenttable INNER JOIN newstudenttable ON "
        + "examstudenttable.matric=newstudenttable.StudentNumber WHERE ExamName=?";
    
    public static final String GET_EXAM_STUDENTS = "SELECT * FROM examstudenttable ORDER BY matric  ASC";
    
    public static final String INSERT_INTO_ACTIVE_EXAM = "INSERT INTO activeexamstudents "
        + "VALUES(?, ?, ?, ?);";
    
    public static final String GET_STUDENT_EXAMS = "SELECT * FROM newstudenttable WHERE StudentNumber = ? "
        + "ORDER BY StudentNumber ASC";
    
    public static final String GET_UNREGISTERED_STUDENT = "SELECT * FROM newstudenttable  WHERE StudentNumber NOT IN (SELECT matric FROM examstudenttable WHERE ExamName=?)";
    
    public static final String REMOVE_EXAM_STUDENTS = "DELETE  FROM  examstudenttable WHERE ExamName=? ";
    
    public static final String UNREGISTER_STUDENT = "DELETE FROM examstudenttable WHERE matric = ? AND ExamName = ?";
    
    public static final String REDUCE_EXAM_STUDENTS = "UPDATE activeexam SET "
        + "RegisteredCandidates=RegisteredCandidates - 1 WHERE ExamName=?";
    
    public static final String GET_TOTAL_CANDIDATE = "SELECT COUNT(matric) from candidatedata";
    
    public static final String REGISTER_STUDENT = "INSERT INTO candidatedata (email, password, lastName, firstName, "
        + "middleName, institution, mobileNumber, gender, dob, matric) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    
    public static final String REGISTER_NEW_STUDENT = "INSERT INTO candidatedata (lastName, firstName, password, email, "
        + "mobileNumber, number2, matric) VALUES(?, ?, ?, ?, ?, ?, ?);"; 
    
   
    public static final String AUTH_CANDIDATE = "SELECT * FROM candidatedata WHERE email=? "
        + "AND password =? OR matric =? AND password=?";
}
