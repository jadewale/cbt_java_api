/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.services;

/**
 * Handles Exam queries
 * @author jolaadeadewale
 */
public class Query {
    public static final String ADD_EXAM =  "INSERT INTO activeexam(examname, "
        + "scheduledatetime, time, status, registeredcandidates, feedback, "
        + "duration, amount) VALUES(? , ? , ? , 0 ,0 , ? , ? , ?);";
    
    public static final String ADD_EXAM_START = "INSERT INTO activeexam(examname,"
        + " scheduledatetime, time, status, "+ "registeredcandidates, "
        + "feedback, duration, amount) VALUES(?, ?, ?, 1, 0, ?, ?, ?);";
    
    public static final String UPDATE_EXAM = "UPDATE activeexam SET scheduledatetime=?, "
        + "time=?, feedback=?, duration=?, amount=? where examName=?";
    
    public static final String UPDATE_EXAM_REGISTERED_STUDENTS = "UPDATE activeexam SET RegisteredCandidates=? "
        + "WHERE ExamName=?";
    
    public static final String GET_EXAMS = "SELECT Subject FROM examstable ORDER "
        + "BY Subject ASC";
    
    public static final String SCHEDULED_EXAMS = "SELECT * FROM activeexam WHERE Status=0 ORDER BY ExamName";
    
    public static final String SCHEDULED_EXAM = "SELECT * FROM activeexam WHERE Status=0 And ExamName=?";
    
    public static final String AUTO_EXAM = "UPDATE activeexam SET Status=1"
        + " WHERE ScheduleDateTime <= CURDATE() AND status=0 AND RegisteredCandidates > 0";
    
    public static final String CHANGE_EXAM_STATE = "UPDATE activeexam SET Status=1"
        + " WHERE ExamName=?";
    
    public static final String DELETE_ACTIVE_EXAM = "DELETE FROM  activeexam "
        + " WHERE ExamName=?";
    
    public static final String REMOVE_EXAM_STUDENT = "DELETE  FROM  examstudenttable "
        + "WHERE ExamName=? ";
    
    public static final String DEACTIVATE_EXAM = "UPDATE activeexam SET Status=0 "
        + " WHERE ExamName=?";
    
    public static final String DELETE_ALL_ACTIVE_EXAM = "DELETE  FROM activeexamstudents";
    
    public static final String DELETE_FROM_EXAMTABLE = "DELETE  FROM examstudenttable  "
        + "WHERE matric = ?";
    
    public static final String REMOVE_EXAM_STATUS = "DELETE FROM examstatus WHERE matric =?";
    
    public static final String ACTIVE_EXAM_NAME = "SELECT * FROM activeexam WHERE ExamName=?";
    
    public static final String COMPLETED_EXAMS = "SELECT * FROM examstatus WHERE Status =3 AND "
        + "examName =?";
    
    public static final String COMPLETED_EXAMS_STATUS = "SELECT * FROM examstatus WHERE "
        + "status =0 AND examName=?";
    
    public static final String MISSED_EXAMS = "SELECT * FROM examstatus WHERE status =0 AND examName=?";
    
    public static final String ALL_MISSED_EXAMS = "SELECT * FROM examstatus WHERE status =0 ";
    
    public static final String ALL_EXAMS = "SELECT Question FROM examstable WHERE Subject =?";
    
    public static final String INSERT_EXAMS = "INSERT INTO examstable  (Subject, Author, Date, Type, Question, tableT)"
        + "  VALUES (?,?,?,?,?,?) ";
    
    public static final String INSERT_ANSWERS = "INSERT INTO answers  (Question, "
        + "Answer, Weight)  VALUES (?,?,?) ";
    
    public static final String INSERT_QUESTIONS = "INSERT INTO examstable  (Subject, Author, Date, "
        + "Type, Question, tableT)  VALUES (?, ?,CURDATE(), ? ,? , ?) ";
    
    public static final String UPDATE_EXAM_QUESTIONS = "UPDATE examstable SET Question= ? "
        + "AND tableT =?   WHERE Subject =?";
    
    public static final String GET_ALL_EXAMS = "SELECT * FROM examstable";
    
    public static final String GET_ALL_EXAMS_SUBJECT = "SELECT * FROM examstable where Subject=?";
    
    public static final String ONGOING_EXAMS = "SELECT * FROM activeexam WHERE Status=1";
    
    public static final String INSERT_INTO_STARTED_STATUS = "INSERT INTO examstatus VALUES(?, ?, ?, ?);";
    
    public static final String REMOVE_ACTIVE_EXAM = "UPDATE activeexam SET Status=0 WHERE ExamName=?";
    
    public static final String GET_COURSE_EXAMS = "SELECT * FROM examstable WHERE type > 4";
    
    public static final String UNREGISTERED_COURSES = "SELECT * FROM activeexam where status = 1 AND ExamName NOT "
        + "IN (select examName from examstudenttable where matric = ? )";
    
    public static final String GET_ALL_EXAMS_COURSEREGISTER = "SELECT * from courseregister where examName =?";
    
    
}
