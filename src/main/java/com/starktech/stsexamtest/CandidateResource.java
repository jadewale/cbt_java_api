/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.stsexamtest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
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
     * Retrieves representation of an instance of com.starktech.stsexamtest.CandidateResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "You got the resource"; 
    }

    /**
     * PUT method for updating or creating an instance of CandidateResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
