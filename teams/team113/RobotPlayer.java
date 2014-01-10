package team113;

import team113.robots.HQRobot;
import team113.robots.NoiseTowerRobot;
import team113.robots.PASTRRobot;
import team113.robots.SoldierRobot;
import battlecode.common.RobotController;

public class RobotPlayer {

	public static void run(RobotController rc) {
		switch (rc.getType()) {
		case HQ:
			new HQRobot(rc);
			break;
		case NOISETOWER:
			new NoiseTowerRobot(rc);
			break;
		case PASTR:
			new PASTRRobot(rc);
			break;
		case SOLDIER:
			new SoldierRobot(rc);
			break;
		default:
			break;
		}
	}
}
