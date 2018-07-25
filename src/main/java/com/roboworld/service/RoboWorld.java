package com.roboworld.service;


import com.roboworld.dao.RobotTaskDAO;
import com.roboworld.model.Robot;
import com.roboworld.model.RobotTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Service
public class RoboWorld {

    private final RobotTaskDAO robotTaskDAO;

    //Working with Blocking queue
    private final int capacityOfRobotActionsQueue = 10;
    private static Map<Long, Robot> robotMap = new HashMap<>();

    private BlockingQueue<RobotTask> queue = new ArrayBlockingQueue<>(capacityOfRobotActionsQueue);
    {
        int startNumbersOfRobots = 3;
        for (int i = 1; i<= startNumbersOfRobots; i++) {
            Robot robot = new Robot();
            robotMap.put(robot.getId(), robot);
        }
    }

    @Autowired
    public RoboWorld(RobotTaskDAO robotTaskDAO) {
        this.robotTaskDAO = robotTaskDAO;
    }

    // Create new robot
    private void createNewRobot() {
        Robot robot = new Robot();
        robot.setQueue(queue);
        robotMap.put(robot.getId(), robot);
        Thread thread = new Thread(robot);
        thread.start();
    }

    // Initialize all robots at start
    public void initRobots() {
        for (Robot r: robotMap.values()) {
            r.setQueue(queue);
            Thread thread = new Thread(r, r.getRobotName());
            thread.start();
        }
    }

    // Fill Task queue with tasks by random generated id
    public void createRobotTasksQueue() {
        int startNumbersOfRobotTasks = 6;
        for (int i = 0; i< startNumbersOfRobotTasks; i++) {
            int id = (int)(Math.random()*(robotTaskDAO.getTaskList().size()-1));
            queue.add(robotTaskDAO.getTaskList().get(id));
        }
    }

    //Add the Robot Task to the queue for all robots
    public void addTaskToAll(RobotTask robotTask) {
        try {
            queue.put(robotTask);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Return list of all active robots
    public Collection<Robot> getRobotList() {
        return robotMap.values();
    }

    // Add certain task to the robot
    public void addTaskQueueToRobot(RobotTask robotTask, Long robotId) {
        try {
            robotMap.get(robotId).getManualQueue().put(robotTask);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robotMap.get(robotId).run();
        if (robotTask.getId()==0) {
            robotMap.remove(robotId);
        }
    }

    // Remove all destroyed robots from map
    public void removeRobots() {
        for (Long i: Robot.getRemoveId()) {
            robotMap.remove(i);
            createNewRobot();
        }
        Robot.getRemoveId().clear();
    }
}
