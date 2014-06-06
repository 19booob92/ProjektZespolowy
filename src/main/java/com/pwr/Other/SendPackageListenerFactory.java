package com.pwr.Other;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.filter.ContainerListener;
import com.sun.jersey.api.client.filter.OnStartConnectionListener;

public class SendPackageListenerFactory implements OnStartConnectionListener{
	     public ContainerListener onStart(ClientRequest cr) {
	         return new SendPackageListener();
	    }
}
