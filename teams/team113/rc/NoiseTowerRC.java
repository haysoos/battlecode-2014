package team113.rc;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class NoiseTowerRC extends RC {

	public NoiseTowerRC(RobotController rc) {
		super(rc);
	}

	public void attackLoud(MapLocation mapLocation) {
		try {
			rc.attackSquare(mapLocation);
		} catch (GameActionException e) {
			printErrorMessage(e);
		}
	}

}
