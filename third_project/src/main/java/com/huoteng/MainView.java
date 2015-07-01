package com.huoteng;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 主视图
 * 参考学长的项目生成视图
 * 改进正则表达式匹配
 * Created by huoteng on 6/13/15.
 */
public class MainView {

    public MainView() {
        FileFrame frame = new FileFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    class FileFrame extends JFrame {

        public FileFrame() {
            setSize(600, 600);
            setTitle("操作系统项目之文件管理");

            FilePanel filePanel = new FilePanel();
            Container contentPane = getContentPane();
            contentPane.add(filePanel);
        }
    }

    class FilePanel extends JPanel {
        private JFrame frame;
        private JTextArea terminalView;
        private JTextField intputField;
        private String rootPath = "admin:~ $";

        public FilePanel() {

            //界面设置
            setLayout(new BorderLayout());
            Border brd = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK);

            //输入命令行
            intputField = new JTextField();
            intputField.setBorder(brd);
            intputField.setBackground(Color.YELLOW);

            KeyHandler KeyListener = new KeyHandler();
            intputField.addKeyListener(KeyListener);
            intputField.setFont(new Font("Verdana", Font.BOLD, 18));
            intputField.setFocusable(true);

            JLabel label = new JLabel("[INPUT]");
            label.setFont(new Font("Times New Roman", Font.BOLD, 15));
            label.setBorder(brd);
            label.setForeground(Color.black);

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(label, BorderLayout.WEST);
            panel.add(intputField);

            this.add(panel, BorderLayout.SOUTH);

            //输出界面
            terminalView = new JTextArea();
            terminalView.setBorder(brd);
            terminalView.setLineWrap(true);
            terminalView.setWrapStyleWord(true);
            terminalView.setFocusable(false);
            terminalView.setBackground(Color.DARK_GRAY);
            terminalView.setForeground(Color.GREEN);
            terminalView.setFont(new Font("Verdana", Font.BOLD, 15));
            terminalView.append(rootPath);

            JScrollPane spOutput =
                    new JScrollPane(
                            terminalView,
                            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            this.add(spOutput);
        }

        private class KeyHandler implements KeyListener {
            public void keyPressed(KeyEvent event) {
                int keyCode = event.getKeyCode();
                if (keyCode == KeyEvent.VK_ENTER
                        && intputField.getText().compareTo("") != 0) {

                    //得到输入命令
                    String back = getInput(intputField.getText());

                    intputField.setText("");
                    terminalView.append(back);
                }
            }
            public void keyReleased(KeyEvent event) {
            }

            public void keyTyped(KeyEvent event) {
            }
        }

        private String getInput(String input) {
            //正则匹配输入命令
            String back = "admin:~ $ 输入有误请重新输入";
            Manager terminalManager = new Manager();

            if (input.matches("^cd[\\d\\w\\s/~]*")) {
                //cd命令
                terminalManager.order_cd("path");

            } else if (input.matches("^mkdir[\\d\\w\\s/~]*")) {
                //mkdir命令
                terminalManager.order_mkdir("newName");

            } else if (input.matches("^rm[\\d\\w\\s/~]*")) {
                //rm命令
                terminalManager.order_rm("rmName");

            } else if (input.matches("^pwd[\\s]*")) {
                //pwd命令
                back = terminalManager.order_pwd();

            } else if (input.matches("^help[\\s]*")) {
                //help
                back = terminalManager.order_help();

            }
            return back + "\n";
        }
    }
}

