package nl.tsuriani.abcltest.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.armedbear.lisp.Fixnum;
import org.armedbear.lisp.LispObject;

@AllArgsConstructor
@Getter
public class LispExecutionResult {
	private LispObject result;

	public String getStringValue() {
		return result.getStringValue();
	}

	public boolean getBooleanValue() {
		return result.getBooleanValue();
	}

	public Integer getIntValue() {
		return ((Fixnum) result).value;
	}
}
