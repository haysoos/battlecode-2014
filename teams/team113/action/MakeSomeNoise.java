package team113.action;

import team113.rc.NoiseTowerRC;
import team113.worldinfo.WorldInfo;
import battlecode.common.MapLocation;

public class MakeSomeNoise implements Action {

	private NoiseTowerRC rc;

	public MakeSomeNoise(NoiseTowerRC rc) {
		this.rc = rc;
	}

	@Override
	public void performAction(WorldInfo info) {
		MapLocation[] enemyPastrs = rc.sensePastrLocations(rc.getTeam().opponent());
		for (MapLocation pastr : enemyPastrs) {
			if (rc.getLocation().distanceSquaredTo(pastr) < 40) {
				rc.attackLoud(pastr);				
			}
		}
	}

}
