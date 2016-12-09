/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.endpoint;

import com.starktech.exams.AddNewExam;
import com.starktech.exams.PopulateExamAndResult;
import com.starktech.exams.QueryExamDatabase;
import com.starktech.exams.StartUpClassOngoingExams;
import com.starktech.services.Utility;
import com.starktech.reports.Report;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author jolaadeadewale
 */
@Path("exam")
public class ExamResource {

    @Context
    private UriInfo context; 
    public static String getNewRegisteredStudents; // Used in start up ongoing class 
    public static HashMap<String, ArrayList<String>> registeredCourses = null; // Used in start up ongoing class also used in student verification
    public static HashMap<String, String[]> registeredStudents = new HashMap<>(); // Used in start up ongoing class also used in student verification


    /**
     * Creates a new instance of ExamResource
     */
    public ExamResource() {
    }

    /**
     * Retrieves representation of an instance of com.starktech.endpoint.ExamResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Path("results")
    @Produces("application/json")
    public String report() {
        Report rep = new Report();
        return Utility.response(rep.sendReport());  
    }
    
    @GET
    @Path("exams")
    @Produces("application/json")
    public String getonGoingExamMethods() {
        StartUpClassOngoingExams start = new StartUpClassOngoingExams();
        return Utility.response(start.manageOngoingExam());
    }
    
    @POST
    @Path("exams")
    @Produces("application/json")
    public String createExam(String jsonObj) {
        if (Utility.isNotNull(jsonObj)) {
            String[] examData = Utility.JsonParse(jsonObj, new String[]{"subject",
                "author", "question", "html", "type"}, 5);
            PopulateExamAndResult populateExams = new PopulateExamAndResult(); 
            if (Utility.isNotNull(examData[0]) && Utility.isNotNull(examData[1])
                    && Utility.isNotNull(examData[2]) && Utility.isNotNull(examData[3])
                    && Utility.isNotNull(examData[4])) {
                return Utility.response(populateExams.populateDatabase(examData[0], examData[1],
                        Integer.parseInt(examData[4]), examData[2], examData[3]));
            }
        }
    
        return Utility.response(false);
    }
    
    @POST
    @Consumes("application/json")
    @Path("schedule")
    public String scheduleExam(String json, @QueryParam("status") int status) {
 
        if (Utility.isNotNull(json)) {
            String[] examData = Utility.JsonParse(json, new String[]{"subjectName",
                "examDate", "examTime", "duration", "amountOfQuestions", "feedback"}, 6);
            AddNewExam addExam = new AddNewExam();
            if (Utility.isNotNull(examData[0]) && Utility.isNotNull(examData[1])
                    && Utility.isNotNull(examData[2]) && Utility.isNotNull(examData[3])
                    && Utility.isNotNull(examData[4])) {
                switch (status) {
                    case 1:
                        return Utility.response(addExam.insertExam(examData[0], examData[1], examData[2],
                                examData[3], examData[4], examData[5]));
                   // default:
                       // addExam.insertExam();
                       // return addExam.runInsertExamQuery(examData[0], examData[1], examData[2],
                         //       examData[3], examData[4], examData[5]);
                }  
            }
        }

        return Utility.response(false);
    }
    
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("subjects")  
    public String queryExam(@QueryParam("status") int status, @QueryParam("subject") String subject) {
        // To DO : This method is exposed verify an entry at least
        QueryExamDatabase exams = new QueryExamDatabase();  
        switch (status) {
            case 1:
                return Utility.response(exams.getSubjectsQuery());
            case 2:
                return Utility.response(exams.getSubjectQuestions(subject));
            default:
                return Utility.response(exams.getAllQuestions());
        }
    }

 

    /**
     * PUT method for updating or creating an instance of ExamResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
