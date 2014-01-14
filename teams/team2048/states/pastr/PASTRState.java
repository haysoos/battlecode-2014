package team2048.states.pastr;

import team2048.robots.BaseRobot;
import team2048.states.State;

public class PASTRState implements State {

	@Override
	public void execute(BaseRobot robot) {
		robot.getRobotController().yield();
	}

}
