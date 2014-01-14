package team2048.states.soldier;

import team2048.robots.BaseRobot;
import team2048.states.State;
import battlecode.common.Clock;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

public class SoldierAttackHQState implements State {

	@Override
	public void execute(BaseRobot robot) {
		RobotController rc = robot.getRobotController();
		robot.moveTowardsTarget(rc.senseEnemyHQLocation());
		
		RobotInfo weakestRobot = robot.getWeakestEnemyInRange(robot.senseNearbyEnemyRobots());
		if (weakestRobot == null && Clock.getRoundNum() > 1000) {
			try {
				while (!rc.isActive()) {
					rc.yield();
				}
				robot.getRobotController().construct(RobotType.PASTR);
				
			} catch (GameActionException e) {
				robot.printErrorMessage(e);
			}
		}
	}

}
