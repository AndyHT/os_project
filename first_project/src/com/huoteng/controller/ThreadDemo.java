package com.huoteng.controller;

/**
 * Created by huoteng on 4/20/15.
 * 用Thread创建线程示范
 */
public class ThreadDemo extends Thread {
    int k = 0;
    public ThreadDemo(String name, int k) {
        super(name);
        this.k = k;
    }
    public void run() {
        int i = k;
        System.out.println(getName() + ",");
        while (i < 40) {
            System.out.println(i);
            i += 2;
        }
        System.out.println(getName() + ":end");
        System.out.println();
    }
}
