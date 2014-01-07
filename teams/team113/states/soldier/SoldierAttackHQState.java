package team113.states.soldier;

import team113.robots.BaseRobot;
import team113.states.State;
import battlecode.common.RobotController;

public class SoldierAttackHQState implements State {

	@Override
	public void execute(BaseRobot robot) {
		RobotController rc = robot.getRobotController();
		robot.moveTowardsTarget(rc.senseEnemyHQLocation());
	}

}
