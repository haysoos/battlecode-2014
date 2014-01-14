package team1024.common;

import java.util.Arrays;
import java.util.List;

import team1024.rc.RC;
import team1024.rc.SoldierRC;
import team1024.worldinfo.WorldInfo;
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
					if (myPASTRs.contains(new MapLocation(i, j))) {
						continue;
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
