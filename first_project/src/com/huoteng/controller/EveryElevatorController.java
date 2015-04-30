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

    /**
     * 任务队列
     */
    private ArrayList<Integer> task;

    /**
     * 电梯编号
     */
    private int elevatorNum;

    /**
     * 电梯状态
     */
    private int elevatorCondition;

    /**
     * 电梯所在楼层
     */
    private int elevatorCurrentFloor;

    /**
     * 电梯状态label
     */
    private ArrayList<JLabel> labels_condition;

    /**
     * 电梯运行图label
     */
    private JLabel[][] elevators;

    /**
     * 电梯上下行按钮
     */
    private ArrayList<JButton> btns_up;
    private ArrayList<JButton> btns_down;


    /**
     * 构造函数设置电梯编号和状态
     * 0静止1上2下
     * @param elevatorNum 设置的电梯编号
     */
    public EveryElevatorController(int elevatorNum, MainView mainView) {
        this.elevatorNum = elevatorNum;
        this.elevatorCurrentFloor = 1;
        this.elevatorCondition = ElevatorCondition.FREEZ;

        task = new ArrayList<>();

        elevators = mainView.getLabels_currentFloor();

        for (int i = 0; i < 5; i++) {
            elevators[0][i].setBackground(Color.RED);
        }

        labels_condition = mainView.getLabels_elvatorCondition();

        btns_up = mainView.getBtns_up();
        btns_down = mainView.getBtns_down();
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
        while (true) {
            if (!task.isEmpty()) {
                //执行任务
                goFloor = task.remove(0);

                if (goFloor > elevatorCurrentFloor) {
                    elevatorCondition = ElevatorCondition.UP;
                } else {
                    elevatorCondition = ElevatorCondition.DOWN;
                }

                switch (elevatorCondition) {
                    case ElevatorCondition.UP:
                        elevatorMoveUp(elevatorCurrentFloor, goFloor);
                        btns_up.get(19-elevatorCurrentFloor).setForeground(Color.BLACK);
                        break;
                    case ElevatorCondition.DOWN:
                        elevatorMoveDown(elevatorCurrentFloor, goFloor);
                        btns_down.get(19-elevatorCurrentFloor).setForeground(Color.BLACK);
                        break;
                    default:
                        elevatorCondition = ElevatorCondition.FREEZ;
                        break;
                }
            } else {
                elevatorSleep();
            }
        }
    }

    /**
     * 电梯上行
     * @param currentFloor 电梯当前楼层
     * @param goFloor 电梯要去的楼层
     */
    private void elevatorMoveUp(int currentFloor, int goFloor) {
        labels_condition.get(elevatorNum).setText(ElevatorCondition.STRING_UP);

        for ( int i = currentFloor; i < goFloor; i++) {
            if (1 == i) {
                elevatorSleep();
                elevators[0][elevatorNum].setBackground(Color.LIGHT_GRAY);
            }
            elevators[i][elevatorNum].setBackground(Color.RED);
            elevatorCurrentFloor = i;
            elevatorSleep();
            elevators[i][elevatorNum].setBackground(Color.LIGHT_GRAY);
        }

        elevatorCondition = ElevatorCondition.FREEZ;
        elevators[elevatorCurrentFloor][elevatorNum].setBackground(Color.RED);
        System.out.println(elevatorCurrentFloor);
        labels_condition.get(elevatorNum).setText(ElevatorCondition.STRING_OPEN);
        elevatorSleep();
        labels_condition.get(elevatorNum).setText(ElevatorCondition.STRING_CLOSE);
        elevatorSleep();
        labels_condition.get(elevatorNum).setText(elevatorCurrentFloor+1 + "层");
    }

    /**
     *电梯下行
     * @param currentFloor 电梯当前楼层
     * @param goFloor 目标楼层
     */
    private void elevatorMoveDown(int currentFloor, int goFloor) {
        labels_condition.get(elevatorNum).setText(ElevatorCondition.STRING_DOWN);

        for ( int i = currentFloor; i >= goFloor-1; i--) {
            elevators[i][elevatorNum].setBackground(Color.RED);
            elevatorCurrentFloor = i;
            elevatorSleep();
            elevators[i][elevatorNum].setBackground(Color.LIGHT_GRAY);
        }

        elevatorCondition = ElevatorCondition.FREEZ;
        elevators[elevatorCurrentFloor][elevatorNum].setBackground(Color.RED);
//        System.out.println(elevatorCurrentFloor);
        labels_condition.get(elevatorNum).setText(ElevatorCondition.STRING_OPEN);
        elevatorSleep();
        labels_condition.get(elevatorNum).setText(ElevatorCondition.STRING_CLOSE);
        elevatorSleep();
        labels_condition.get(elevatorNum).setText(elevatorCurrentFloor+1 + "层");

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
     * @param e ActionListener
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //将任务放入队列
        JButton btn = (JButton)e.getSource();
        String command = e.getActionCommand();
        int floor;
        switch (command) {
            case "open":
                if (ElevatorCondition.FREEZ == elevatorCondition) {
                    labels_condition.get(elevatorNum).setText(ElevatorCondition.STRING_OPEN);
                    elevatorSleep();
                    labels_condition.get(elevatorNum).setText(ElevatorCondition.STRING_CLOSE);
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
                floor = Integer.parseInt(command);
                //将任务放到对应电梯的任务队列
                addTask(floor);
        }
    }

    public void addTask(int floor) {
        task.add(floor);
    }


//    public int getElevatorNum() {
//        return elevatorNum;
//    }

    public int getElevatorCondition() {
        return elevatorCondition;
    }

    public int getElevatorCurrentrFloor() {
        return elevatorCurrentFloor;
    }
}

