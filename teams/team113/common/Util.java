package team113.common;

import java.util.Arrays;
import java.util.List;

import team113.rc.RC;
import team113.rc.SoldierRC;
import team113.worldinfo.WorldInfo;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;

public final class Util {

	private Util() {}

	public static boolean areEnemiesNearby(WorldInfo info) {
		return info.getNearbyEnemies() != null && info.getNearbyEnemies().size() > 0;
	}

	public static MapLocation findMapLocationWithMostMilk(RC rc) {

		double[][] milk = rc.senseCowGrowth();
		List<MapLocation> myPASTRs = Arrays.asList(rc.sensePastrLocations(rc.getTeam()));

		int x = 0, y = 0;
		double maxMilk = Double.MIN_VALUE;
		for (int i=0; i<milk.length; i++) {
			for (int j=0; j<milk[i].length; j++) {
				if (milk[i][j] > maxMilk) {
					rc.setIndicatorString(Constants.INDICATOR_PATHING, new MapLocation(i, j).toString());
					MapLocation temp = new MapLocation(i, j);
					try {
						if (myPASTRs.contains(temp) || (rc.canSenseSquare(temp) && rc.senseObjectAtLocation(temp) != null)) {
							continue;
						}
					} catch (GameActionException e) {
						rc.printErrorMessage(e);
					}
					x = i;
					y = j;
					maxMilk = milk[i][j];
				}
			}
		}

		return new MapLocation(x, y);
	}

	public static MapLocation findEnemyMapLocationWithMostMilk(SoldierRC rc) {
		MapLocation[] enemyPASTRs = rc.sensePastrLocations(rc.getTeam().opponent());
		if (enemyPASTRs.length == 0) {
			return rc.senseEnemyHQLocation();
		}
		MapLocation closest = enemyPASTRs[0];
		for (MapLocation pastr : enemyPASTRs) {
			if (rc.getLocation().distanceSquaredTo(pastr) < closest.distanceSquaredTo(rc.getLocation())) {
				closest = pastr;
			}
		}
		
		return closest;
	}

}
