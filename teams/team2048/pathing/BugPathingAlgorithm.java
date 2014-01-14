package team2048.pathing;

import team2048.robots.BaseRobot;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class BugPathingAlgorithm implements PathingAlgorithm {

	private static final int MAX_DIRECTIONS = 8;
	private BaseRobot robot;
	private boolean rotateRight = true;
	private Direction lastDirection;
	private RobotController rc;
	private boolean stuck;
	private MapLocation stuckLocation;

	public BugPathingAlgorithm(BaseRobot robot) {
		this.robot = robot;
		rc = robot.getRobotController();
	}

	@Override
	public void moveTowards(MapLocation mapLocation) {
		Direction direction = rc.getLocation().directionTo(mapLocation);
		if (canMakeProgress(direction)) {
			moveToDirection(rc, direction);
			stuck = false;
			return ;
		} else {
			stuck = true;
			stuckLocation = rc.getLocation();
		}
		
		if (stuck) {
			int counter = 0;
			if (!direction.equals(Direction.OMNI) &&
					!direction.equals(Direction.NONE)) {
				while (!canMakeProgress(direction)) {
					if (rotateRight) {
						direction = direction.rotateRight();
					} else {
						direction = direction.rotateLeft();
					}
					if (counter++ > MAX_DIRECTIONS) {
						break;
					}
				}
			}
			
			MapLocation movingTo = rc.getLocation().add(direction);
			if (movingTo.equals(stuckLocation)) {
				rotateRight = !rotateRight;
				return;
			}
			if (canMakeProgress(direction)) {
				moveToDirection(rc, direction);
			}
		}
		
	}

	private boolean canMakeProgress(Direction direction) {
		if (rc.canMove(direction)) {
			if (lastDirection == null) {
				return true;
			}
			Direction opposite = getOppositeDirection(lastDirection);
			if (direction.equals(opposite) || direction.equals(opposite.rotateLeft()) ||
					direction.equals(opposite.rotateRight())) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	private Direction getOppositeDirection(Direction direction) {
		return direction.rotateRight().rotateRight().rotateRight().rotateRight();
	}

	private void moveToDirection(RobotController rc, Direction direction) {
		while (!rc.isActive()) {
			rc.yield();
		}
		try {
			rc.setIndicatorString(1, "Moving to " + rc.getLocation().add(direction) + " Last stuck at " + stuckLocation + " Last Direction " + lastDirection);
			rc.sneak(direction);
			lastDirection = direction;
			rc.yield();
		} catch (GameActionException e) {
			robot.printErrorMessage(e);
		}
	}

}
