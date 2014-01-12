package team1024.actionselector;

import java.util.List;

import team1024.RobotPlayer.Goal;
import team1024.RobotPlayer.WorldInfo;
import team1024.action.Action;

public interface ActionSelector {
	List<Action> selectActions(WorldInfo info, List<Goal> goals);
}
