package team1024.action;

import java.util.Random;

import team1024.rc.SoldierRC;
import team1024.worldinfo.WorldInfo;
import battlecode.common.Direction;
import battlecode.common.GameActionException;

public class Move implements Action {
	private static final Direction[] directions = Direction.values();
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
