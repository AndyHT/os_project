package com.huoteng.view;

import javax.swing.*;
import java.awt.*;
import com.huoteng.controller.*;

/**
 * Created by huoteng on 4/20/15.
 */
public class MainView {

    public static final String TITLE = "Elevator Attemper";
    public static final int SIZE_WIDTH = 800;
    public static final int SIZE_HEIGHT = 600;

    /**
     * 创建并显示GUI。出于线程安全的考虑，
     * 这个方法在事件调用线程中调用。
     */
    public static void createAndShowGUI() {
        JFrame elevatorMainView = new JFrame(TITLE);
        Container elevatorContainer = elevatorMainView.getContentPane();//Main View container
        elevatorContainer.setLayout(new BorderLayout());

        /**
         * 生成Title lable
         */
        JLabel labelTile = new JLabel();
        labelTile.setText("操作系统项目:电梯模拟线程调度");
        elevatorContainer.add(labelTile, BorderLayout.NORTH);

        /**
         * 生成上下行按钮组并添加相应Listener
         * up_down_btns:上下行按钮组
         */
        JPanel up_down_btns = new JPanel();
        up_down_btns.setLayout(new GridLayout(20, 3));
        JButton btn;
        for (int i = 20; i > 0; i--) {
            btn = new JButton("Up");
            btn.addActionListener(new ElevatorViewController());
            up_down_btns.add(btn);

            btn = new JButton("Down");
            up_down_btns.add(btn);

            btn = new JButton(Integer.toString(i));
            up_down_btns.add(btn);
        }
        elevatorContainer.add(up_down_btns, BorderLayout.WEST);


        /**
         * 生成每部电梯内按钮
         * inner_btns:电梯内按钮组
         */
        JPanel inner_btns = new JPanel();
        inner_btns.setLayout(new GridLayout(25, 5));
        for (int i = 0; i < 5; i++) {
            btn = new JButton((i + 1) + " 号电梯");
            inner_btns.add(btn);
        }
        for (int i = 0; i < 5; i++) {
            btn = new JButton("1层");
            inner_btns.add(btn);
        }
        for (int i = 20; i > 0; i--) {
            for (int j = 0; j < 5; j++) {
                btn = new JButton(Integer.toString(i));
                inner_btns.add(btn);
            }
        }
        for (int i = 0; i < 5; i++) {
            btn = new JButton("开门");
            inner_btns.add(btn);
        }
        for (int i = 0; i < 5; i++) {
            btn = new JButton("关门");
            inner_btns.add(btn);
        }
        for (int i = 0; i < 5; i++) {
            btn = new JButton("警报");
            inner_btns.add(btn);
        }
        elevatorContainer.add(inner_btns,BorderLayout.CENTER);

        /**
         * 设置主界面大小
         */
        elevatorMainView.setVisible(true);
        elevatorMainView.setSize(SIZE_WIDTH,SIZE_HEIGHT);


    }
}
