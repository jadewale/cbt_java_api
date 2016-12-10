/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.websocket;

import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import com.starktech.endpoint.ExamResource;  
import com.starktech.services.UserExam;
import com.starktech.services.Utility;
import java.util.HashMap;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;

/**
 *
 * @author jolaadeadewale
 */
@ServerEndpoint("/endpointResource")
public class NewWSEndpoint1 {

    static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();

    @OnMessage
    public String onMessage(String message) {
        return null;
    }

    @OnOpen
    public String openConnection(Session session) {
        System.out.println("a new connection has been established ");
        if (session.getQueryString().contains("Administrator")) {
            session.getUserProperties().put(session.getQueryString()
                    .split("Administrator=")[0], "Administrator");
            addQueue(session);
            this.getStudentNames();
        }
        return null;
    }

    public void adminUpdater() {
    }

    @OnClose
    public String closedConnection(Session session) {

        System.out.println(session.getUserProperties().values());
        Set key = session.getUserProperties().keySet();
        if (key.toString().equals("Supervisor") || key.toString().equals("Administrator")) {
            queue.remove(session);
            return null;
        }
        queue.remove(session);

        return null;
    }

    @OnError
    public String error(Session session, Throwable t) {
        queue.remove(session);
        t.printStackTrace();

        return null;
    }

    public void getStudentNames() {

        HashMap<String, UserExam> candidateResource = new HashMap<>();
        Set<String> set = ExamResource.registeredCourses.keySet();
        for(String key: set) { 
            UserExam userExam = new UserExam();
            userExam.setLastName(ExamResource.registeredStudents.get(key)[0]);
            userExam.setFirstName(ExamResource.registeredStudents.get(key)[1]);
            userExam.setMiddleName(ExamResource.registeredStudents.get(key)[2]);
            userExam.setGender(ExamResource.registeredStudents.get(key)[3]);
            userExam.setUsername(ExamResource.registeredStudents.get(key)[4]);
            if (ExamResource.registeredCourses.containsKey(key)) {
                userExam.setExams(ExamResource.registeredCourses.get(key));
            }
            candidateResource.put(key, userExam);
        } 
        
        
        /*
        ExamResource.registeredStudents.keySet().forEach(key -> {
            UserExam userExam = new UserExam();
            userExam.setLastName(ExamResource.registeredStudents.get(key)[0]);
            userExam.setFirstName(ExamResource.registeredStudents.get(key)[1]);
            userExam.setMiddleName(ExamResource.registeredStudents.get(key)[2]);
            userExam.setGender(ExamResource.registeredStudents.get(key)[3]);
            userExam.setUsername(ExamResource.registeredStudents.get(key)[4]);
            if (ExamResource.registeredCourses.containsKey(key)) {
                userExam.setExams(ExamResource.registeredCourses.get(key));
            }
            candidateResource.put(key, userExam);
        });
         */ 
         
       for(Session session : queue) {
           if(session.getUserProperties().containsValue("Administrator")) {
               if(session.isOpen()) {
                   session.getAsyncRemote().sendObject(Utility.response(candidateResource));
               }
           }
       }
                
    }

    private void addQueue(Session session) {
        queue.add(session);
    }
}
