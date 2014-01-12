package team1024.action;

import battlecode.common.Direction;
import team1024.RobotPlayer.WorldInfo;
import team1024.rc.HQRC;

public final class Spawn implements Action {
	
	private final HQRC rc;
	
	public Spawn(HQRC rc) {
		this.rc = rc;
	}

	@Override
	public void performAction(WorldInfo info) {
		try {					
			//Check if a robot is spawnable and spawn one if it is.
			if (rc.isActive() && rc.senseRobotCount() < 25) {
				Direction toEnemy = rc.getLocation().directionTo(rc.senseEnemyHQLocation());
				if (rc.senseObjectAtLocation(rc.getLocation().add(toEnemy)) == null) {
					rc.spawn(toEnemy);
				}
			}
		} catch (Exception e) {
			System.out.println("HQ Exception: " + e.getMessage());
		}
	}

}
