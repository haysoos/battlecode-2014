package team1024.goalmotors;

import java.util.ArrayList;
import java.util.List;

import team1024.GoalMotor;
import team1024.RobotPlayer.Goal;
import team1024.RobotPlayer.WorldInfo;
import team1024.rc.HQRC;

public class HQGoalMotor implements GoalMotor {
	
	private final HQRC rc;
	
	public HQGoalMotor(HQRC rc) {
		this.rc = rc;
	}

	@Override
	public List<Goal> motorGoals(WorldInfo info) {
		return new ArrayList<Goal>();
	}

}
