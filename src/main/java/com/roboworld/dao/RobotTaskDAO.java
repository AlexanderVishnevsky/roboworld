package com.roboworld.dao;

import com.roboworld.enums.TaskType;
import com.roboworld.model.RobotTask;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RobotTaskDAO {

    private static ArrayList<RobotTask> robotTasks;

    {
        robotTasks = new ArrayList<>();
        robotTasks.add(new RobotTask(1, TaskType.SPEAK, 1));
        robotTasks.add(new RobotTask(2, TaskType.CHARGE, 3));
        robotTasks.add(new RobotTask(3, TaskType.UPGRADE, 5));
        robotTasks.add(new RobotTask(99, TaskType.STOP, 0));
        robotTasks.add(new RobotTask(0, TaskType.DESTROY, 0));
    }

    public void addTask(RobotTask robotTask) {
        robotTasks.add(robotTask);
    }

    public int deleteTask(int id) {
        for (RobotTask robotTask : robotTasks) {
            if (robotTask.getId() == id) {
                robotTasks.remove(robotTask);
                return id;
            }
        }
        return -1;
    }
    public RobotTask getTask(int id) {
        for (final RobotTask robotTask : robotTasks) {
            if (robotTask.getId() == id) {
                return robotTask;
            }
        }
        return null;
    }

    public List<RobotTask> getTaskList() {
        return robotTasks;
    }

}
