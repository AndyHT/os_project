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
    private  Container elevatorContainer;
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
     * btns_elevator1:内部楼层按钮
     * lables_elevatorNumber:电梯编号Lable
     * lables_elevatorCondition:电梯状态Lable
     */
    private ArrayList<JButton> btns_elevator1;
    private ArrayList<JButton> btns_elevator2;
    private ArrayList<JButton> btns_elevator3;
    private ArrayList<JButton> btns_elevator4;
    private ArrayList<JButton> btns_elevator5;
    private ArrayList<JLabel> labels_elevatorNumber;
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
        elevatorContainer.add(panel_upDown, BorderLayout.WEST);
    }

    /**
     * 生成panel_inner;
     * panel_inner:电梯内按钮和Lable;
     */
    private void createPanel_inner() {
        JPanel panel_inner = new JPanel();
        panel_inner.setLayout(new GridLayout(25, 5));
        labels_elvatorCondition = new ArrayList<>();
        labels_elevatorNumber = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            label = new JLabel((i + 1) + " 号电梯");
            panel_inner.add(label);
            labels_elevatorNumber.add(label);
        }
        for (int i = 0; i < 5; i++) {
            label = new JLabel("1层");
            panel_inner.add(label);
            labels_elvatorCondition.add(label);
        }

        ArrayList<JButton> btns_inner = new ArrayList<>();
        for (int i = 20; i > 0; i--) {
            btn = new JButton(Integer.toString(i));
            btns_inner.add(btn);
        }
        btn = new JButton("开门");
        btns_inner.add(btn);
        btn = new JButton("关门");
        btns_inner.add(btn);
        btn = new JButton("报警");
        btns_inner.add(btn);

        btns_elevator1 = new ArrayList<>(btns_inner);
        btns_elevator2 = new ArrayList<>(btns_inner);
        btns_elevator3 = new ArrayList<>(btns_inner);
        btns_elevator4 = new ArrayList<>(btns_inner);
        btns_elevator5 = new ArrayList<>(btns_inner);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 23; j++) {
                panel_inner.add(btns_elevator1.get(j));
                panel_inner.add(btns_elevator2.get(j));
                panel_inner.add(btns_elevator3.get(j));
                panel_inner.add(btns_elevator4.get(j));
                panel_inner.add(btns_elevator5.get(j));
            }
        }

        elevatorContainer.add(panel_inner,BorderLayout.CENTER);
    }

    /**
     * 创建elevatorMainView和elevatorContainer对象;
     * 调用createPanel_upDown()
     * 调用createPanel_inner()
     */
    public MainView() {
        elevatorMainView = new JFrame(TITLE);
        elevatorContainer = elevatorMainView.getContentPane();
        elevatorContainer.setLayout(new BorderLayout());

        /**
         * 生成Title lable
         */
        JLabel labelTile = new JLabel();
        labelTile.setText("操作系统项目:电梯模拟线程调度");
        elevatorContainer.add(labelTile, BorderLayout.NORTH);

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

    public ArrayList<JLabel> getLabels_elevatorNumber() {
        return labels_elevatorNumber;
    }

    public ArrayList<JLabel> getLabels_elvatorCondition() {
        return labels_elvatorCondition;
    }

    public Container getElevatorContainer() {
        return elevatorContainer;
    }
}
