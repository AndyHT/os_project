package com.huoteng.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Created by huoteng on 4/20/15.
 * 生成主界面
 */
public class MainView {

    public static final String TITLE = "Elevator Attemper";

    private  JFrame elevatorMainView;
    private JButton btn;
    private JLabel label;

    /**
     * 电梯外部按钮
     * up_btns:上行按钮
     * down_btns:下行按钮
     * condition_btns:电梯状态Lable
     */
    private ArrayList<JButton> btns_up;
    private ArrayList<JButton> btns_down;
    private ArrayList<JLabel> lables_floor;

    /**
     * 电梯内部按钮
     * panels_elevator
     * btns_elevator1:内部楼层按钮
     * lables_elevatorNumber:电梯编号Lable
     * lables_elevatorCondition:电梯状态Lable
     */
    private ArrayList<JPanel> panels_elevator;
    private ArrayList<JButton> btns_elevator1;
    private ArrayList<JButton> btns_elevator2;
    private ArrayList<JButton> btns_elevator3;
    private ArrayList<JButton> btns_elevator4;
    private ArrayList<JButton> btns_elevator5;
    private ArrayList<JLabel> labels_elvatorCondition;

    /**
     * 创建panel_upDown;
     * panel_upDown:上下行按钮组和楼层Lable;
     */
    private void createPanel_upDown() {
        JPanel panel_upDown = new JPanel();
        panel_upDown.setLayout(new GridLayout(20, 3));
        btns_up = new ArrayList<>();
        btns_down = new ArrayList<>();
        lables_floor = new ArrayList<>();
        for (int i = 20; i > 0; i--) {
            btn = new JButton("Up");
            panel_upDown.add(btn);
            btns_up.add(btn);

            btn = new JButton("Down");
            panel_upDown.add(btn);
            btns_down.add(btn);

            label = new JLabel(Integer.toString(i));
            panel_upDown.add(label);
            lables_floor.add(label);
        }
        elevatorMainView.add(panel_upDown, BorderLayout.WEST);
    }

    /**
     * 生成panel_inner;
     * panel_inner:电梯内按钮和Lable;
     */
    private void createPanel_inner() {
        JPanel panel_inner = new JPanel();
        JPanel panel_oneElevator;
        panel_inner.setLayout(new GridLayout(1, 5));
        labels_elvatorCondition = new ArrayList<>();
        panels_elevator = new ArrayList<>();

        ArrayList[] btnsList = {btns_elevator1,btns_elevator2,btns_elevator3,btns_elevator4,btns_elevator5};

        for (int j = 0; j < 5; j++) {
            btnsList[j] = new ArrayList<>();
            for (int i = 20; i > 0; i--) {
                btn = new JButton(Integer.toString(i));
                btnsList[j].add(btn);
            }
            btn = new JButton("开门");
            btnsList[j].add(btn);
            btn = new JButton("关门");
            btnsList[j].add(btn);
            btn = new JButton("报警");
            btnsList[j].add(btn);
        }

        for (int i = 0; i < 5; i++) {
            panel_oneElevator = new JPanel();
            panel_oneElevator.setLayout(new GridLayout(25, 1));
            label = new JLabel((i + 1) + " 号电梯");
            panel_oneElevator.add(label);
            label = new JLabel("1层");
            panel_oneElevator.add(label);
            labels_elvatorCondition.add(label);
            for (int j = 0; j < 23; j++) {
                panel_oneElevator.add((JButton)btnsList[i].get(j));
            }
            panels_elevator.add(panel_oneElevator);
            panel_inner.add(panel_oneElevator);
        }

        elevatorMainView.add(panel_inner,BorderLayout.CENTER);
    }

    /**
     * 创建elevatorMainView和elevatorContainer对象;
     * 调用createPanel_upDown()
     * 调用createPanel_inner()
     */
    public MainView() {
        elevatorMainView = new JFrame(TITLE);
        elevatorMainView.setLayout(new BorderLayout());

        /**
         * 生成Title lable
         */
        JLabel labelTile = new JLabel();
        labelTile.setText("操作系统项目:电梯模拟线程调度");
        elevatorMainView.add(labelTile, BorderLayout.NORTH);

        createPanel_upDown();
        createPanel_inner();
    }

    public ArrayList<JButton> getBtns_up() {
        return btns_up;
    }

    public ArrayList<JButton> getBtns_down() {
        return btns_down;
    }

    public ArrayList<JLabel> getLables_floor() {
        return lables_floor;
    }

    public ArrayList<JButton> getBtns_elevator1() {
        return btns_elevator1;
    }

    public ArrayList<JButton> getBtns_elevator2() {
        return btns_elevator2;
    }

    public ArrayList<JButton> getBtns_elevator3() {
        return btns_elevator3;
    }

    public ArrayList<JButton> getBtns_elevator4() {
        return btns_elevator4;
    }

    public ArrayList<JButton> getBtns_elevator5() {
        return btns_elevator5;
    }

    public ArrayList<JLabel> getLabels_elvatorCondition() {
        return labels_elvatorCondition;
    }

    public JFrame getElevatorMainView() {
        return elevatorMainView;
    }

    public ArrayList<JPanel> getPanels_elevator() {
        return panels_elevator;
    }
}
