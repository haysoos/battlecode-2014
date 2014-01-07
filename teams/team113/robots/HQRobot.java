package team113.robots;

import team113.communication.Message;
import team113.communication.MessageIntent;
import team113.pathing.BugPathingAlgorithm;
import team113.strategies.hq.HQSpawningState;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;

public class HQRobot extends BaseRobot {

	public HQRobot(RobotController rc) {
		super(rc);
		setState(new HQSpawningState());
		setPathingAlgorithm(new BugPathingAlgorithm(this));
		runProgram();
	}

	@Override
	public void enemyInSightAction(RobotInfo robotInfo) {
		Message message = returnToBaseMessage();
		broadcast(message);
	}

	private Message returnToBaseMessage() {
		Message message = new Message();
		message.setMessageIntent(MessageIntent.RETURN_TO_BASE);
		message.setMapLocation(getLocation());
		return message;
	}

}
