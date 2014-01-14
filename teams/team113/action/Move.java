package team113.action;

import java.util.Random;

import team113.rc.SoldierRC;
import team113.worldinfo.WorldInfo;
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
			action = 0;
			if (action < 50) {
				Direction moveDirection = rc.getLocation().directionTo(info.getGoalLocation());
				if (moveDirection == Direction.OMNI || moveDirection == Direction.NONE){
					return ;
				}
				int counter = 0;
				while (!rc.canMove(moveDirection)) {
					moveDirection = moveDirection.rotateRight();
					if (counter++ > 8) {
						return;
					}
				}
				if (rc.canMove(moveDirection) && rc.isActive()) {
					rc.sneak(moveDirection);
				}
				// Sneak towards the enemy
			} 
			//			else {
			//				Direction toEnemy = rc.getLocation().directionTo(
			//						rc.senseEnemyHQLocation());
			//				if (rc.canMove(toEnemy)) {
			//					rc.sneak(toEnemy);
			//				}
			//			}
		} catch (GameActionException e) {
			e.printStackTrace();
		}
	}
}
