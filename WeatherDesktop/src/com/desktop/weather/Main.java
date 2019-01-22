package com.desktop.weather;

import javax.swing.*;
import com.desktop.weather.gui.MainForm;

public class Main {

    public static void main(String[] args) {
        JFrame newFrame = new JFrame("MainForm");
        newFrame.setContentPane(new MainForm().getjPanelMain());
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.pack();
        newFrame.setVisible(true);
    }
}
