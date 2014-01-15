package team113.stateofmind;

import team113.action.MakeSomeNoise;
import team113.rc.NoiseTowerRC;

public class MakeNoise implements StateOfMind {
	
	private final MakeSomeNoise makeNoise;
	
	public MakeNoise(NoiseTowerRC rc) {
		makeNoise = new MakeSomeNoise(rc);
	}

	@Override
	public StateOfMind think() {
		// Nothing to think about.
		return this;
	}

	@Override
	public void act() {
		makeNoise.performAction(null);
	}
}
