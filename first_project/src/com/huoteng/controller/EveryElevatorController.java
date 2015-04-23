package com.huoteng.controller;

import com.huoteng.module.EveryElevatorTaskQueue;
import com.huoteng.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by huoteng on 4/23/15.
 */
public class EveryElevatorController implements Runnable,ActionListener{

    private static final int SLEEPTIME = 500;

    private static int elevatorNum;

    private EveryElevatorTaskQueue tasks;


    /**
     * 构造函数设置电梯编号
     * @param elevatorNum
     */
    public EveryElevatorController(int elevatorNum) {
        this.elevatorNum = elevatorNum;
        tasks = new EveryElevatorTaskQueue(elevatorNum);
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
            if (!tasks.queueIsEmpty()) {
                //执行任务
                goFloor = tasks.getTask(0);
                ArrayList[] btnsList = MainView.getBtnsList();
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
     * 点击电梯内按钮的响应事件
     * 将任务放入队列
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
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
                    tasks.addTask(floor);
                }
        }
    }
}

