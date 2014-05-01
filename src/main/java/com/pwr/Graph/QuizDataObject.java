package com.pwr.Graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class QuizDataObject {
	String title;
	String[] correct;
	String wrong;
	String id;
	int xCoord=0;
	int yCoord=0;
	int randomX;
	int randomY;
	
	static double size = 50;
	static int xGapIn=-10;
	static int yGapIn=(int) (size/4);
	
	static int xGapOut=(int)(size+10);
	static int yGapOut=(int) (size/4);
	
	
	static int xGapWrong=(int)(size/2);
	static int yGapWrong=(int)(size/2+10);
	
	final static int DRAW_MODE_IN=0;
	final static int DRAW_MODE_OUT=1;
	final static int DRAW_MODE_WRONG=2;
	static int color = 0xffffaa;

	public QuizDataObject(String title, String[] correct, String wrong,
			String id) {
		super();
		this.title = title;
		this.correct = correct;
		this.wrong = wrong;
		this.id = id;
		this.randomX=new Random().nextInt(1);
		this.randomY=new Random().nextInt(1);
	}

	public void draw(Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g;
			
		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(1));
		g2d.drawLine(randomX+x+xGapIn, randomY+y+yGapIn,randomX+ x+xGapOut,randomY+ y+ yGapOut);
		g2d.drawLine(randomX+x+xGapWrong,randomY+ y+yGapWrong,(int)( randomX+x+size/2),(int)( randomY+y+ size/4));
		g2d.drawLine(randomX+x+(int)(size/2), randomY+y,randomX+ x+(int)(size/2),randomY+ y-10-((xCoord%2==0)?20:0));
		
		g2d.setColor(new Color(color));
		g2d.fillRect(randomX+x,randomY+ y, (int) size, (int) (size / 2));
		g2d.setColor(new Color(0));
		g2d.setStroke(new BasicStroke(3));
		g2d.drawRect(randomX+x,randomY+ y, (int) size, (int) (size / 2));
		g2d.setColor(new Color(0));

		
		
		g2d.setColor(new Color(0xffffcc));
		g2d.fillRect(randomX+x,randomY+ y-20-((xCoord%2==0)?20:0),  8*9, (int) (size / 3));
		g2d.setColor(new Color(0));
		g2d.setStroke(new BasicStroke(1));
		g2d.drawRect(randomX+x,randomY+ y-20-((xCoord%2==0)?20:0),  8*9, (int) (size / 3));
		g2d.setColor(new Color(0));
		g2d.drawString(title.substring(0, title.length()>8?7:title.length())+((title.length()>8)?"...":""), randomX+x+5,randomY+ y+15-20-((xCoord%2==0)?20:0));
		
		
		g2d.fillOval(randomX+x+xGapIn-5,randomY+ y+yGapIn-5, 10, 10);
		g2d.fillOval(randomX+x+xGapOut-5,randomY+ y+ yGapOut-5, 10, 10);
		g2d.fillOval(randomX+x+xGapWrong-5,randomY+ y+ yGapWrong-5, 10, 10);
	}

	public static double getSize() {
		return size;
	}

	public static void setSize(double size) {
		QuizDataObject.size = size;
	}

	public String getCorrectByIndex(int index) {
		return correct[index];
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getCorrect() {
		return correct;
	}

	public void setCorrect(String[] correct) {
		this.correct = correct;
	}

	public String getWrong() {
		return wrong;
	}

	public void setWrong(String wrong) {
		this.wrong = wrong;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public int getxCoordAbsolute(int mode) {
		int tempX=0;
		if(mode==DRAW_MODE_IN)
		{
			tempX=xGapIn;
		}
		if(mode==DRAW_MODE_OUT)
		{
			tempX=xGapOut;
		}
		if(mode==DRAW_MODE_WRONG)
		{
			tempX=xGapWrong;
		}
		return (int) (randomX+tempX+GraphPanel.xOffset+xCoord * (QuizDataObject.getSize()+GraphPanel.space));
	}

	

	public int getyCoordAbsolute(int mode) {

		int tempY=0;
		if(mode==DRAW_MODE_IN)
		{
			tempY=yGapIn;
		}
		if(mode==DRAW_MODE_OUT)
		{
			tempY=yGapOut;
		}
		if(mode==DRAW_MODE_WRONG)
		{
			tempY=yGapWrong;
		}
		return (int) (randomY+tempY+GraphPanel.yOffset+yCoord * (QuizDataObject.getSize()+GraphPanel.space));
	}
}
