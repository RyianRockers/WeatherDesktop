package com.desktop.weather.web;

import javax.swing.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebResponse {
    private int httpResponse;

    public int getHttpResponse(String url){
        try {
            URL newUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) newUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            httpResponse = connection.getResponseCode();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.toString());
        }

        return httpResponse;
    }
}
