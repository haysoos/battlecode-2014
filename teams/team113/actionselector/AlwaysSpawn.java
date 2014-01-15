package team113.actionselector;

import java.util.ArrayList;
import java.util.List;

import team113.action.Action;
import team113.action.Spawn;
import team113.rc.HQRC;
import team113.rc.RC;
import team113.worldinfo.WorldInfo;

public class AlwaysSpawn implements ActionSelector {
	private final List<Action> spawnActionList = new ArrayList<Action>();
	private HQRC rc;
	
	public AlwaysSpawn(HQRC rc) {
		this.rc = rc;
		spawnActionList.add(new Spawn(rc));
	}
	
	@Override
	public List<Action> selectActions(WorldInfo info) {
		return spawnActionList;
	}

	@Override
	public RC getRC() {
		return rc;
	}
}
