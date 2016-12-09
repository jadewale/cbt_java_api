/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.endpoint;

import com.starktech.services.UserDetails;
import com.starktech.services.Utility;
import com.starktech.administrator.LogInAdministrator;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author jolaadeadewale
 */
@Path("administrator")
public class AdministratorResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AdministratorResource
     */
    public AdministratorResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.starktech.endpoint.AdministratorResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AdministratorResource
     *
     * @param content representation for the resource 
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @POST
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login")
    public String login(String jsonObj) {
        if (Utility.isNotNull(jsonObj)) {
            String[] userData = Utility.JsonParse(jsonObj, new String[]{"username",
                "password"}, 3);
            if (UserDetails.validUsername(userData[0]) && UserDetails.validPassword(userData[1])) {
                LogInAdministrator auth = new LogInAdministrator();

                return Utility.response(auth.authenticate(userData[0], userData[1]));
            }
        }

        return Utility.response(false);
    }
}
