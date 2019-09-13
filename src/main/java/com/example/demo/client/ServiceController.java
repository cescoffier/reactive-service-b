package com.example.demo.client;

import com.example.demo.service.Database;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;

@Path("/b/service")
public class ServiceController {

    @Inject Database database;

    @GET
    @Path("/{parameter}")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<String> doSomething(@PathParam("parameter") String parameter) {
        return database.transmogrify(parameter);
    }
}
