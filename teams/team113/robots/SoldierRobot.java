package team113.robots;

import team113.pathing.BugPathingAlgorithm;
import team113.states.soldier.SoldierAttackHQState;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;

public class SoldierRobot extends BaseRobot {

	public SoldierRobot(RobotController rc) {
		super(rc);
		setState(new SoldierAttackHQState());
		setPathingAlgorithm(new BugPathingAlgorithm(this));
		runProgram();
	}

	@Override
	public void enemyInSightAction(RobotInfo robotInfo) {
		attackRobot(robotInfo);
	}

}
