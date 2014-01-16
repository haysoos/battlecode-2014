package team113.actionselector;

import java.util.ArrayList;
import java.util.List;

import team113.action.Action;
import team113.action.Attack;
import team113.action.Broadcast;
import team113.action.ConstructNoiseTower;
import team113.action.Move;
import team113.action.SoldierBroadcast;
import team113.common.Util;
import team113.rc.RC;
import team113.rc.SoldierRC;
import team113.worldinfo.WorldInfo;
import battlecode.common.MapLocation;

public class SpoilerTheirMilk implements ActionSelector {

	private SoldierRC rc;
	private final Attack attack;
	private final Broadcast<SoldierRC> broadcast;
	private final Move move;
	@SuppressWarnings("unused")
	private final Action constructNoiseTower;

	public SpoilerTheirMilk(SoldierRC rc) {
		this.rc = rc;
		attack = new Attack(rc);
		broadcast = new SoldierBroadcast(rc);
		move = new Move(rc);
		constructNoiseTower = new ConstructNoiseTower(rc);
	}

	@Override
	public List<Action> selectActions(WorldInfo info) {
		List<Action> actions = new ArrayList<Action>();
		info.updateWorldInfo();
		
		if (Util.areEnemiesNearby(info)) {
			actions.add(attack);
			actions.add(broadcast);
//		} else if (rc.getLocation().distanceSquaredTo(info.getGoalLocation()) < 300) {
//			actions.add(constructNoiseTower);
		} else {
			MapLocation mostMilk = Util.findEnemyMapLocationWithMostMilk(rc);
			info.setGoalLocation(mostMilk);
			actions.add(move);
		}	
		
		return actions;
	}

	

	@Override
	public RC getRC() {
		return rc;
	}

}
