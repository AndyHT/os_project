package com.huoteng.controller;

import com.huoteng.module.ElevatorCondition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * 管理外部Up和Down按钮产生的任务队列
 * Created by huoteng on 4/23/15.
 */
public class UpDownController implements ActionListener{

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
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println("我被点啦");
        JButton btn = (JButton)e.getSource();
        String command = e.getActionCommand();
        //解析command
        int floor = Integer.parseInt(command.substring(0, 1));
        int want;
        if (command.substring(2,4).equals("up")) {
            want = ElevatorCondition.UP;
            btn.setForeground(Color.YELLOW);
        } else {
            want = ElevatorCondition.DOWN;
            btn.setForeground(Color.YELLOW);
        }
        pressedFloors.add(new pressedFloor(floor, want));
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
