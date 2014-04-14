/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pwr.Map;

//import Editor.MainView;
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
    
    public void deleteMarker(int index)
    {
        for(int i=index;i<markers-1;i++)
        {
            marker[i]=marker[i+1];
        }
        marker[markers-1]=null;
        markers--;
    }
    public void changeZoom()
    {
        for(int i=0;i<markers;i++)
        {
            marker[i].changeZoom();
        }
    }
    
    public void select(int index)
    {
        for(int i=0;i<markers;i++)
        {
            if(i==index)
            {
                marker[i].select();
            }
            else
            {
                marker[i].unSelect();
            }
        }
    }
    
    public double getLatitude(int index)
    {
        return marker[index].getLatitude();
    }
    
    public double getLongtitude(int index)
    {
        return marker[index].getLongtitude();
    }
    
    public double getLongtitude()
    {
        for(int i=0;i<markers;i++)
        {
            if(marker[i].isSelected())
            {
                return marker[i].getLongtitude();
            }
        }
        return 0;
    }
    
    public double getLatitude()
    {
        for(int i=0;i<markers;i++)
        {
            if(marker[i].isSelected())
            {
                return marker[i].getLatitude();
            }
        }
        return 0;
    }
    
    public int getSelectedIndex()
    {
        for(int i=0;i<markers;i++)
        {
            if(marker[i].isSelected())
            {
                return i;
            }
        }
        return -1;
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
        marker[markers-1].setHeight(height);
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
            if(marker[i].isSelected())
            {
                //MainView.setSelectedListItem(i);
            }
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
