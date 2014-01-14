package team113.goalmotor;

import java.util.List;

import team113.goals.Goal;
import team113.worldinfo.WorldInfo;

public interface GoalMotor {
	// Update WorldInfo and select goals based on new state of world.
	List<Goal> motorGoals(WorldInfo info);
}
