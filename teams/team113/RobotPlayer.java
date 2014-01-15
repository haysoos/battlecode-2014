package team113;

import java.util.List;

import team113.action.Action;
import team113.actionselector.ActionSelector;
import team113.actionselector.AlwaysSpawn;
import team113.actionselector.HarvestMilk;
import team113.actionselector.LetsMakeSomeNoise;
import team113.actionselector.SpoilerTheirMilk;
import team113.common.Constants;
import team113.rc.HQRC;
import team113.rc.NoiseTowerRC;
import team113.rc.RC;
import team113.rc.SoldierRC;
import team113.worldinfo.WorldInfo;
import battlecode.common.RobotController;

public class RobotPlayer {

	public static void run(RobotController rc) {
		ActionSelector actionSelector = turnOnFluxCapacitor(rc);
		WorldInfo info = createTheHeavensAndTheEarth(actionSelector.getRC());
		runGoalsThroughMotor(rc, actionSelector, info);
	}

	private static ActionSelector turnOnFluxCapacitor(RobotController rc) {
		ActionSelector actionSelector = null;
		
		switch (rc.getType()) {
		case HQ:
			HQRC hqrc = new HQRC(rc);
			actionSelector = new AlwaysSpawn(hqrc);
			break;

		case SOLDIER:
			SoldierRC soldierRc = new SoldierRC(rc);
			
			if (rc.sensePastrLocations(rc.getTeam()).length >= 4) {
				actionSelector = new SpoilerTheirMilk(soldierRc);
				soldierRc.setIndicatorString(Constants.INDICATOR_GENERAL, "Spoiler");
			} else {
				actionSelector = new HarvestMilk(soldierRc);
				soldierRc.setIndicatorString(Constants.INDICATOR_GENERAL, "Harvester");
			}
			
			break;

		case NOISETOWER:
			NoiseTowerRC noiseTowerRc = new NoiseTowerRC(rc);
			actionSelector = new LetsMakeSomeNoise(noiseTowerRc);
			break;
		case PASTR:
			lookSexy(rc);

		default:
			throw new RuntimeException("Unknown type " + rc.getType());
		}
		return actionSelector;
	}

	private static WorldInfo createTheHeavensAndTheEarth(RC rc) {
		WorldInfo info = new WorldInfo(rc);
		return info;
	}

	private static void runGoalsThroughMotor(RobotController rc,
			ActionSelector actionSelector, WorldInfo info) {
		
		while (true) {
			
			if (rc.isActive()) {
				List<Action> actions = actionSelector
						.selectActions(info);

				for (Action action : actions) {
					action.performAction(info);
				}
			}
			rc.yield();
		}
	}	
	
	private static void lookSexy(RobotController rc) {
		while (true) {
			rc.yield();
		}
	}
}
