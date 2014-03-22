/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Editor;

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
    private static double step=0.003;
    private static double xToMap=0.00004;
    private static double yToMap=0.000026;
    
    private static int zoom=15;
    private static int imageSizeW=640;
    private static int imageSizeH=640;
    private static double latitude=51.110851;
    private static double longtitude=17.029839;
    private static String googleKey="AIzaSyAYEbDIFRtcBXkDn4XbE_VH7A7WqHx1Z8o";
    private static String mapType="roadmap";
    
    public static Marker[] markersArray = new Marker[20];
    public static int markers=0;
    
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
    
    static public String addMarker(int x,int y)
    {
        double lat = latitude - yToMap*y + 320*yToMap;
        //double lat = 51.111565;
        double lon = longtitude + xToMap*x - 320*xToMap;
        
        if(markers<20){
        markersArray[markers]=new Marker(lat,lon);
        markers++;}
        
        return Double.toString(lat)+","+Double.toString(lon);
    }
    
    static public String createUrl(int x,int y)
    {
        longtitude+=xToMap*x;
        latitude+=yToMap*y;
        String url = "http://maps.googleapis.com/maps/api/staticmap?center=";
        url+=Double.toString(latitude);
        url+=",";
        url+=Double.toString(longtitude);
        url+="&zoom=";
        url+=Integer.toString(zoom);
        url+="&size=";
        url+=Integer.toString(imageSizeH);
        url+="x";
        url+=Integer.toString(imageSizeW);
        
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
