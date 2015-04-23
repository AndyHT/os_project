package com.huoteng.module;

import java.util.ArrayList;

/**
 * Created by huoteng on 4/23/15.
 */
public class EveryElevatorTaskQueue {

    private int elevatorNum;

    private  ArrayList<Task> taskQueue = new ArrayList<>();

    public void addTask(int floor) {
        taskQueue.add(new Task(floor));
    }

    public int getTask(int index) {
        return taskQueue.get(index).gotoFloorth;
    }

    public boolean queueIsEmpty() {
        return taskQueue.isEmpty();
    }

    public EveryElevatorTaskQueue(int elevatorNum) {
        this.elevatorNum = elevatorNum;
    }
}
class Task {
    int gotoFloorth;

    public Task(int gotoFloorth) {
        this.gotoFloorth = gotoFloorth;
    }
}
