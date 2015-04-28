package com.huoteng.controller;

import com.huoteng.module.ElevatorCondition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 管理外部Up和Down按钮产生的任务队列
 * Created by huoteng on 4/23/15.
 */
public class UpDownController implements ActionListener,Runnable {

    private static ArrayList<pressedFloor> pressedFloors = new ArrayList<>();

    /**
     * 得到按钮被按的楼层和要去的方向，0静止1上2下
     * @return 被按下的楼层和要去的方向，如:110,表示下楼，被按下的按钮位于十层
     */
    public static String getPressedFloor() {
        pressedFloor temp = pressedFloors.remove(0);
        String order = Integer.toString(temp.wantGo);
        order += Integer.toString(temp.floor);

        return order;
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
        int want;
        if (command.substring(2,4).equals("up")) {
            want = ElevatorCondition.UP;
        } else {
            want = ElevatorCondition.DOWN;
        }
        pressedFloors.add(new pressedFloor(floor, want));
    }

    /**
     * 实现任务分配
     */
    @Override
    public void run() {
        while (true) {
            if (pressedFloors.isEmpty()) {
                //分配任务
            } else {
                //sleep
            }
        }
    }

    /**
     * @return 队列是否为空
     */
    public static boolean taskIsEmpty() {
        return pressedFloors.isEmpty();
    }

}

class pressedFloor {
    int floor;
    int wantGo;

    public pressedFloor(int floor, int wantGo) {
        this.floor = floor;
        this.wantGo = wantGo;
    }
}
