package com.roboworld.service;

import com.roboworld.enums.TaskType;
import com.roboworld.model.Robot;

/*
* Tracking robots activity
 */
public interface RobotListenerImpl {
    void logRobotAction(Robot robot, TaskType type);
}
