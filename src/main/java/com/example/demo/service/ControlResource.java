package com.example.demo.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/control")
@Produces(MediaType.TEXT_PLAIN)
public class ControlResource {

    @Inject Database database;

    @GET
    public String current() {
        return "slow: " + database.isSlow() + "\n"
                + "fail: " + database.isFail();
    }

    @GET
    @Path("/slow")
    public String toggleSlow() {
        return "isSlow: " + database.toggleSlow();
    }

    @GET
    @Path("/fail")
    public String toggleFail() {
        return "isFail: " + database.toggleFail();
    }

}
