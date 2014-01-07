package team113.pathing;

import team113.robots.BaseRobot;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class BugPathingAlgorithm implements PathingAlgorithm {

	private BaseRobot robot;

	public BugPathingAlgorithm(BaseRobot robot) {
		this.robot = robot;
	}

	@Override
	public void moveTowards(MapLocation mapLocation) {

		RobotController rc = robot.getRobotController();
		if (rc.isActive()) {

			Direction direction = rc.getLocation().directionTo(mapLocation);
			int counter = 0;
			if (!direction.equals(Direction.OMNI) &&
					!direction.equals(Direction.NONE)) {
				while (!rc.canMove(direction)) {
					direction.rotateRight();
					if (counter++ > 8) {
						break;
					}
				}
			} else {
				return ;
			}


			if (rc.canMove(direction)) {
				while (!rc.isActive()) {
					rc.yield();
				}
				try {
					rc.setIndicatorString(1, "Moving to " + rc.getLocation().add(direction));
					rc.move(direction);
					rc.yield();
				} catch (GameActionException e) {
					robot.printErrorMessage(e);
				}
			}

		}
	}

}
