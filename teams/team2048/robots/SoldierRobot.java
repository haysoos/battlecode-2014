package team2048.robots;

import team2048.pathing.BugPathingAlgorithm;
import team2048.states.soldier.SoldierAttackHQState;
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
