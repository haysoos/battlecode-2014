package team113.robots;

import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import team113.pathing.BugPathingAlgorithm;
import team113.states.pastr.PASTRState;
import team113.strategies.hq.HQSpawningState;

public class PASTRRobot extends BaseRobot {

	public PASTRRobot(RobotController rc) {
		super(rc);
		setState(new PASTRState());
		setPathingAlgorithm(new BugPathingAlgorithm(this));
		runProgram();
	}

	@Override
	public void enemyInSightAction(RobotInfo robotInfo) {

	}

}
