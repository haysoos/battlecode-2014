package team113.actionselector;

import java.util.ArrayList;
import java.util.List;

import team113.action.Action;
import team113.action.MakeSomeNoise;
import team113.goals.Goal;
import team113.rc.NoiseTowerRC;
import team113.rc.RC;
import team113.worldinfo.WorldInfo;

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
