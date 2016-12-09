/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.services;

import java.io.Serializable;

/**
 *
 * @author bola odesile
 */
public class FileStream implements Serializable{
   private String question;
   
    public FileStream(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
 
}
