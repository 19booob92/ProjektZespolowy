package com.pwr.NewInterface;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AspectLogging {

	@After("execution(* getAllUsers(..)) && args (splashWindow,..)")
	public void killSplash(SplashWindow splashWindow) {
		splashWindow.dispose();
		splashWindow = null;
	}
}
