package team1024.actionselector;

import java.util.ArrayList;
import java.util.List;

import team1024.action.Action;
import team1024.action.Attack;
import team1024.action.Broadcast;
import team1024.action.ConstructNoiseTower;
import team1024.action.ConstructPASTR;
import team1024.action.Move;
import team1024.action.SoldierBroadcast;
import team1024.common.Util;
import team1024.goals.Goal;
import team1024.rc.RC;
import team1024.rc.SoldierRC;
import team1024.worldinfo.WorldInfo;
import battlecode.common.MapLocation;

public class HarvestMilk implements ActionSelector {

	private final Move move;
	private final Attack attack;
	private final Broadcast<SoldierRC> broadcast;
	private SoldierRC rc;
	private final Action constructPASTR;
	
	public HarvestMilk(SoldierRC rc) {
		this.rc = rc;
		move = new Move(rc);
		attack = new Attack(rc);
		broadcast = new SoldierBroadcast(rc);
		constructPASTR = new ConstructPASTR(rc);
		
	}
	
	@Override
	public List<Action> selectActions(WorldInfo info, List<Goal> goals) {
		List<Action> result = new ArrayList<Action>();
		info.updateWorldInfo();
		
		if (Util.areEnemiesNearby(info)) {
			result.add(attack);
			result.add(broadcast);
		} else if (reachedGoal(info.getGoalLocation())) {
			result.add(constructPASTR);
		} else {
			MapLocation mostMilk = Util.findMapLocationWithMostMilk(rc);
			info.setGoalLocation(mostMilk);
			result.add(move);
		}		
		
		return result;
	}

	private boolean reachedGoal(MapLocation goalLocation) {
		return goalLocation.distanceSquaredTo(rc.getLocation()) < 2;
	}

	

	@Override
	public RC getRC() {
		return rc;
	}

}
