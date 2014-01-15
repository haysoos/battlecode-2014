package team113.stateofmind;

import team113.action.Spawn;
import team113.rc.HQRC;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameConstants;

public class HaveManyBabies implements StateOfMind {
	
	private final HQRC rc;
	private final Spawn spawn;
	
	public HaveManyBabies(HQRC rc) {
		this.rc = rc;
		spawn = new Spawn(rc);
	}

	@Override
	public StateOfMind think() {
		// Nothing to think about.
		return this;
	}

	@Override
	public void act() {
		spawn.performAction(null);
	}
	
	// Alternative implementation of spawn that will result in yielding after
	// we determine that we cannot spawn in any dir.
	public void spawn() {
		try {					
			//Check if a robot is spawnable and spawn one if it is.
			if (rc.senseRobotCount() < GameConstants.MAX_ROBOTS) {
				Direction spawnDirection = rc.getLocation().directionTo(rc.senseEnemyHQLocation());
				int i = 1;
				for (; i < 8 && !rc.canSpawn(spawnDirection); i++) {					
					spawnDirection = spawnDirection.rotateRight();					
				}
				if (i < 8) {
					rc.spawn(spawnDirection);	
				}
			}
		} catch (GameActionException e) {
			rc.printErrorMessage(e);
		}
	}
}
