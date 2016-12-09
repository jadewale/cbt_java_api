/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.services;

import java.util.ArrayList;

/**
 *
 * @author jolaadeadewale
 */
public class ExamBuilder {
    
    private String examName;
    private String scheduleDateTime;
    private String time;
    private String registeredCandidates;
    private String duration; 
    private String amountOfQuestions; 
    private String feedback;

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getAmountOfQuestions() {
        return amountOfQuestions;
    }

    public void setAmountOfQuestions(String amountOfQuestions) {
        this.amountOfQuestions = amountOfQuestions;
    }

    public ArrayList<ExamQuestion> getExamQuestions() {
        return examQuestions;
    }
  
    public void setExamQuestions(ArrayList<ExamQuestion> examQuestions) {
        this.examQuestions = examQuestions;
    }
    private ArrayList<ExamQuestion> examQuestions; 

    public String getExamName() {
        return examName;
    }
 
    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getScheduleDateTime() {
        return scheduleDateTime;
    }

    public void setScheduleDateTime(String scheduleDateTime) {
        this.scheduleDateTime = scheduleDateTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRegisteredCandidates() {
        return registeredCandidates;
    }

    public void setRegisteredCandidates(String registeredCandidates) {
        this.registeredCandidates = registeredCandidates;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
            
}
