/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Editor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import static java.lang.Math.abs;

/**
 *
 * @author Michał Sypniewski
 */
public class MarkerRect {
    
    private double latitude;
    private double longtitude;
    private double latitudeV=0;
    private double longtitudeV=0;
    
    private boolean selected = false;
    
    private String label=null;
 
    private double x;
    private double y;
    private int width = 0;
    private int height = 0;
    
    public double getLatitude()
    {
        return latitude;
    }
    
    public double getLongtitude()
    {
        return longtitude;
    }
    
    public void selectMarker(int tX,int tY)
    {
        if(((tX>x)&&(tX<x+width))&&((tY>y)&&(tY<y+height)))
        {
            selected=true;
        }
        else
        {
            selected=false;
        }
        
    }
    public void setLabel(String tLabel)
    {
        if(tLabel!=null){
        label=tLabel;}
    }
    
    public boolean isSelected()
    {
        return selected;
    }
    public MarkerRect(double tLatitude, double tLongtitude)
    {
        latitude=tLatitude;
        longtitude=tLongtitude;
    }
    
    public void cleanUp()
    {
        if(label==null){label="Obszar";}
        if(width==0){width=60;}
        if(height==0){height=60;}
        latitude-=latitudeV;
        latitudeV=0;
        longtitude+=longtitudeV;
        longtitudeV=0;
    }
    public void setWidth(int tWidth)
    {
        if(tWidth>0)
        {
            width=tWidth;
        }
        else
        {
            longtitudeV=tWidth*MapGetter.getXToMap();
            width=abs(tWidth);
        }
    }
    
    public void setheight(int tHeight)
    {
        if(tHeight>=0)
        {
            height=tHeight;
        }
        else
        {
            latitudeV=tHeight*MapGetter.getYToMap();
            height=abs(tHeight);
        }
    }
    
    public void doDrawing(Graphics g)
    {
        if(selected)
        {
            g.setColor(Color.red);
        }
        //x = (int) (latitude - MapGetter.getLatitude() -MapGetter.getImageSizeH()/2*MapGetter.getYToMap());
        x = ((longtitude+longtitudeV) - MapGetter.getLongtitude())/MapGetter.getXToMap() + MapGetter.getImageSizeW()/2;
        //y = (int) (longtitude - MapGetter.getLongtitude() - MapGetter.getImageSizeH()/2*MapGetter.getXToMap());
        y = -((latitude-latitudeV) - MapGetter.getLatitude())/MapGetter.getYToMap() + MapGetter.getImageSizeH()/2;
        
        g.drawRect((int) x, (int)y, width, height);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18)); 
        g.setColor(Color.black);
        if(label!=null)
        {
            g.drawString(label, (int) x, (int) y+18);
        }
        
        
    }
}
