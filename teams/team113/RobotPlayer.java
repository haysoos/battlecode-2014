package team113;

import team113.robots.HQRobot;
import team113.robots.SoldierRobot;
import battlecode.common.RobotController;

public class RobotPlayer {

	public static void run(RobotController rc) {
		switch (rc.getType()) {
		case HQ:
			new HQRobot(rc);
			break;
		case NOISETOWER:
			break;
		case PASTR:
			break;
		case SOLDIER:
			new SoldierRobot(rc);
			break;
		default:
			break;
		}
	}
}
