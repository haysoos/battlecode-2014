package team1024.worldinfo;

import battlecode.common.Robot;

public class WorldInfo {

	private Robot[] nearbyEnemies;

	public void setNearbyEnemies(Robot[] nearbyEnemies) {
		this.nearbyEnemies = nearbyEnemies;
	}

	public Robot[] getNearbyEnemies() {
		return nearbyEnemies;
	}

}
