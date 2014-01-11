package team1024;

import java.util.ArrayList;
import java.util.List;

import team1024.goalmotors.HQGoalMotor;
import team1024.rc.HQRC;
import team1024.rc.SoldierRC;
import battlecode.common.RobotController;

public class RobotPlayer {
	
	public static void run(RobotController rc) {
		GoalMotor goalMotor;
		ActionSelector actionSelector = new ActionSelector() {
			@Override
			public List<Action> selectActions(WorldInfo info, List<Goal> goals) {			
				return new ArrayList<Action>();
			}
		};
		
		switch (rc.getType()) {
		case HQ:		 
			HQRC hqRc = new HQRC(rc);
			goalMotor = new HQGoalMotor(hqRc);			
			break;
			
		case SOLDIER:
			final SoldierRC soldierRc = new SoldierRC(rc);
			goalMotor = new GoalMotor() {
				@Override
				public List<Goal> motorGoals(WorldInfo info) {
					return new ArrayList<Goal>();
				}
				
			};
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
		     List<Action> actions = actionSelector.selectActions(info, goals);
		     for (Action action : actions) {
		       action.performAction(info);
		     }
		}
	}

	// TODO Move these to a their own files once defined.
	public static class WorldInfo {
		
	}
		
	public static class Goal {
		
	}	
}
