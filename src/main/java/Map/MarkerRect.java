/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import static java.lang.Math.abs;

/**
 *
 * @author Michał Sypniewski
 */
public class MarkerRect {
    
    private double leftUpperLatitude;
    private double leftUpperLongtitude;

    private double rightBottomLatitude;
    private double rightBottomLongtitude;
    private double latitudeV=0;
    private double longtitudeV=0;
    
    private boolean selected = false;
    private boolean selectedWidth = false;
    private boolean selectedHeight = false;
    private boolean selectedDrag = false;
    
    private String label=null;
 
    private double x;
    private double y;
    private int width = 0;
    private int height = 0;
    
    private int widthV = 0;
    private int heightV = 0;
    
    private int barWidth=10;
    private int barHeight=10;
    
    public MarkerRect(double tLatitude, double tLongtitude)
    {
        leftUpperLatitude=tLatitude;
        leftUpperLongtitude=tLongtitude;
    }
    
    public void changeZoom()
    {
        width=abs((int)((leftUpperLongtitude-rightBottomLongtitude)/MapGetter.getXToMap()));
        height=abs((int)((leftUpperLatitude-rightBottomLatitude)/MapGetter.getYToMap()));
    }
    
    public void select()
    {
        selected=true;
    }
    
    public void unSelect()
    {
        selected=false;
    }
    public boolean isSelectedDrag()
    {
        return selectedDrag;
    }
    
    public void selectSize(int tX,int tY)
    {
        if(((tX>(x+width-barWidth))&&(tX<x+width))&&((tY>y)&&(tY<y+height)))
        {
            selectedWidth=true;
        }
        else
        {
            selectedWidth=false;
        }
        
        if(((tX>x)&&(tX<(x+width)))&&((tY>y+height-barHeight)&&(tY<y+height)))
        {
            selectedHeight=true;
        }
        else
        {
            selectedHeight=false;
        }
        
        if(((tX>x)&&(tX<x+width-barWidth))&&((tY>y)&&(tY<y+height-barHeight)))
        {
            selectedDrag=true;
        }
        else
        {
            selectedDrag=false;
        }
    }
    
    
    public boolean isSelectedWidth()
    {
        return selectedWidth;
    }
    
    public boolean isSelectedHeight()
    {
        return selectedHeight;
    }
    
    public double getLatitude()
    {
        return leftUpperLatitude;
    }
    
    public double getLongtitude()
    {
        return leftUpperLongtitude;
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

    
    public void cleanUp()
    {
        if(label==null){label="Obszar";}
        height+=heightV;
        width+=widthV;
        heightV=0;
        widthV=0;
        //if(width==0){width=60;}
        //if(height==0){height=60;}
        leftUpperLatitude-=latitudeV;
        latitudeV=0;
        leftUpperLongtitude+=longtitudeV;
        longtitudeV=0;
        
        rightBottomLongtitude=leftUpperLongtitude+width*MapGetter.getXToMap();
        rightBottomLatitude=leftUpperLatitude-height*MapGetter.getYToMap();
    }
    public void setWidth(int tWidth)
    {
        if(tWidth>0)
        {
            widthV=tWidth;
        }
        else
        {
            longtitudeV=tWidth*MapGetter.getXToMap();
            widthV=abs(tWidth);
        }
    }
    
    public void setheight(int tHeight)
    {
        if(tHeight>=0)
        {
            heightV=tHeight;
        }
        else
        {
            latitudeV=tHeight*MapGetter.getYToMap();
            heightV=abs(tHeight);
        }
    }
    
    public void setX(int tX)
    {
        longtitudeV=tX*MapGetter.getXToMap();
    }
    
    public void setY(int tY)
    {
        latitudeV=tY*MapGetter.getYToMap();
    }
    public void changeWidth(int tWidth)
    {
        if(width+widthV>=30)
        {
            widthV=tWidth;
        }
        else
        {
            width=30;
            widthV=10;
        }

    }
    
        public void changeHeight(int tHeight)
    {
        if(height+heightV>=30)
        {
            heightV=tHeight;
        }
        else
        {
            height=30;
            heightV=10;
        }

    }
    
    public void doDrawing(Graphics g)
    {
        if(selected)
        {
            g.setColor(Color.red);
        }
        //x = (int) (latitude - MapGetter.getLatitude() -MapGetter.getImageSizeH()/2*MapGetter.getYToMap());
        x = ((leftUpperLongtitude+longtitudeV) - MapGetter.getLongtitude())/MapGetter.getXToMap() + MapGetter.getImageSizeW()/2;
        //y = (int) (longtitude - MapGetter.getLongtitude() - MapGetter.getImageSizeH()/2*MapGetter.getXToMap());
        y = -((leftUpperLatitude-latitudeV) - MapGetter.getLatitude())/MapGetter.getYToMap() + MapGetter.getImageSizeH()/2;
        
        g.drawRect((int) x, (int)y, width + widthV, height + heightV);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18)); 
        
        if(selected)
        {
            g.drawRect((int)x+width+widthV-barWidth, (int)y, barWidth, height+heightV-barWidth);
            g.drawRect((int) x, (int) y +height+heightV-barHeight, width+widthV-barHeight, barHeight);
        }
        g.setColor(Color.black);
        if(label!=null)
        {
            g.drawString(label, (int) x, (int) y+18);
        }
        

    }
}
