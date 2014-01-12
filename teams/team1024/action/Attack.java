package team1024.action;

import battlecode.common.GameActionException;
import battlecode.common.Robot;
import battlecode.common.RobotInfo;
import team1024.RobotPlayer.WorldInfo;
import team1024.rc.SoldierRC;

public class Attack implements Action {

	private final SoldierRC rc;

	public Attack(SoldierRC rc) {
		this.rc = rc;
	}

	@Override
	public void performAction(WorldInfo info) {
		try {
			Robot[] nearbyEnemies = rc.senseNearbyGameObjects(Robot.class, 10,
					rc.getTeam().opponent());
			if (nearbyEnemies.length > 0) {
				RobotInfo robotInfo = rc.senseRobotInfo(nearbyEnemies[0]);
				rc.attackSquare(robotInfo.location);
			}
		} catch (GameActionException e) {
			e.printStackTrace();
		}
	}
}
