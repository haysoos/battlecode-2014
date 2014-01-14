package team1024.worldinfo;

import java.util.List;

import team1024.rc.RC;
import battlecode.common.MapLocation;
import battlecode.common.RobotInfo;

public class WorldInfo {

	private MapSize mapSize;
	private List<RobotInfo> nearbyEnemies;
	private RC rc;
	private int distanceToEnemyBase;
	private int distanceToBase;
	private MapLocation goalLocation;

	public MapSize getMapSize() {
		return mapSize;
	}

	public int getDistanceToEnemyBase() {
		return distanceToEnemyBase;
	}

	public int getDistanceToBase() {
		return distanceToBase;
	}

	public WorldInfo(RC rc) {
		this.rc = rc;
		setInitialGoal();
		determineMapSize();
	}

	private void setInitialGoal() {
		this.goalLocation = rc.senseEnemyHQLocation();		
	}

	public void updateWorldInfo() {
		distanceToEnemyBase = rc.getLocation().distanceSquaredTo(rc.senseEnemyHQLocation());
		distanceToBase = rc.getLocation().distanceSquaredTo(rc.senseHQLocation());
		nearbyEnemies = rc.senseNearbyEnemyRobots();
	}

	private void determineMapSize() {
		if (rc.getMapHeight() < 30 || rc.getMapWidth() < 30) {
			mapSize = MapSize.SMALL;
		} else if (rc.getMapHeight() < 50 || rc.getMapWidth() < 50) {
			mapSize = MapSize.MEDIUM;
		} else {
			mapSize = MapSize.LARGE;
		}
	}

	public List<RobotInfo> getNearbyEnemies() {
		return nearbyEnemies;
	}
	
	public void setGoalLocation(MapLocation goalLocation) {
		this.goalLocation = goalLocation;
	}
	
	public MapLocation getGoalLocation() {
		return goalLocation;
	}

}
