package team1024;

import java.util.ArrayList;
import java.util.List;

import team1024.action.Action;
import team1024.actionselector.ActionSelector;
import team1024.actionselector.AlwaysSpawn;
import team1024.actionselector.BeStupid;
import team1024.goalmotor.GoalMotor;
import team1024.rc.HQRC;
import team1024.rc.SoldierRC;
import battlecode.common.RobotController;

public class RobotPlayer {

	public static void run(RobotController rc) {
		GoalMotor goalMotor = new GoalMotor() {
			@Override
			public List<Goal> motorGoals(WorldInfo info) {
				return new ArrayList<Goal>();
			}
		};
		ActionSelector actionSelector = new ActionSelector() {
			@Override
			public List<Action> selectActions(WorldInfo info, List<Goal> goals) {
				return new ArrayList<Action>();
			}
		};

		switch (rc.getType()) {
		case HQ:
			HQRC hqRc = new HQRC(rc);
			actionSelector = new AlwaysSpawn(hqRc);
			break;

		case SOLDIER:
			SoldierRC soldierRc = new SoldierRC(rc);
			actionSelector = new BeStupid(soldierRc);
			break;

		case NOISETOWER:
		case PASTR:
			while (true) {
				rc.yield();
			}

		default:
			throw new RuntimeException("Unknown type " + rc.getType());
		}

		WorldInfo info = null;
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

	// TODO Move these to a their own files once defined.
	public static class WorldInfo {

	}

	public static class Goal {

	}
}
