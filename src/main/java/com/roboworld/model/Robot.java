package com.roboworld.model;

import com.roboworld.enums.TaskType;
import com.roboworld.service.RobotListener;
import com.roboworld.service.RobotListenerImpl;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Robot implements Entity {

    private static int count = 0;
    private static ArrayList<Long> removeId = new ArrayList<>();
    private long id;
    private String robotName;
    private RobotTask robotTask;
    private BlockingQueue<RobotTask> queue;
    private BlockingQueue<RobotTask> manualQueue = new ArrayBlockingQueue<>(5);
    private RobotListenerImpl listener;

    public Robot() {
        id = count++;
        robotName = "Robot - " + id;
        listener = new RobotListener();
        listener.logRobotAction(this, TaskType.CREATE);
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Robot.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRobotName() {
        return robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

    public RobotTask getRobotTask() {
        return robotTask;
    }

    public void setRobotTask(RobotTask robotTask) {
        this.robotTask = robotTask;
    }

    public BlockingQueue<RobotTask> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<RobotTask> queue) {
        this.queue = queue;
    }

    public BlockingQueue<RobotTask> getManualQueue() {
        return manualQueue;
    }

    public void setManualQueue(BlockingQueue<RobotTask> manualQueue) {
        this.manualQueue = manualQueue;
    }

    public static ArrayList<Long> getRemoveId() {
        return removeId;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (manualQueue.isEmpty()) {
                    robotTask = queue.take();
                } else {
                    robotTask = manualQueue.take();
                }
                if (robotTask.getId() == 0) {
                    listener.logRobotAction(this, TaskType.DESTROY);
                    break;
                }
                doRobotAction();
                int time = robotTask.getTime();
                Thread.sleep(time * 100);
                listener.logRobotAction(this, TaskType.STOP);
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        removeId.add(id);
    }

    private void doRobotAction() {
        listener.logRobotAction(this, TaskType.DO);
    }
}
