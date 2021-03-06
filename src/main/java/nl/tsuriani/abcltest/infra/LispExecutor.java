package nl.tsuriani.abcltest.infra;

import lombok.extern.java.Log;
import org.armedbear.lisp.Interpreter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@ApplicationScoped
@Log
public class LispExecutor {
	@Inject
	Interpreter interpreter;

	public Optional<LispExecutionResult> loadFromResourceFile(String lispFileName) {
		try {
			final String normalizedName = "classes/abcl/" +
					(lispFileName.endsWith(".lisp")? lispFileName : lispFileName + ".lisp");
			ExecutorService executor = Executors.newSingleThreadExecutor();
			Callable<LispExecutionResult> callable = () -> {
				InputStream inputStream = new FileInputStream(new File(normalizedName));
				var lispCode = new StringBuilder("(progn ")
						.append(new BufferedReader(new InputStreamReader(inputStream)).lines()
								.collect(Collectors.joining("\n")))
						.append(")")
						.toString();
				return new LispExecutionResult(interpreter.eval(lispCode));
			};
			return Optional.of(executor.submit(callable).get());
		} catch (Exception exception) {
			log.severe(exception.getMessage());
			return Optional.empty();
		}
	}
}
