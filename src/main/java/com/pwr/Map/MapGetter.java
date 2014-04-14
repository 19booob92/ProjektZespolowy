/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pwr.Map;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;


/**
 *
 * @author Michał Sypniewski
 */
public class MapGetter {
    private static double step= 0.003;
    private static double[] xToMap= {0.0001807692307692,0.00006184210526315736,0.000040869565217391,0.0000215596330275229};
    private static double[] yToMap= {0.00011240625,0.000053686567164179,0.000025692857142857,0.00001327306273062};
    
    private static int zoom=15;
    private static int zoomIndex=2;
    private static int imageSizeW=640;
    private static int imageSizeH=640;
    private static double latitude=51.110851;
    private static double longtitude=17.029839;
    private static String googleKey="AIzaSyAYEbDIFRtcBXkDn4XbE_VH7A7WqHx1Z8o";
    private static String mapType="roadmap";
    
    public static Marker[] markersArray = new Marker[20];
    public static int markers=0;
    
    static public void zoomIn()
    {
        if(zoom<16)
        {
        zoom++;
        zoomIndex++;
        }
    }
    
    static public void zoomOut()
    {
        if(zoom>13)
        {
        zoom--;
        zoomIndex--;
        }
    }
    
    static public double getLatitude()
    {
        return latitude;
    }
    
    static public double getLongtitude()
    {
        return longtitude;
    }
    
    static public double getXToMap()
    {
        return xToMap[zoomIndex];
    }
    
    static public double getYToMap()
    {
        return yToMap[zoomIndex];
    }
    
    static public int getImageSizeW()
    {
        return imageSizeW;
    }
    
    static public int getImageSizeH()
    {
        return imageSizeH;
    }
    static public void setImageSizeW(int width)
    {
        imageSizeW=width;
    }
    
    static public void setImageSizeH(int height)
    {
        imageSizeH=height;
    }
    
    static public void setMapType(String type)
    {
        mapType=type;
    }
    
    static public void deleteMarker()
    {
        if(markers>0){
        markersArray[markers]=null;
        markers--;
        Marker.decTrigger();
        }
    }
    
    static public double getLatitude(int y)
    {
        return latitude - yToMap[zoomIndex]*y + imageSizeH/2*yToMap[zoomIndex];
    }
    
    static public double getLongtitude(int x)
    {
        return longtitude + xToMap[zoomIndex]*x - imageSizeW/2*xToMap[zoomIndex];
    }
    
    static public String addMarker(int x,int y)
    {
        double lat = latitude - yToMap[zoomIndex]*y + imageSizeH/2*yToMap[zoomIndex];
        //double lat = 51.111565;
        double lon = longtitude + xToMap[zoomIndex]*x - imageSizeW/2*xToMap[zoomIndex];
        
        if(markers<20){
        markersArray[markers]=new Marker(lat,lon);
        markers++;}
        
        return Double.toString(lat)+","+Double.toString(lon);
    }
    
    static public double[] getMarker(int x, int y)
    {
    	double lat = latitude - yToMap[zoomIndex]*y + imageSizeH/2*yToMap[zoomIndex];
        double lon = longtitude + xToMap[zoomIndex]*x - imageSizeW/2*xToMap[zoomIndex];    
        double [] coords = {lat, lon};
        return coords;
    }
    
    static public String createUrl(int x,int y)
    {
        longtitude+=xToMap[zoomIndex]*x;
        latitude+=yToMap[zoomIndex]*y;
        String url = "http://maps.googleapis.com/maps/api/staticmap?center=";
        url+=Double.toString(latitude);
        url+=",";
        url+=Double.toString(longtitude);
        url+="&zoom=";
        url+=Integer.toString(zoom);
        url+="&size=";
        url+=Integer.toString(imageSizeW);
        url+="x";
        url+=Integer.toString(imageSizeH);
        
        /*if(markers!=0){
        url+="&markers=color:blue%7Clabel:P%7C";
        url+=Double.toString(marker_x);
        url+=",";
        url+=Double.toString(marker_y);
        }*/
        
        for(int i=0;i<markers;i++)
        {
            url+=markersArray[i].toString();
        }
        
        url+="&maptype=";
        url+=mapType;
        url+="&key=";
        url+=googleKey;
        //url+="&format=jpg";
        return url;
    }
    
        static public String createUrl(double tLongtitude, double tLatitude)
    {
        longtitude=tLongtitude;
        latitude=tLatitude;
        String url = "http://maps.googleapis.com/maps/api/staticmap?center=";
        url+=Double.toString(latitude);
        url+=",";
        url+=Double.toString(longtitude);
        url+="&zoom=";
        url+=Integer.toString(zoom);
        url+="&size=";
        url+=Integer.toString(imageSizeW);
        url+="x";
        url+=Integer.toString(imageSizeH);
        
        /*if(markers!=0){
        url+="&markers=color:blue%7Clabel:P%7C";
        url+=Double.toString(marker_x);
        url+=",";
        url+=Double.toString(marker_y);
        }*/
        
        for(int i=0;i<markers;i++)
        {
            url+=markersArray[i].toString();
        }
        
        url+="&maptype=";
        url+=mapType;
        url+="&key=";
        url+=googleKey;
        //url+="&format=jpg";
        return url;
    }
        
    static public void getMapImage(String imageUrl)
    {
         try {
        //    String imageUrl = "http://maps.google.com/staticmap?center=51.110851,17.029839&zoom=15&size=800x600&maptype=satellite&key=AIzaSyD9KVTMcGRQ37C0vVq5h2mq0S6N_GU6vCk
//&format=jpg";

            
            String destinationFile = "image.jpg";
            String str = destinationFile;
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(destinationFile);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
               
            os.flush();
            is.close();
            os.close();
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } 
    };
    
}
