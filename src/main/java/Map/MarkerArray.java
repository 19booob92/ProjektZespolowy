/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Map;

import java.awt.Graphics;

/**
 *
 * @author Michał Sypniewski
 */


public class MarkerArray {
    private MarkerRect[] marker = new MarkerRect[20];
    
    private int markers = 0;
    private int maxMarkers = 20;
    
    public MarkerArray(int tMaxMarkers)
    {
        maxMarkers=tMaxMarkers;
    }
    
    public void addMarker(double latitude,double longtitude)
    {
        if(markers<20)
        {
            marker[markers] = new MarkerRect(latitude,longtitude);
            markers++;
        }
    }
    
    public void setWidth(int width)
    {
        marker[markers-1].setWidth(width);
    }
    
    public void setHeight(int height)
    {
        marker[markers-1].setheight(height);
    }
    
    public void doDrawing(Graphics g)
    {
        for(int i=0;i<markers;i++)
        {
            marker[i].doDrawing(g);
        }
    }
    
    public void cleanUp()
    {
        for(int i=0;i<markers;i++)
        {
            marker[i].cleanUp();
        }
    }
    
    public void selectMarker(int x,int y)
    {
        for(int i=0;i<markers;i++)
        {
            marker[i].selectMarker(x, y);
        }
    }
    
    public boolean isSelected()
    {
        for(int i=0;i<markers;i++)
        {
            if(marker[i].isSelected())
            {
                return true;
            }
        }
        return false;
    }
    
    public void setLabel(String label)
    {
        for(int i=0;i<markers;i++)
        {
            if(marker[i].isSelected())
            {
                marker[i].setLabel(label);
                //break;
            }
        }
    }
    
    public boolean isSelectedWidth()
    {
        for(int i=0;i<markers;i++)
        {
            if(marker[i].isSelectedWidth())
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isSelectedHeight()
    {
        for(int i=0;i<markers;i++)
        {
            if(marker[i].isSelectedHeight())
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isSelectedDrag()
    {
        for(int i=0;i<markers;i++)
        {
            if(marker[i].isSelectedDrag())
            {
                return true;
            }
        }
        return false;
    }
    
    public void selectSize(int x,int y)
    {
        for(int i=0;i<markers;i++)
        {
            marker[i].selectSize(x, y);
        }
    }
    
    public void setX(int x)
    {
        for(int i=0;i<markers;i++)
        {
            if(marker[i].isSelectedDrag())
            {
                marker[i].setX(x);
               // break;
            }
        }
    }
    
    public void setY(int y)
    {
        for(int i=0;i<markers;i++)
        {
            if(marker[i].isSelectedDrag())
            {
                marker[i].setY(y);
               // break;
            }
        }
    }
    
    public void changeWidth(int width)
    {
        for(int i=0;i<markers;i++)
        {
            if(marker[i].isSelectedWidth())
            {
                marker[i].changeWidth(width);
               // break;
            }
        }
    }
    
    public void changeHeight(int height)
    {
        for(int i=0;i<markers;i++)
        {
            if(marker[i].isSelectedHeight())
            {
                marker[i].changeHeight(height);
            }
        }
    }
    
    

}
