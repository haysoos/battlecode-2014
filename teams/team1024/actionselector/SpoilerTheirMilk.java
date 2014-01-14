package team1024.actionselector;

import java.util.ArrayList;
import java.util.List;

import team1024.action.Action;
import team1024.action.Attack;
import team1024.action.Broadcast;
import team1024.action.ConstructNoiseTower;
import team1024.action.Move;
import team1024.action.SoldierBroadcast;
import team1024.common.Util;
import team1024.goals.Goal;
import team1024.rc.RC;
import team1024.rc.SoldierRC;
import team1024.worldinfo.WorldInfo;
import battlecode.common.MapLocation;

public class SpoilerTheirMilk implements ActionSelector {

	private SoldierRC rc;
	private final Attack attack;
	private final Broadcast<SoldierRC> broadcast;
	private final Move move;
	private final Action constructNoiseTower;

	public SpoilerTheirMilk(SoldierRC rc) {
		this.rc = rc;
		attack = new Attack(rc);
		broadcast = new SoldierBroadcast(rc);
		move = new Move(rc);
		constructNoiseTower = new ConstructNoiseTower(rc);
	}

	@Override
	public List<Action> selectActions(WorldInfo info, List<Goal> goals) {
		List<Action> actions = new ArrayList<Action>();
		info.updateWorldInfo();
		
		if (Util.areEnemiesNearby(info)) {
			actions.add(attack);
			actions.add(broadcast);
		} else if (rc.getLocation().distanceSquaredTo(info.getGoalLocation()) < 300) {
			actions.add(constructNoiseTower);
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
