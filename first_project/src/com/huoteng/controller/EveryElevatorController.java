package com.huoteng.controller;

import com.huoteng.module.ElevatorCondition;
import com.huoteng.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 *
 * Created by huoteng on 4/23/15.
 */
public class EveryElevatorController implements Runnable, ActionListener{

    public static final int SLEEPTIME = 500;

    private ArrayList<Integer> task;

    private int elevatorNum;//电梯编号

    private int elevatorCondition;//电梯状态

    private int elevatorCurrentFloor;//电梯所在楼层

    private ArrayList<JLabel> labels_condition;

    private ArrayList<JButton>[] btns_upDown = new ArrayList[2];

    private JLabel[][] elevators;


    /**
     * 构造函数设置电梯编号和状态
     * 0静止1上2下
     * @param elevatorNum
     */
    public EveryElevatorController(int elevatorNum, MainView mainView) {
        this.elevatorNum = elevatorNum;
        this.elevatorCurrentFloor = 1;
        this.elevatorCondition = ElevatorCondition.FREEZ;

        task = new ArrayList<>();

        btns_upDown[0] = mainView.getBtns_up();
        btns_upDown[1] = mainView.getBtns_down();

        elevators = mainView.getLabels_currentFloor();

        for (int i = 0; i < 5; i++) {
            elevators[0][i].setBackground(Color.RED);
        }

        labels_condition = mainView.getLabels_elvatorCondition();

    }

    /**
     * 重写run()用来响应每部电梯
     * goFloor:将要去的楼层
     * btn:表示电梯
     * 被ElevatorViewContraoller调用
     */
    @Override
    public void run() {
        int goFloor;
        JButton btn;
        while (true) {
            if (!task.isEmpty()) {
                //执行任务
                goFloor = task.remove(0);

                if ( goFloor < elevatorCurrentFloor) {
                    //电梯上行
                    elevatorCondition = ElevatorCondition.UP;
//                    elevatorMove(elevatorCurrentFloor, goFloor);
//                    btn = (JButton)btns_upDown[0].get(goFloor);
//                    btn.setBackground(Color.LIGHT_GRAY);
                } else if ( goFloor > elevatorCurrentFloor) {
                    //电梯下行
                    elevatorCondition = ElevatorCondition.DOWN;
//                    elevatorMove(elevatorCurrentFloor, goFloor);
//                    btn = (JButton)btns_upDown[1].get(goFloor);
//                    btn.setBackground(Color.LIGHT_GRAY);
                } else {
                    elevatorCondition = ElevatorCondition.FREEZ;
                }
                elevatorMove(elevatorCurrentFloor, goFloor);
                labels_condition.get(elevatorNum).setText(ElevatorCondition.STRING_OPEN);
                elevatorSleep();
                labels_condition.get(elevatorNum).setText(ElevatorCondition.STRING_CLOSE);
                elevatorSleep();
                labels_condition.get(elevatorNum).setText(goFloor + "层");

            } else {
                //休眠
                elevatorSleep();
            }
        }
    }

    public void elevatorMove(int currentFloor, int goFloor) {
        if ( currentFloor < goFloor) {
            labels_condition.get(elevatorNum).setText(ElevatorCondition.STRING_UP);
            System.out.println(goFloor + ";");

            for ( int i = currentFloor; i < goFloor; i++) {
                elevators[i-1][elevatorNum].setBackground(Color.RED);
                elevatorCurrentFloor = i;
                elevatorSleep();
                elevators[i-1][elevatorNum].setBackground(Color.LIGHT_GRAY);
            }
        } else {
            labels_condition.get(elevatorNum).setText(ElevatorCondition.STRING_DOWN);
            for ( int i = currentFloor; i > goFloor; i--) {
                elevators[i-1][elevatorNum].setBackground(Color.RED);
                elevatorCurrentFloor = i;
                elevatorSleep();
                elevators[i-1][elevatorNum].setBackground(Color.LIGHT_GRAY);
            }
        }
        elevatorCondition = ElevatorCondition.FREEZ;
        elevators[elevatorCurrentFloor][elevatorNum].setBackground(Color.RED);
        System.out.println(elevatorCurrentFloor);//elevatorCurrentFloor有问题
    }

    public void elevatorSleep() {
        try {
            Thread.sleep(SLEEPTIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * inner按钮的Listener
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //将任务放入队列
        String command = e.getActionCommand();
        int floor;
        switch (command) {
            case "open":
                if (ElevatorCondition.FREEZ == elevatorCondition) {
                    labels_condition.get(elevatorNum).setText(ElevatorCondition.STRING_OPEN);
                }
                break;
            case "close":
                if (ElevatorCondition.FREEZ == elevatorCondition) {
                    labels_condition.get(elevatorNum).setText(ElevatorCondition.STRING_CLOSE);
                }
                break;
            case "alarm":
                break;
            default:
                if (command != null) {
                    floor = Integer.parseInt(command);
                    //将任务放到对应电梯的任务队列
                    addTask(floor);
                }
        }
    }

    public void addTask(int floor) {
        task.add(floor);
    }


    public int getElevatorNum() {
        return elevatorNum;
    }

    public int getElevatorCondition() {
        return elevatorCondition;
    }

    public int getElevatorCurrentrFloor() {
        return elevatorCurrentFloor;
    }


}

