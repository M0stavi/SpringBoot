package org.example;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        // Print the initial scale factor
        double initialScaleFactor = getScaleFactor(toolkit);
        System.out.println("Initial Scale Factor: " + initialScaleFactor);

        // Add a property change listener to listen for display changes
        toolkit.addPropertyChangeListener("screenResolution", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                double newScaleFactor = getScaleFactor(toolkit);
                System.out.println("Scale Factor Changed: " + newScaleFactor);
            }
        });

        // Keep the application running to detect changes
        while (true) {
            // Simulate monitoring in the main thread
            try {
                Thread.sleep(1000);
                double newScaleFactor = getScaleFactor(toolkit);
                System.out.println("Scale Factor Changed: " + newScaleFactor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static double getScaleFactor(Toolkit toolkit) {
        int dpi = toolkit.getScreenResolution();
        // Assuming 96 DPI as the baseline for 100% scaling
        return dpi / 96.0;
    }
}