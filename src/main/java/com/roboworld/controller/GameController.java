package com.roboworld.controller;

import com.roboworld.dao.RobotTaskDAO;
import com.roboworld.service.RoboWorld;
import com.roboworld.service.RobotListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {

    private final RoboWorld roboWorld;
    private final RobotTaskDAO robotTaskDAO;
    private final RobotListener robotListener;

    @Autowired
    public GameController(RoboWorld roboWorld, RobotTaskDAO robotTaskDAO, RobotListener robotListener) {
        this.roboWorld = roboWorld;
        this.robotTaskDAO = robotTaskDAO;
        this.robotListener = robotListener;
    }

    @RequestMapping(value = "/roboworld", method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView model = new ModelAndView();
        roboWorld.removeRobots();
        model.addObject("robotList", roboWorld.getRobotList());
        model.addObject("logList", robotListener.getLog());
        model.setViewName("roboworld");
        return model;
    }

    @RequestMapping(value = "/begin")
    public String startGame(Model model) {
        roboWorld.createRobotTasksQueue();
        roboWorld.initRobots();
        model.addAttribute("robotsList", roboWorld.getRobotList());
        return "redirect:/roboworld";
    }

    // Add Destroy action to the robot
    @RequestMapping(value = "/addDestroy/{id}")
    public String addDestroyYourselfToRobot(@PathVariable("id") Long id) {
        roboWorld.addTaskQueueToRobot(robotTaskDAO.getTask(0), id);
        return "redirect:/roboworld";
    }

    // Add Speak action to the robot
    @RequestMapping(value = "/addSpeak/{id}")
    public String addAttackActionToRobot(@PathVariable("id") Long id) {
        roboWorld.addTaskQueueToRobot(robotTaskDAO.getTask(1), id);
        return "redirect:/roboworld";
    }

    // Add Charge action to the robot
    @RequestMapping(value = "/addCharge/{id}")
    public String addBuildActionToRobot(@PathVariable("id") Long id) {
        roboWorld.addTaskQueueToRobot(robotTaskDAO.getTask(2), id);
        return "redirect:/roboworld";
    }

    // Add Upgrade action to the robot
    @RequestMapping(value = "/addUpgrade/{id}")
    public String addMineActionToRobot(@PathVariable("id") Long id) {
        roboWorld.addTaskQueueToRobot(robotTaskDAO.getTask(3), id);
        return "redirect:/roboworld";
    }

    //tasks for all robots
    @RequestMapping(value = "/addDestroyTask")
    public String addDestroyYourselAction() {
        roboWorld.addTaskToAll(robotTaskDAO.getTask(0));
        return "redirect:/roboworld";
    }

    //tasks for all robots
    @RequestMapping(value = "/addSpeakTask")
    public String addAttackAction(Model model) {
        roboWorld.addTaskToAll(robotTaskDAO.getTask(1));
        return "redirect:/roboworld";
    }

    //tasks for all robots
    @RequestMapping(value = "/addChargeTask")
    public String addBuildAction() {
        roboWorld.addTaskToAll(robotTaskDAO.getTask(2));
        return "redirect:/roboworld";
    }

    //tasks for all robots
    @RequestMapping(value = "/addUpgradeTask")
    public String addMineAction() {
        roboWorld.addTaskToAll(robotTaskDAO.getTask(3));
        return "redirect:/roboworld";
    }
}
