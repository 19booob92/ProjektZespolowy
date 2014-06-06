package com.pwr.Other;

import java.awt.EventQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pwr.MainView.ProjectMainView;
import com.sun.jersey.api.client.filter.ContainerListener;

public class SendPackageListener extends ContainerListener {

	@Override
	public void onSent(long delta, long bytes) {
		ProjectMainView.updateProgressBar(bytes);
	}

	@Override
	public void onReceiveStart(long totalBytes) {
		System.out.print(totalBytes);
	}

	@Override
	public void onReceived(long delta, long bytes) {
	}

	@Override
	public void onFinish() {
	}
}
