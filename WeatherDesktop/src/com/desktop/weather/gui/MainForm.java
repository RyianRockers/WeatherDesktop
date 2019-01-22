package com.desktop.weather.gui;

import com.desktop.weather.darksky.DarkSky;
import com.desktop.weather.geocode.Geocode;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private JPanel jPanelMain;
    private JButton jButtonGo;
    private JTextField txtAddress;
    private JLabel jLabelAddress;
    private JTextArea textAreaResults;
    private JLabel labeCurrentlWeather;
    private JTable jTableWeather;
    private String address;
    private DarkSky newRequest;
    private Geocode newCoordinates;
    private String coordinates = "";
    private StringBuilder results;

    public MainForm() {
        jButtonGo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                address = txtAddress.getText();
                newCoordinates = new Geocode(address);
                coordinates = newCoordinates.getCoordinates();
                newRequest = new DarkSky(coordinates);
                results = newRequest.getWeather();
                formatJTableWithWeather(results);
                updateWeatherLabel();
            }
        });
    }

    public void formatJTableWithWeather(StringBuilder results){
        String [] strArray = results.toString().split(",");
        DefaultTableModel model = (DefaultTableModel) jTableWeather.getModel();

        TableColumn key = new TableColumn();
        TableColumn value = new TableColumn();
        key.setHeaderValue("Key");
        value.setHeaderValue("Value");
        model.addColumn(key);
        model.addColumn(value);
        jTableWeather.setModel(model);

        for(String result : strArray){
            String [] split = result.split(":");
            model.addRow(new Object[]{split[0], split[1]});
        }
    }

    public void updateWeatherLabel(){
        labeCurrentlWeather.setText("Current Weather in " + address);
    }

    public JPanel getjPanelMain(){
        return jPanelMain;
    }
}
