package nl.tsuriani;
import org.armedbear.lisp.*;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Path("/resteasy/hello")
public class AbclTestResource {

    @Inject
    Interpreter interpreter;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Integer hello() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Integer> callable = () -> {
            LispObject lispObject = interpreter.eval("1");
            return lispObject.intValue();
        };
        return executor.submit(callable).get();
    }
}
