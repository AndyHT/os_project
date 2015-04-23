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

    /**
     * 得到要去的楼层
     * @return
     */
    public static String getGoFloor() {
        String order;
        GoFloor temp;
        if (!goFloors.isEmpty()) {
            temp = goFloors.remove(0);
            order = Integer.toString(temp.condition);
            order += Integer.toString(temp.floor).substring(1);
            return order;
        } else {
            return null;
        }
    }

    /**
     *
     * @param floor
     * @param condition
     */
//    public static void setGoFloor(int floor, int condition) {
//        goFloors.add(new GoFloor(floor,condition));
//    }

    /**
     * 点击按钮的响应事件
     * 将任务放入队列
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //需要改进，光靠run()无法实现任务分配
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
                //核心算法
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
