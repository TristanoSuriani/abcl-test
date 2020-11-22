package nl.tsuriani;

import org.armedbear.lisp.Interpreter;

import javax.inject.Singleton;

@Singleton
public class ABCLConfiguration {
	@Singleton
	Interpreter getInterpreter() {
		var interpreter = Interpreter.getInstance();
		if (interpreter == null) {
			return Interpreter.createInstance();
		} else return interpreter;
	}
}
