package com.pwr.Graph;

public class GraphClick {

	int type=-1;
	String id;
	int whichOne;
	
	public void clear()
	{
		type=-1;
	}
	public boolean isClicked()
	{
		if(type!=-1)
			return true;
		return false;
	}
	public  GraphClick(int ttype, String tid, int twhichone)
	{
		type=ttype;
		id=tid;
		whichOne=twhichone;
	}
	public void setClick(int ttype, String tid, int twhichone)
	{
		type=ttype;
		id=tid;
		whichOne=twhichone;
	}
}
