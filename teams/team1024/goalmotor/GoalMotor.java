package team1024.goalmotor;

import java.util.List;

import team1024.goals.Goal;
import team1024.worldinfo.WorldInfo;

public interface GoalMotor {
	// Update WorldInfo and select goals based on new state of world.
	List<Goal> motorGoals(WorldInfo info);
}
