package com.huoteng.controller;


import com.huoteng.module.ElevatorCondition;
import com.huoteng.view.MainView;

import javax.swing.*;
import java.awt.*;
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
    private EveryElevatorController[] everyElevators = new EveryElevatorController[5];

    private UpDownController upDown;

    public ElevatorViewController() {
        mainView = new MainView();
        innerBtnsList = MainView.getBtnsList();
        btns_up = mainView.getBtns_up();
        btns_down = mainView.getBtns_down();

        mainView.getElevatorMainView().setSize(1024, 800);
        mainView.getElevatorMainView().setVisible(true);
        upDown = new UpDownController();

        for (int i = 0; i < everyElevators.length; i++) {
            everyElevators[i] = new EveryElevatorController(i, mainView);
        }

        //为上下行按钮添加Listener
        for (JButton btn : btns_up) {
            btn.addActionListener(upDown);
        }
        for (JButton btn : btns_down) {
            btn.addActionListener(upDown);
        }

        //为inner按钮添加Listener
        for (int i = 0; i < innerBtnsList.length; i++) {
            for (int j = 0; j < innerBtnsList[i].size(); j++) {
                JButton btn = (JButton)innerBtnsList[i].get(j);
                btn.addActionListener(everyElevators[i]);
            }
        }
    }

    @Override
    public void run() {

        //启动五部电梯
        Thread[] elevatorThreads = new Thread[5];

        for (int i = 0; i < elevatorThreads.length; i++) {
            elevatorThreads[i] = new Thread(everyElevators[i]);
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

                    //将按钮还原为黑色
                    switch (wantGo) {
                        case ElevatorCondition.UP:
                            btns_up.get(floor).setForeground(Color.BLACK);
                            break;
                        case ElevatorCondition.DOWN:
                            btns_down.get(floor).setForeground(Color.BLACK);
                            break;
                        default:
                            break;
                    }

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
     * @param floor 按钮被按下的楼层
     * @return 找到的电梯
     */
    public EveryElevatorController findUp(int floor) {
        for (EveryElevatorController e : everyElevators) {
            if ( ElevatorCondition.UP == e.getElevatorCondition() && e.getElevatorCurrentrFloor() < floor) {
                return e;
            }
        }
        return null;
    }

    /**
     * 找到下行且所在楼层大于floor的电梯
     * @param floor 按钮被按下的楼层
     * @return 找到的电梯
     */
    public EveryElevatorController findDown(int floor) {
        for (EveryElevatorController e : everyElevators) {
            if ( ElevatorCondition.DOWN == e.getElevatorCondition() && e.getElevatorCurrentrFloor() > floor) {
                return e;
            }
        }
        return null;
    }

    /**
     * 找到静止的电梯
     * @return 找到的电梯
     */
    public EveryElevatorController findFreez() {
        for (EveryElevatorController e : everyElevators) {
            if ( ElevatorCondition.FREEZ == e.getElevatorCondition()) {
                return e;
            }
        }
        return everyElevators[0];//几乎不可能发生的情况
    }
}
