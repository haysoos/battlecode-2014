package team1024.action;

import team1024.rc.SoldierRC;
import team1024.worldinfo.WorldInfo;
import battlecode.common.Robot;
import battlecode.common.RobotInfo;

public class Attack implements Action {

	private final SoldierRC rc;

	public Attack(SoldierRC rc) {
		this.rc = rc;
	}

	@Override
	public void performAction(WorldInfo info) {
		Robot[] nearbyEnemies = rc.senseNearbyEnemyRobots();
		info.setNearbyEnemies(nearbyEnemies);
		RobotInfo weakest = rc.getWeakestEnemyInRange(nearbyEnemies);
		if (weakest != null) {
			rc.attackRobot(weakest);
		}
	}
}
