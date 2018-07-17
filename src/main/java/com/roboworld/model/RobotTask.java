package com.roboworld.model;

import com.roboworld.enums.TaskType;

public class RobotTask {

    private TaskType type;
    private int id;
    private int time;

    public RobotTask(int id, TaskType type, int time) {
        this.id = id;
        this.type = type;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "RobotTask{" +
                "type=" + type +
                ", id=" + id +
                ", time=" + time +
                '}';
    }
}
