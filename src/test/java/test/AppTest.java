package test;


import com.roboworld.enums.TaskType;
import com.roboworld.model.Robot;
import com.roboworld.model.RobotTask;
import com.roboworld.service.RoboWorld;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    RoboWorld roboWorld;

    @Autowired
    RobotTask robotTask;

    /**
     * Test {@link com.roboworld.service.RoboWorld#createNewRobot}
     */
    @Test
    public void createRobotTest() {
        Robot robot = new Robot();
        robot.setId(1);

        Robot robot2 = new Robot();
        robot2.setId(2);

        robotTask.setType(TaskType.CHARGE);
        roboWorld.addTaskToAll(robotTask);

        Assert.assertEquals(robot.getRobotTask(), robot2.getRobotTask());

    }

}

