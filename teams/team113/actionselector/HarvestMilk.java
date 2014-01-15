package team113.actionselector;

import java.util.ArrayList;
import java.util.List;

import team113.action.Action;
import team113.action.Attack;
import team113.action.Broadcast;
import team113.action.ConstructPASTR;
import team113.action.Move;
import team113.action.SoldierBroadcast;
import team113.common.Util;
import team113.rc.RC;
import team113.rc.SoldierRC;
import team113.worldinfo.WorldInfo;
import battlecode.common.GameActionException;
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
	public List<Action> selectActions(WorldInfo info) {
		List<Action> result = new ArrayList<Action>();
		info.updateWorldInfo();

		if (Util.areEnemiesNearby(info)) {
			result.add(attack);
			result.add(broadcast);
		} else if (reachedGoal(info.getGoalLocation())) {
			result.add(constructPASTR);
		} else {

			if (rc.canSenseSquare(info.getGoalLocation())) {
				try {
					if (rc.senseObjectAtLocation(info.getGoalLocation()) != null) {
						MapLocation attackLocation = info.getGoalLocation().add(2, 2);
						if (rc.canAttackSquare(attackLocation)) {
							rc.attackSquare(attackLocation);
						}
						
					}
				} catch (GameActionException e) {
					rc.printErrorMessage(e);
				}
			}
			if (rc.sensePastrLocations(rc.getTeam()).length >= 4 ||
					rc.sensePastrLocations(rc.getTeam().opponent()).length > 1) {
				MapLocation enemyPastr = Util.findEnemyMapLocationWithMostMilk(rc);
				info.setGoalLocation(enemyPastr);
			} else {
				MapLocation mostMilk = Util.findMapLocationWithMostMilk(rc);
				info.setGoalLocation(mostMilk);
			}
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
