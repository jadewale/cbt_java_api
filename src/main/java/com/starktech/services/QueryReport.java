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
public class QueryReport {
    public static final String ALL_REPORT = "SELECT * FROM report";
    
    public static final String GET_REPORT = "SELECT * FROM report WHERE Subject=? And Date=?";
    
    public static final String GET_AVERAGE = "SELECT AVG(score) from results WHERE courseId =? and YearDate =?";
    
    public static final String GET_COMPLETE_AVG_EXAM = "SELECT COUNT(complete) from results WHERE courseId =? and complete = 0";
    
    public static final String GET_DISTINCT_COLUMN = "SELECT DISTINCT dateYear FROM results WHERE courseId=?";
     
    public static final String GET_TOTAL_CANDIDATE = "SELECT COUNT(matric) from results WHERE courseId=?";
            
    public static final String GET_PASS_RATE = "SELECT COUNT(score) from results WHERE score > 50 and courseId=?";
    
    public static final String GET_MAX_GRADES = "SELECT * from results  WHERE courseId =? ORDER BY score DESC, time ASC limit 5;";
    
    public static final String SUBJECT_REPORT = "SELECT * FROM report WHERE Subject=? And Date=?";
    
    public static final String RESULT_SHEET = "Select * from mark where Exam= ? ";
    
    public static final String GET_RESULT_DESC = "SELECT * from results where matric =? ORDER BY dateyear DESC";
    
    public static final String GET_RANKING = "SELECT matric from results where courseId = ? ORDER BY score DESC, time DESC";
    
    public static final String GET_LEADERBOARD_SUBJECT = "SELECT matric, score, time, dateYear from results where courseId = ? "
        + "ORDER BY score DESC, time ASC  ";
}
