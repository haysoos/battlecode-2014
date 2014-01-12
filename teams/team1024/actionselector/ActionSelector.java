package team1024.actionselector;

import java.util.List;

import team1024.action.Action;
import team1024.goals.Goal;
import team1024.worldinfo.WorldInfo;

public interface ActionSelector {
	public List<Action> selectActions(WorldInfo info, List<Goal> goals);
}
