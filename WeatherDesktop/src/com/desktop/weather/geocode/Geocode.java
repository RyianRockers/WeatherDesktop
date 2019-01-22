package com.desktop.weather.geocode;

import com.desktop.weather.web.JsonReader;
import com.desktop.weather.web.WebResponse;
import javax.swing.*;

public class Geocode extends WebResponse implements JsonReader {
    private String url  = "https://maps.googleapis.com/maps/api/geocode/json?key=<KEY>c&address=";
    private String address;
    private String coordinates;

    public Geocode(String address){
        this.address = address;
        getNewCoordinates();
    }

    private String getNewCoordinates(){
        StringBuilder results;
        String formattedAddress = address.replace(' ', '+');

        url += formattedAddress;

        if(getHttpResponse(url) == 200){
            results = JsonReader.readJsonFromUrl(url);
            coordinates = results.toString();
        }
        else{
            JOptionPane.showMessageDialog(null,"Google Geocode API is not responding.");
        }

        return coordinates;
    }

    public String getCoordinates(){
        return coordinates;
    }

}
