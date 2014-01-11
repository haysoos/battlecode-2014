package team113.robots;

import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import team113.pathing.BugPathingAlgorithm;
import team113.strategies.hq.HQSpawningState;

public class NoiseTowerRobot extends BaseRobot {

	public NoiseTowerRobot(RobotController rc) {
		super(rc);
		setState(new HQSpawningState());
		setPathingAlgorithm(new BugPathingAlgorithm(this));
		runProgram();
	}

	@Override
	public void enemyInSightAction(RobotInfo robotInfo) {
				
	}

}
