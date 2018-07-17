package com.roboworld.controller;

import com.roboworld.dao.RobotTaskDAO;
import com.roboworld.service.RoboWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/*
* REST Endpoint	HTTP Method	Description
* GET	Returns the list of customers
* GET	Returns customer detail for given customer {id}
* POST	Creates new customer from the post data
* PUT	Replace the details for given customer {id}
* DELETE	Delete the customer for given customer {id}
*/

@RestController
public class GameControllerREST {

    private final RoboWorld roboWorld;
    private final RobotTaskDAO robotTaskDAO;

    @Autowired
    public GameControllerREST(RoboWorld roboWorld, RobotTaskDAO robotTaskDAO) {
        this.roboWorld = roboWorld;
        this.robotTaskDAO = robotTaskDAO;
    }

    @GetMapping(value = "/beginREST")
    public void startGameREST(Model model) {
        roboWorld.createRobotTasksQueue();
        roboWorld.initRobots();
        model.addAttribute("robotsList", roboWorld.getRobotList());
    }

    @GetMapping(value = "/addDestroyTaskREST")
    public ResponseEntity addDestroyTaskREST() {
        roboWorld.addTaskToAll(robotTaskDAO.getTask(0));
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping(value = "/addSpeakTaskREST")
    public ResponseEntity addSpeakTaskREST() {
        roboWorld.addTaskToAll(robotTaskDAO.getTask(1));
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping(value = "/addChargeTaskREST")
    public ResponseEntity addChargeTaskREST() {
        roboWorld.addTaskToAll(robotTaskDAO.getTask(2));
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping(value = "/addUpgradeTaskREST")
    public ResponseEntity addUpgradeTaskREST() {
        roboWorld.addTaskToAll(robotTaskDAO.getTask(3));
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping(value = "/addDestroyREST/{id}")
    public ResponseEntity addDestroyREST(@PathVariable("id") Long id) {
        roboWorld.addTaskQueueToRobot(robotTaskDAO.getTask(0), id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping(value = "/addSpeakREST/{id}")
    public ResponseEntity addSpeakREST(@PathVariable("id") Long id) {
        roboWorld.addTaskQueueToRobot(robotTaskDAO.getTask(1), id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping(value = "/addChargeREST/{id}")
    public ResponseEntity addChargeREST(@PathVariable("id") Long id) {
        roboWorld.addTaskQueueToRobot(robotTaskDAO.getTask(2), id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping(value = "/addUpgradeREST/{id}")
    public ResponseEntity addUpgradeREST(@PathVariable("id") Long id) {
        roboWorld.addTaskQueueToRobot(robotTaskDAO.getTask(3), id);
        return new ResponseEntity(HttpStatus.OK);
    }
}