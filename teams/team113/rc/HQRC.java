package team113.rc;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class HQRC extends RC {
	
	public HQRC(RobotController rc) {
		super(rc);
	}	

	public void spawn(Direction arg0) throws GameActionException {
		rc.spawn(arg0);
	}

	public boolean canSpawn(Direction spawnDirection) {
		return rc.canMove(spawnDirection);
	}
}
