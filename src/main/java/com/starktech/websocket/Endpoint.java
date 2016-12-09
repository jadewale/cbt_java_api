/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.websocket;

import com.starktech.services.UserExam; 
import com.starktech.services.Utility;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import com.starktech.endpoint.ExamResource;

/**
 *
 * @author Jolaade Adewale
 */
@ServerEndpoint("/endpoint") 
public class Endpoint {

    private static final Logger logger = Logger.getLogger("end");
    static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();

    public void getStudentNames() {
        
        HashMap<String, UserExam> candidateResource = new HashMap<>();
        
        ExamResource.registeredStudents.keySet().forEach( key -> { 
             UserExam userExam = new UserExam();
            userExam.setLastName(ExamResource.registeredStudents.get(key)[0]);
            userExam.setFirstName(ExamResource.registeredStudents.get(key)[1]);
            userExam.setMiddleName(ExamResource.registeredStudents.get(key)[2]);
            userExam.setGender(ExamResource.registeredStudents.get(key)[3]);
            userExam.setUsername(ExamResource.registeredStudents.get(key)[4]);
            if(ExamResource.registeredCourses.containsKey(key)) {
                userExam.setExams(ExamResource.registeredCourses.get(key));
            }
            candidateResource.put(key, userExam); 
        });
        
        queue.stream().filter( ss -> 
                 ss.getUserProperties().containsValue("Administrator"))
                .filter(ss -> ss.isOpen()).forEach(ss -> {
                    ss.getAsyncRemote().sendObject(Utility.response(candidateResource)); 
                 }); 
    } 

    private void addQueue(Session session) {
        queue.add(session);
    }

    @OnOpen
    public void openConnection(Session session) {
        System.out.println("a new connection has been established ");
        if (session.getQueryString().contains("Administrator")) {
            session.getUserProperties().put(session.getQueryString()
                    .split("Administrator=")[0], "Administrator");
            addQueue(session);
            this.getStudentNames();
        }
    }

    public void adminUpdater() {
    }

    @OnClose
    public void closedConnection(Session session) {
        System.out.println("connection clossed properly");
       
        System.out.println(session.getUserProperties().values());
        Set key = session.getUserProperties().keySet();
        if (key.toString().equals("Supervisor") || key.toString().equals("Administrator")) {
            queue.remove(session);
            return;
        }
        queue.remove(session);
    }

    @OnError
    public void error(Session session, Throwable t) {
        queue.remove((Object) session);
        t.printStackTrace();
        logger.log(Level.INFO, "Connection error.");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @OnMessage
    public void messageRecieved(Session session, String msg) {
           
    }

    @OnMessage
    public void binaryMessage(Session session, ByteBuffer msg) {
        System.out.println("Binary message: " + msg.toString());
    }


    class Name {

        private String lastName;
        private String firstName;
        private String matric;

        Name() {
        }

        public String getLastName() {
            return this.lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return this.firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getMatric() {
            return this.matric;
        }

        public void setMatric(String matric) {
            this.matric = matric;
        }
    }
}
  