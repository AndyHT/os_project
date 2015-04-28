package com.huoteng.controller;


import com.huoteng.view.MainView;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by huoteng on 4/20/15.
 * 控制电梯上下运动
 */
public class ElevatorViewController extends Thread{

    private MainView mainView;
    private ArrayList[] innerBtnsList;
    private EveryElevatorController elevator0;
    private EveryElevatorController elevator1;
    private EveryElevatorController elevator2;
    private EveryElevatorController elevator3;
    private EveryElevatorController elevator4;

    private UpDownController upDown;

    public ElevatorViewController() {
        mainView = new MainView();
        innerBtnsList = MainView.getBtnsList();
        mainView.getElevatorMainView().setSize(800, 600);
        mainView.getElevatorMainView().setVisible(true);
        upDown = new UpDownController();

        elevator0 = new EveryElevatorController(0);
        elevator1 = new EveryElevatorController(1);
        elevator2 = new EveryElevatorController(2);
        elevator3 = new EveryElevatorController(3);
        elevator4 = new EveryElevatorController(4);

        //为inner按钮增添Listener
        for (Button btn : (ArrayList<Button>)innerBtnsList[0]) {
            btn.addActionListener(elevator0);
        }
        for (Button btn : (ArrayList<Button>)innerBtnsList[1]) {
            btn.addActionListener(elevator1);
        }
        for (Button btn : (ArrayList<Button>)innerBtnsList[2]) {
            btn.addActionListener(elevator2);
        }
        for (Button btn : (ArrayList<Button>)innerBtnsList[3]) {
            btn.addActionListener(elevator3);
        }
        for (Button btn : (ArrayList<Button>)innerBtnsList[4]) {
            btn.addActionListener(elevator4);
        }
    }

    @Override
    public void run() {
        //启动五部电梯


        Thread elevatorThread0 = new Thread(elevator0);
        Thread elevatorThread1 = new Thread(elevator1);
        Thread elevatorThread2 = new Thread(elevator2);
        Thread elevatorThread3 = new Thread(elevator3);
        Thread elevatorThread4 = new Thread(elevator4);

        Thread upDownBtnThread = new Thread(upDown);

        elevatorThread0.start();
        elevatorThread1.start();
        elevatorThread2.start();
        elevatorThread3.start();
        elevatorThread4.start();
        upDownBtnThread.start();

        //将任务分发给五个电梯线程
//        String order;
//        int floor;
//        int wantGo;
//        while ( !UpDownController.taskIsEmpty()) {
//            order = UpDownController.getPressedFloor();
//            floor = Integer.parseInt(order.substring(0, 1));
//            wantGo = Integer.parseInt(order.substring(1));
//
//            //如果被按的那一层有电梯，直接打开电梯门
//            if (floor == elevator0.getElevatorCurrentrFloor()) {
////                mainView
//            } else if (floor == elevator1.getElevatorCurrentrFloor()) {
//                elevator1.setTasks(floor);
//            }
//
//        }

    }
}
