package com.roboworld.service;

import com.roboworld.enums.TaskType;
import com.roboworld.model.Robot;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RobotListener implements RobotListenerImpl {

    private static List<String> log = new ArrayList<String>(1000);

    @Override
    public synchronized void logRobotAction(Robot robot, TaskType type) {
        switch (type) {
            case CREATE: {
                String text = robot.getRobotName();
                log.add(text);
                break;
            }
            case DO: {
                String text = robot.getRobotName() + robot.getRobotTask().getType();
                log.add(text);
                break;
            }
            case STOP: {
                String text = robot.getRobotName();
                log.add(text);
                break;
            }
            case DESTROY: {
                String text = robot.getRobotName();
                log.add(text);
                break;
            }
        }
    }


    public List<String> getLog() {
        List<String> reversedLog = new ArrayList<>(log);
        Collections.reverse(reversedLog);
        return reversedLog;
    }



}
