package team113.rc;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class SoldierRC extends RC {
	
	public SoldierRC(RobotController rc) {
		super(rc);
	}

	public void attackSquare(MapLocation arg0) throws GameActionException {
		rc.attackSquare(arg0);
	}

	public void attackSquareLight(MapLocation arg0) throws GameActionException {
		rc.attackSquareLight(arg0);
	}

	public boolean canAttackSquare(MapLocation arg0) {
		return rc.canAttackSquare(arg0);
	}

	public boolean canMove(Direction arg0) {
		if (arg0 == Direction.OMNI || arg0 == Direction.NONE) {
			return false;
		}
		return rc.canMove(arg0);
	}

	public void construct(RobotType arg0) throws GameActionException {
		rc.construct(arg0);
	}

	public int getConstructingRounds() {
		return rc.getConstructingRounds();
	}

	public RobotType getConstructingType() {
		return rc.getConstructingType();
	}

	public boolean isConstructing() {
		return rc.isConstructing();		
	}

	public void move(Direction arg0) throws GameActionException {
		rc.move(arg0);
	}

	public void sneak(Direction arg0) throws GameActionException {
		rc.sneak(arg0);
	}
}
