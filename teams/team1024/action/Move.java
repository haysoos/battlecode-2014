package team1024.action;

import java.util.Random;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import team1024.RobotPlayer.WorldInfo;
import team1024.rc.SoldierRC;

public class Move implements Action {
	private static final Direction[] directions = { Direction.NORTH,
			Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST,
			Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST,
			Direction.NORTH_WEST };

	private final Random rand = new Random();

	private final SoldierRC rc;

	public Move(SoldierRC rc) {
		this.rc = rc;
	}

	@Override
	public void performAction(WorldInfo info) {
		try {
			int action = (rc.getRobot().getID() * rand.nextInt(101) + 50) % 101;
			if (action < 50) {
				Direction moveDirection = directions[rand.nextInt(8)];
				if (rc.canMove(moveDirection)) {
					rc.move(moveDirection);
				}
				// Sneak towards the enemy
			} else {
				Direction toEnemy = rc.getLocation().directionTo(
						rc.senseEnemyHQLocation());
				if (rc.canMove(toEnemy)) {
					rc.sneak(toEnemy);
				}
			}
		} catch (GameActionException e) {
			e.printStackTrace();
		}
	}
}
