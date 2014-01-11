package team1024;

import java.util.List;

import team1024.RobotPlayer.Goal;
import team1024.RobotPlayer.WorldInfo;

public interface ActionSelector {
	List<Action> selectActions(WorldInfo info, List<Goal> goals);
}
