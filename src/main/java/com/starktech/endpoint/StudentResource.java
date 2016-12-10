/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.endpoint;

import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author jolaadeadewale
 */
@Path("student")
public class StudentResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of StudentResource
     */
    public StudentResource() {
    }

    /**
     * Retrieves representation of an instance of com.starktech.endpoint.StudentResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return ""; 
    }

    /**
     * PUT method for updating or creating an instance of StudentResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    } 
    
    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("course/information")
    public String course(@QueryParam("status") int status, @QueryParam("subjects") String subjects, @QueryParam("matric") String matric) {
        System.out.println("Returning Subjects registered on exam table");

        if (status == 2) {
            StudentResourceGetAllSubjects getSubjects = new StudentResourceGetAllSubjects();
            return new Gson().toJson((Object) getSubjects.getReturningStudentSubjects(matric));
        }
        if (status == 3) {
            StudentResourceEditUpdate edit = new StudentResourceEditUpdate();
            return new Gson().toJson((Object) edit.edit(matric));
        }
        if (status == 4) {
            StudentResourceExamData returnExamData = new StudentResourceExamData();
            return Utility.response(returnExamData.getResultSummary(subjects));
        }
        if (status == 5) {
            StudentResourceViewProfile getProfile = new StudentResourceViewProfile();
            return new Gson().toJson((Object) getProfile.queryData(matric));
        }
        if (status == 6) {
            StudentResourceViewProfile getSubject = new StudentResourceViewProfile();
            return new Gson().toJson((Object) getSubject.learderBoardSubjects(subjects));
        }
        if (status == 7) {
            StudentResourceResult delete = new StudentResourceResult();
            return new Gson().toJson((Object) delete.UnregisterStudent(matric, subjects));
        }
        if (status == 8) {
            StudentResourceDeleteExamsUnregisterCandidate deleteExamAndcan = new StudentResourceDeleteExamsUnregisterCandidate();
            return new Gson().toJson((Object) deleteExamAndcan.prepareDeleteQuery(subjects));
        }
        StudentResourceGetAllSubjects getSubjects = new StudentResourceGetAllSubjects();
        return new Gson().toJson((Object) getSubjects.sendAllResults());

    } */
}
