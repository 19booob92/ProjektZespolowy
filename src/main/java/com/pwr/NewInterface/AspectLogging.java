package com.pwr.NewInterface;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AspectLogging {
	
	@After("execution(* getAllUsers(..))")
	public void killSplash() {
		ProjectMainView.splashWindow.dispose();
		ProjectMainView.splashWindow = null;
	}
}
