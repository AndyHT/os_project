package com.huoteng.controller;

import com.huoteng.module.ElevatorCondition;
import com.huoteng.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by huoteng on 4/23/15.
 */
public class EveryElevatorController implements Runnable, ActionListener{

    private static final int SLEEPTIME = 500;

    private ArrayList<Integer> task;

    private int elevatorNum;//电梯编号

    private int elevatorCondition;//电梯状态

    private int elevatorCurrentFloor;//电梯所在楼层

    /**
     * 构造函数设置电梯编号和状态
     * 0静止1上2下
     * @param elevatorNum
     */
    public EveryElevatorController(int elevatorNum) {
        this.elevatorNum = elevatorNum;
        this.elevatorCurrentFloor = 1;
        this.elevatorCondition = ElevatorCondition.FREEZ;

        task = new ArrayList<>();

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
        ArrayList[] btnsList;
        while (true) {
            if (!task.isEmpty()) {
                //执行任务
                goFloor = task.remove(0);
                btnsList = MainView.getBtnsList();
                for (int i = 0;i < btnsList[elevatorNum].size(); i++) {
                    btn = (JButton)btnsList[elevatorNum].get(i);
                    btn.setBackground(Color.RED);
                    try {
                        Thread.sleep(SLEEPTIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    btn.setBackground(Color.LIGHT_GRAY);
                }
            } else {
                //休眠
                try {
                    Thread.sleep(SLEEPTIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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
                break;
            case "close":
                break;
            case "alarm":
                break;
            default:
                if (command != null) {
                    floor = Integer.parseInt(command);
                    //将任务放到对应电梯的任务队列
                    task.add(floor);
                }
        }
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

