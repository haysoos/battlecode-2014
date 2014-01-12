package team1024.action;

import team1024.rc.HQRC;
import team1024.worldinfo.WorldInfo;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameConstants;

public final class Spawn implements Action {
	
	private final HQRC rc;
	
	public Spawn(HQRC rc) {
		this.rc = rc;
	}

	@Override
	public void performAction(WorldInfo info) {
		try {					
			//Check if a robot is spawnable and spawn one if it is.
			if (rc.isActive() && rc.senseRobotCount() < GameConstants.MAX_ROBOTS) {
				Direction toEnemy = rc.getLocation().directionTo(rc.senseEnemyHQLocation());
				if (rc.senseObjectAtLocation(rc.getLocation().add(toEnemy)) == null) {
					rc.spawn(toEnemy);
				}
			}
		} catch (GameActionException e) {
			rc.printErrorMessage(e);
		}
	}

}