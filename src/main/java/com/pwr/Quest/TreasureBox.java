package com.pwr.Quest;

public class TreasureBox {
	private double xCoordinate;
	private double yCoordinate;
	private double x2Coordinate;
	private double y2Coordinate;
        private double width;
        private double height;
	private String note;

	public TreasureBox() {

	}
	
	public void countDimmension()
	{
		width = (y2Coordinate - yCoordinate);
		height = (x2Coordinate - xCoordinate);
	}

	public double getX2Coordinate()
	{
		return x2Coordinate;
	}
	
	public double getY2Coordinate()
	{
		return y2Coordinate;
	}
	
	public void setX2Coordinate(double x)
	{
		this.x2Coordinate=x;
	}
	
	public void setY2Coordinate(double y)
	{
		this.y2Coordinate=y;
	}
	
    public double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public String toString()
    {
    	return note;
    }
        
        
}
