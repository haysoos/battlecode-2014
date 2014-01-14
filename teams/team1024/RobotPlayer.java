package team1024;

import java.util.ArrayList;
import java.util.List;

import team1024.action.Action;
import team1024.actionselector.ActionSelector;
import team1024.actionselector.AlwaysSpawn;
import team1024.actionselector.HarvestMilk;
import team1024.actionselector.LetsMakeSomeNoise;
import team1024.actionselector.SpoilerTheirMilk;
import team1024.common.Constants;
import team1024.goalmotor.GoalMotor;
import team1024.goals.Goal;
import team1024.rc.HQRC;
import team1024.rc.NoiseTowerRC;
import team1024.rc.RC;
import team1024.rc.SoldierRC;
import team1024.worldinfo.WorldInfo;
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
		
		GoalMotor goalMotor = makeGoalMotor();
		
		while (true) {
			List<Goal> goals = goalMotor.motorGoals(info);
			if (rc.isActive()) {
				List<Action> actions = actionSelector
						.selectActions(info, goals);

				for (Action action : actions) {
					action.performAction(info);
				}
			}
			rc.yield();
		}
	}

	private static GoalMotor makeGoalMotor() {
		GoalMotor goalMotor = new GoalMotor() {
			@Override
			public List<Goal> motorGoals(WorldInfo info) {
				return new ArrayList<Goal>();
			}
		};
		return goalMotor;
	}

	private static void lookSexy(RobotController rc) {
		while (true) {
			rc.yield();
		}
	}
}
