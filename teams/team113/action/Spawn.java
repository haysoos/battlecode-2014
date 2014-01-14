package team113.action;

import team113.rc.HQRC;
import team113.worldinfo.WorldInfo;
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
				Direction spawnDirection = rc.getLocation().directionTo(rc.senseEnemyHQLocation());
				
				while (!rc.canSpawn(spawnDirection)) {
					spawnDirection = spawnDirection.rotateRight();
				} 
				
				rc.spawn(spawnDirection);
			}
		} catch (GameActionException e) {
			rc.printErrorMessage(e);
		}
	}

}
