package com.huoteng.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Created by huoteng on 4/23/15.
 */
public class ThreadElevatorController implements Runnable, ActionListener {

    private static ArrayList<Task> taskQueue = new ArrayList<>();

    public static void setTaskQueue(int floor) {
        taskQueue.add(new Task(floor));
    }

    /**
     * 电梯线程
     */
    public ThreadElevatorController() {

    }

    /**
     * 重写run()用来响应每部电梯
     * 被ElevatorViewContraoller调用
     */
    @Override
    public void run() {
        while (true) {
            if (!taskQueue.isEmpty()) {
                //执行任务
            } else {
                //让线程休眠一段时间
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
                    taskQueue.add(new Task(floor));
                }
        }
    }
}

class Task {
    int gotoFloorth;

    public Task(int gotoFloorth) {
        this.gotoFloorth = gotoFloorth;
    }
}
