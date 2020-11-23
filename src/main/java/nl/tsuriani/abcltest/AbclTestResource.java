package nl.tsuriani.abcltest;

import nl.tsuriani.abcltest.infra.LispExecutionResult;
import nl.tsuriani.abcltest.infra.LispExecutor;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/abcl/")
public class AbclTestResource {
    @Inject
    LispExecutor lispExecutor;

    @GET
    @Path("test1")
    @Produces(MediaType.TEXT_PLAIN)
    public String test1() throws Exception {
        return lispExecutor.loadFromResourceFile("test")
                .map(LispExecutionResult::getStringValue)
                .orElse("Error.");
    }

    @GET
    @Path("test2")
    @Produces(MediaType.TEXT_PLAIN)
    public String test2() throws Exception {
        return lispExecutor.loadFromResourceFile("fibonacci")
                .map(LispExecutionResult::getIntValue)
                .map(Object::toString)
                .orElse("Error.");
    }

    @GET
    @Path("test3")
    @Produces(MediaType.TEXT_PLAIN)
    public String test3() throws Exception {
        return lispExecutor.loadFromResourceFile("load-file-test")
                .map(LispExecutionResult::getStringValue)
                .orElse("Error.");
    }
}
