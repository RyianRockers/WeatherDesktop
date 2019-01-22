package com.desktop.weather.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.*;

import javax.swing.*;

public interface JsonReader {

     static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    static StringBuilder readJsonFromUrl(String url){
         StringBuilder builder = new StringBuilder();
         try (InputStream is = new URL(url).openStream()){
             BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
             String jsonText = readAll(rd);
             JSONObject json = new JSONObject(jsonText);
             if(url.contains("geocode")){
                 builder = parseJsonForCoordinates(json);
             }
             else{
                 builder = parseJsonForWeather(json);
             }
        }catch(JSONException | IOException e){
            JOptionPane.showMessageDialog(null,e.toString());
        }
        return builder;
    }

    static StringBuilder parseJsonForCoordinates(JSONObject json){
         StringBuilder coordinates = new StringBuilder(2);

         try {
             if(json.get("status").toString().equalsIgnoreCase("OK")) {
                 coordinates.append(json.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lat"));
                 coordinates.append(',');
                 coordinates.append(json.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lng"));
             }
             else{
                 JOptionPane.showMessageDialog(null,"Geocode did not return data for Address specified.");
                 return null;
             }
         }catch(JSONException e){
             JOptionPane.showMessageDialog(null, e.toString());
         }
         return coordinates;
    }

    static StringBuilder parseJsonForWeather(JSONObject jsonObject){
        StringBuilder results = new StringBuilder();
         try {
             results.append(jsonObject.getJSONObject("currently").toString());
         }catch(JSONException e){
             JOptionPane.showMessageDialog(null,e.toString());
         }
        return results;
    }

}