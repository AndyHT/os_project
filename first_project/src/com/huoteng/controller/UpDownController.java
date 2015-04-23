package com.huoteng.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 管理外部Up和Down按钮产生的任务队列
 * Created by huoteng on 4/23/15.
 */
public class UpDownController implements ActionListener, Runnable {

    private static ArrayList<GoFloor> goFloors = new ArrayList<>();

    public static GoFloor getGoFloor() {
        if (!goFloors.isEmpty()) {
            return goFloors.remove(0);
        } else {
            return null;
        }
    }

    public static void setGoFloor(int floor, int condition) {
        goFloors.add(new GoFloor(floor,condition));
    }

    /**
     * 点击按钮的响应事件
     * 将任务放入队列
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        //解析command
        int floor = Integer.parseInt(command.substring(0, 1));
        int condition;
        if (command.substring(2,4).equals("up")) {
            condition = 0;
        } else {
            condition = 1;
        }
        goFloors.add(new GoFloor(floor, condition));
    }

    /**
     * run()分配任务到每个电梯
     */
    @Override
    public void run() {
        while (true) {
            if (!goFloors.isEmpty()) {
                //将任务分发给五个电梯线程
            } else {
                //让线程休眠一段时间
            }
        }

    }
}

class GoFloor {
    int floor;
    int condition;//0 Up    1 Down

    public GoFloor(int floor, int condition) {
        this.floor = floor;
        this.condition = condition;
    }
}
