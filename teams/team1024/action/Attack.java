package team1024.action;

import team1024.rc.SoldierRC;
import team1024.worldinfo.WorldInfo;
import battlecode.common.RobotInfo;

public class Attack implements Action {

	private final SoldierRC rc;

	public Attack(SoldierRC rc) {
		this.rc = rc;
	}

	@Override
	public void performAction(WorldInfo info) {
		RobotInfo weakest = rc.getWeakestEnemyInRange(info.getNearbyEnemies());
		if (weakest != null) {
			rc.attackRobot(weakest);
		}
	}
}
