package team2048.robots;

import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import team2048.pathing.BugPathingAlgorithm;
import team2048.states.pastr.PASTRState;
import team2048.strategies.hq.HQSpawningState;

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
