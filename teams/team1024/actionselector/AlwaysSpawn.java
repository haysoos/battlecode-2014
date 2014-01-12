package team1024.actionselector;

import java.util.ArrayList;
import java.util.List;

import team1024.action.Action;
import team1024.action.Spawn;
import team1024.goals.Goal;
import team1024.rc.HQRC;
import team1024.worldinfo.WorldInfo;

public class AlwaysSpawn implements ActionSelector {
	private final List<Action> spawnActionList = new ArrayList<Action>();
	
	public AlwaysSpawn(HQRC rc) {
		spawnActionList.add(new Spawn(rc));
	}
	
	@Override
	public List<Action> selectActions(WorldInfo info, List<Goal> goals) {
		return spawnActionList;
	}
}
