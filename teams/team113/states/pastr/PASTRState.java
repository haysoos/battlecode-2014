package team113.states.pastr;

import team113.robots.BaseRobot;
import team113.states.State;

public class PASTRState implements State {

	@Override
	public void execute(BaseRobot robot) {
		robot.getRobotController().yield();
	}

}
