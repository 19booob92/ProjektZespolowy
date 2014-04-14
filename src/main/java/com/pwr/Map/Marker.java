/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pwr.Map;

/**
 *
 * @author Michał Sypniewski
 */
public class Marker {
    private double latitude;
    private double longtitude;
    public int id=0;
    private static int idTrigger=0;
    
    Marker(double lat, double lon)
    {
        idTrigger++;
        id=idTrigger;
        latitude = lat;
        longtitude = lon;
    }
    
    public static void decTrigger()
    {
        idTrigger--;
    }
    
    @Override
    public String toString()
    {
        return "&markers=color:blue%7Clabel:"+Integer.toString(id)+"%7C"+Double.toString(latitude)+","+Double.toString(longtitude);
    }

}
