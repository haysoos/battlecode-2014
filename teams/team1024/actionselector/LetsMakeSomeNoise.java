package team1024.actionselector;

import java.util.ArrayList;
import java.util.List;

import team1024.action.Action;
import team1024.action.MakeSomeNoise;
import team1024.goals.Goal;
import team1024.rc.NoiseTowerRC;
import team1024.rc.RC;
import team1024.worldinfo.WorldInfo;

public class LetsMakeSomeNoise implements ActionSelector {

	private NoiseTowerRC rc;
	private Action makeSomeNoise;

	public LetsMakeSomeNoise(NoiseTowerRC rc) {
		this.rc = rc;
		makeSomeNoise = new MakeSomeNoise(rc);
	}

	@Override
	public List<Action> selectActions(WorldInfo info, List<Goal> goals) {
		List<Action> actions = new ArrayList<Action>();
		info.updateWorldInfo();
		actions.add(makeSomeNoise);
		
		return actions;
	}

	@Override
	public RC getRC() {
		return rc;
	}

}
