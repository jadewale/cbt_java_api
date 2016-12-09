/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.endpoint;

import com.google.gson.Gson;
import com.starktech.candidate.CandidateAuth;
import com.starktech.candidate.CandidateRegistration;
import com.starktech.candidate.CandidateRegisterExam;
import com.starktech.services.UserDetails;
import com.starktech.services.Utility;
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
@Path("candidate")
public class CandidateResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CandidateResource
     */
    public CandidateResource() {
    }

    /**
     * Retrieves representation of an instance of com.starktech.endpoint.CandidateResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "Hello World";
    }

    /**
     * PUT method for updating or creating an instance of CandidateResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @POST
    @Path("login")
    @Produces("application/json")
    public String getCandidateData(String json) {
        if (Utility.isNotNull(json)) {
            System.out.println(json);
            String[] userData = Utility.JsonParse(json, new String[]{"username",
                "password"}, 3);
            if (UserDetails.validUsername(userData[0]) && UserDetails.validPassword(userData[1])) {
                CandidateAuth verification = new CandidateAuth();
                return Utility.response(verification.authenticateUser(userData[0], userData[1])); 
            }
        }

        return Utility.parseString(null); 
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("register")
    public String student(String json) {
         
        if(Utility.isNotNull(json)) {
            String[] candidateData = Utility.JsonParse(json, new String[]{"fullName", "password", 
                "email", "number", "amount"}, 5); 
            CandidateRegistration register = new CandidateRegistration();
            if(Utility.isNotNull(candidateData[0]) && Utility.isNotNull(candidateData[1]) && 
                    Utility.isNotNull(candidateData[2]) && Utility.isNotNull(candidateData[3])) {
                return Utility.response(register.startRegistration(candidateData[0], candidateData[1], candidateData[2], 
                        candidateData[3], candidateData[4]));
            }   
        }
        /*
        if (status.equals("1")) {
            StudentResourceEditUpdate upd = new StudentResourceEditUpdate();
            return new Gson().toJson(upd.update(firstName, lastName, middleName, gender, email, mobileNumber, address, number2, address2, comment, matric));
        }
         */

        return new Gson().toJson("Method not Called in registration post");

    }
    
    @POST
    @Consumes("application/json")
    @Path("register/exam")
    public String registerStudents(String json, @QueryParam("exam") String exam) {

        if (Utility.isNotNull(json) && Utility.isNotNull(exam)) {
            String[] studentData = Utility.JsonParse(json, new String[]{"students"}, 1);

            if (Utility.isNotNull(studentData[0])) {
                CandidateRegisterExam register = new CandidateRegisterExam();
                return Utility.response(register.insert(Utility.stringToArray(studentData[0], "\""), exam));
            }
        }   
        return Utility.response(false);
    }
}
