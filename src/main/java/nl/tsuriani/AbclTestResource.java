package nl.tsuriani;
import org.armedbear.lisp.*;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Path("/abcl/")
public class AbclTestResource {

    @Inject
    Interpreter interpreter;

    @GET
    @Path("hello1")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer hello1() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Integer> callable = () -> {
            LispObject lispObject = interpreter.eval("1");
            return lispObject.intValue();
        };
        return executor.submit(callable).get();
    }

    @GET
    @Path("hello2")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello2() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<String> callable = () -> {
            InputStream inputStream = new ByteArrayInputStream(this.getClass().getClassLoader().getResourceAsStream("test.lisp").readAllBytes());
            var lispCode = new StringBuilder("(progn ")
                    .append(new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .collect(Collectors.joining("\n")))
                    .append(")")
                    .toString();

            LispObject lispObject = interpreter.eval(lispCode);
            return lispObject.getStringValue();
        };
        return executor.submit(callable).get();
    }
}
