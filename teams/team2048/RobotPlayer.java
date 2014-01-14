package team2048;

import team2048.robots.HQRobot;
import team2048.robots.NoiseTowerRobot;
import team2048.robots.PASTRRobot;
import team2048.robots.SoldierRobot;
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
