package com.desktop.weather.darksky;

import com.desktop.weather.web.JsonReader;
import com.desktop.weather.web.WebResponse;

import javax.swing.*;

public class DarkSky extends WebResponse implements JsonReader{
    private String url = "https://api.darksky.net/forecast/046201017f10eee4e0f0b8b7f499afcf/";
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
