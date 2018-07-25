package test;


import com.roboworld.dao.RobotTaskDAO;
import com.roboworld.enums.TaskType;
import com.roboworld.model.Robot;
import com.roboworld.model.RobotTask;
import com.roboworld.service.RoboWorld;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Problems with test run.
 * java.lang.IllegalStateException: Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test
 * Try all solutions from stackoverflow but it doest't helps me.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Deprecated
public class AppTest {

    /**
     * Test {@link com.roboworld.service.RoboWorld#createNewRobot}
     */
    @Test
    public void createRobotTest() {

        RobotTaskDAO robotTaskDAO = new RobotTaskDAO();
        RoboWorld roboWorld = new RoboWorld(robotTaskDAO);
        RobotTask robotTask = new RobotTask(1, TaskType.UPGRADE, 1);

        Robot robot = new Robot();
        robot.setId(1);

        Robot robot2 = new Robot();
        robot2.setId(2);

        roboWorld.addTaskToAll(robotTask);

        Assert.assertEquals(robot.getRobotTask(), robot2.getRobotTask());

    }

}

