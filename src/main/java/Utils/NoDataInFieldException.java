package Utils;

import java.util.EmptyStackException;

public class NoDataInFieldException extends EmptyStackException {
	
	@Override
	public String getMessage() {
		return "Nie uzupelniono wszytkich wymaganych pol";
	}
}
