package com.huoteng.controller;


import com.huoteng.module.ElevatorCondition;
import com.huoteng.view.MainView;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by huoteng on 4/20/15.
 * 控制电梯上下运动
 */
public class ElevatorViewController extends Thread{

    private MainView mainView;
    private ArrayList[] innerBtnsList;
    private ArrayList<JButton> btns_up;
    private ArrayList<JButton> btns_down;
    private EveryElevatorController[] elevators = new EveryElevatorController[5];

    private UpDownController upDown;

    public ElevatorViewController() {
        mainView = new MainView();
        innerBtnsList = MainView.getBtnsList();
        btns_up = mainView.getBtns_up();
        btns_down = mainView.getBtns_down();

        mainView.getElevatorMainView().setSize(1024, 800);
        mainView.getElevatorMainView().setVisible(true);
        upDown = new UpDownController();

        for (int i = 0; i < elevators.length; i++) {
            elevators[i] = new EveryElevatorController(i, mainView);
        }

        //为上下行按钮添加Listener
        for (JButton btn : btns_up) {
            btn.addActionListener(upDown);
//            btn.addMouseListener(upDown);
        }
        for (JButton btn : btns_down) {
            btn.addActionListener(upDown);
//            btn.addMouseListener(upDown);
        }

        //为inner按钮添加Listener
        for (int i = 0; i < innerBtnsList.length; i++) {
            for (int j = 0; j < innerBtnsList[i].size(); j++) {
                JButton btn = (JButton)innerBtnsList[i].get(j);
                btn.addActionListener(elevators[i]);
            }
        }
    }

    @Override
    public void run() {
        //启动五部电梯

        Thread[] elevatorThreads = new Thread[5];

        for (int i = 0; i < elevatorThreads.length; i++) {
            elevatorThreads[i] = new Thread(elevators[i]);
            elevatorThreads[i].start();
        }

        while (true) {
            if (!UpDownController.taskIsEmpty()) {
                //分配任务
                //将任务分发给五个电梯线程
                String order;
                int floor;
                int wantGo;
                while ( !UpDownController.taskIsEmpty()) {
                    order = UpDownController.getPressedFloor();
                    wantGo = Integer.parseInt(order.substring(0, 1));
                    floor = Integer.parseInt(order.substring(1));
                    EveryElevatorController e;
                    if ( ElevatorCondition.UP == wantGo) {
                        //找到上行且所在楼层小于floor的电梯,将任务放入其任务队列
                        e = findUp(floor);
                    } else {
                        //找到下行且所在楼层大于floor的电梯,将任务放入其任务队列
                        e = findDown(floor);
                    }
                    if ( null == e) {
                        e = findFreez();
                    }
                    //将任务放入e的任务队列
                    e.addTask(floor);
                }
            } else {
                try {
                    sleep(EveryElevatorController.SLEEPTIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 找到上行且所在楼层小于floor的电梯
     * @param floor
     * @return
     */
    public EveryElevatorController findUp(int floor) {
        for (EveryElevatorController e : elevators) {
            if ( ElevatorCondition.UP == e.getElevatorCondition() && e.getElevatorCurrentrFloor() < floor) {
                return e;
            }
        }
        return null;
    }

    /**
     * 找到下行且所在楼层大于floor的电梯
     * @param floor
     * @return
     */
    public EveryElevatorController findDown(int floor) {
        for (EveryElevatorController e : elevators) {
            if ( ElevatorCondition.DOWN == e.getElevatorCondition() && e.getElevatorCurrentrFloor() > floor) {
                return e;
            }
        }
        return null;
    }

    /**
     * 找到静止的电梯
     * @return
     */
    public EveryElevatorController findFreez() {
        for (EveryElevatorController e : elevators) {
            if ( ElevatorCondition.FREEZ == e.getElevatorCondition()) {
                return e;
            }
        }
        return elevators[0];//几乎不可能发生的情况
    }
}
