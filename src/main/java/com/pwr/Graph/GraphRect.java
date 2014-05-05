package com.pwr.Graph;

import java.awt.Rectangle;

public class GraphRect extends Rectangle {

	String id;
	int whichOne=0;
	public GraphRect(String tid, int twhichOne, int x, int y, int w, int h) {
		super(x,y,w,h);
		whichOne=twhichOne;
		id=tid;
	}
	
}
