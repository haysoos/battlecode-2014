package team1024.actionselector;

import java.util.ArrayList;
import java.util.List;

import team1024.action.Action;
import team1024.action.Attack;
import team1024.action.Broadcast;
import team1024.action.ConstructPASTR;
import team1024.action.Move;
import team1024.action.SoldierBroadcast;
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
		
		if (info.getNearbyEnemies().size() > 0) {
			result.add(attack);
			result.add(broadcast);
		} else if (reachedGoal(info.getGoalLocation())) {
			result.add(constructPASTR);
		} else {
			MapLocation mostMilk = findMapLocationWithMostMilk();
			info.setGoalLocation(mostMilk);
			result.add(move);
		}		
		
		return result;
	}

	private boolean reachedGoal(MapLocation goalLocation) {
		return goalLocation.equals(rc.getLocation());
	}

	private MapLocation findMapLocationWithMostMilk() {
		
		double[][] milk = rc.senseCowGrowth();
		
		int x = 0, y = 0;
		double maxMilk = Double.MIN_VALUE;
		for (int i=0; i<milk.length; i++) {
			for (int j=0; j<milk[i].length; j++) {
				if (milk[i][j] > maxMilk) {
					x = i;
					y = j;
					maxMilk = milk[i][j];
				}
			}
		}
		
		return new MapLocation(x, y);
	}

	@Override
	public RC getRC() {
		return rc;
	}

}
