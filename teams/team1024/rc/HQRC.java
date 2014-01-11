package team1024.rc;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class HQRC extends RC {

	private final RobotController rc;
	
	public HQRC(RobotController rc) {
		super(rc);
		this.rc = rc;
	}	

	public void spawn(Direction arg0) throws GameActionException {
		rc.spawn(arg0);
	}
}
