package com.desktop.weather.darksky;

import com.desktop.weather.web.JsonReader;
import com.desktop.weather.web.WebResponse;

import javax.swing.*;

public class DarkSky extends WebResponse implements JsonReader{
    private String url = "https://api.darksky.net/forecast/<KEY>/";
    private String coordinates = "";
    private StringBuilder strBuilder = new StringBuilder();

    public DarkSky(String coordinates){
        this.coordinates = coordinates;
    }

    public StringBuilder getWeather(){
        url += coordinates;

        if(getHttpResponse(url) == 200){
            strBuilder = JsonReader.readJsonFromUrl(url);
        }
        else{
            JOptionPane.showMessageDialog(null,"Darksky API is not responding.");
        }

        return strBuilder;
    }
}
