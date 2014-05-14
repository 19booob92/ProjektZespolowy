package com.pwr.Other;

import java.util.EmptyStackException;

public class NoDataInFieldException extends EmptyStackException {

	@Override
	public String getMessage() {
		return "Nie uzupelniono wszytkich wymaganych pol";
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return null;
	}
}
