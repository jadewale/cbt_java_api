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
public class UserExam extends Users {
        
    private ArrayList<String> exams;
 
    public ArrayList<String> getExams() {
        return exams;
    }

    public void setExams(ArrayList<String> exams) {
        this.exams = exams;
    }
    
}
