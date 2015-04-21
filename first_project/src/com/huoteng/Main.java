package com.huoteng;

import com.huoteng.view.*;

public class Main {


    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.


        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainView test = new MainView();
                test.getElevatorMainView().setSize(800,600);
                test.getElevatorMainView().setVisible(true);
            }
        });
    }
}
