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
     */
    private static ArrayList<JButton> btns_up;
    private static ArrayList<JButton> btns_down;

    private static ArrayList[] innerBtnsList = new ArrayList[5];//电梯内部按钮Array
    private static ArrayList<JLabel> labels_elvatorCondition; //电梯状态label

    private static JLabel[][] labels_currentFloor = new JLabel[20][5];//电梯所在楼层

    /**
     * 创建panel_upDown;
     * panel_upDown:上下行按钮组和楼层Lable;
     */
    private void createPanel_upDown() {
        JPanel panel_upDown = new JPanel();
        panel_upDown.setLayout(new GridLayout(20, 3));
        btns_up = new ArrayList<>();
        btns_down = new ArrayList<>();
        ArrayList<JLabel> lables_floor = new ArrayList<>();
        for (int i = 20; i > 0; i--) {
            btn = new JButton("Up");
            btn.setOpaque(true);
            panel_upDown.add(btn);
            btn.setActionCommand(i + ",up");
            btns_up.add(btn);

            btn = new JButton("Down");
            btn.setOpaque(true);
            panel_upDown.add(btn);
            btn.setActionCommand(i + ",down");
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
        /*
      电梯内部按钮
      panels_elevator
      btns_elevator1:内部楼层按钮
      lables_elevatorNumber:电梯编号Lable
      lables_elevatorCondition:电梯状态Lable
     */
        ArrayList<JPanel> panels_elevator = new ArrayList<>();

        for (int j = 0; j < 5; j++) {
            innerBtnsList[j] = new ArrayList<JButton>();
            for (int i = 20; i > 0; i--) {
                btn = new JButton(Integer.toString(i));
                btn.setActionCommand(Integer.toString(i));
                innerBtnsList[j].add(btn);
            }
            btn = new JButton("开门");
            btn.setActionCommand("open");
            innerBtnsList[j].add(btn);
            btn = new JButton("关门");
            btn.setActionCommand("close");
            innerBtnsList[j].add(btn);
            btn = new JButton("报警");
            btn.setActionCommand("alarm");
            innerBtnsList[j].add(btn);
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
                panel_oneElevator.add((JButton)innerBtnsList[i].get(j));
            }
            panels_elevator.add(panel_oneElevator);
            panel_inner.add(panel_oneElevator);
        }

        elevatorMainView.add(panel_inner,BorderLayout.EAST);
    }

    /**
     * 生成MainView中间的电梯状态图
     */
    private void createPanel_condition() {
        JPanel mainCondition = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints s = new GridBagConstraints();
        mainCondition.setLayout(layout);
        for (int x = 5; x > 0; x--) {
            label = new JLabel(x + "号电梯");
            s.gridx = x;
            s.gridy = 0;
            layout.setConstraints(label, s);
            mainCondition.add(label);
            for (int y = 20; y > 0 ; y--) {
                label = new JLabel("第" + (21-y) + "层");
                label.setBackground(Color.LIGHT_GRAY);
                label.setOpaque(true);
                label.setBorder(BorderFactory.createEtchedBorder());
                label.setPreferredSize(new Dimension(50, 30));
                labels_currentFloor[20-y][x-1] = label;
                s.gridx = x;
                s.gridy = y;
                layout.setConstraints(label,s);
                mainCondition.add(label);
            }
        }
        elevatorMainView.add(mainCondition, BorderLayout.CENTER);
    }

    /**
     * 创建elevatorMainView
     * 调用createPanel_upDown()
     * 调用createPanel_inner()
     * 调用createPanel_condition()
     */
    public MainView() {
        elevatorMainView = new JFrame(TITLE);
        elevatorMainView.setLayout(new BorderLayout());

        JLabel labelTile = new JLabel();//生成Title lable
        labelTile.setText("操作系统项目:电梯模拟线程调度");
        elevatorMainView.add(labelTile, BorderLayout.NORTH);

        createPanel_upDown();
        createPanel_inner();
        createPanel_condition();
    }

    public static ArrayList[] getBtnsList() {
        return innerBtnsList;
    }

    public JFrame getElevatorMainView() {
        return elevatorMainView;
    }

    public ArrayList<JButton> getBtns_up() {
        return btns_up;
    }

    public ArrayList<JButton> getBtns_down() {
        return btns_down;
    }

    public ArrayList<JLabel> getLabels_elvatorCondition() {
        return labels_elvatorCondition;
    }

    public JLabel[][] getLabels_currentFloor() {
        return labels_currentFloor;
    }

}
