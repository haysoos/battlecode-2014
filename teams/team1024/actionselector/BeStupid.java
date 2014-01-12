package team1024.actionselector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import team1024.RobotPlayer.Goal;
import team1024.RobotPlayer.WorldInfo;
import team1024.action.Action;
import team1024.action.Attack;
import team1024.action.Move;
import team1024.rc.SoldierRC;

public class BeStupid implements ActionSelector {
	private final Random rand = new Random();
	
	private final Move move;
	private final Attack attack;
	
	public BeStupid(SoldierRC rc) {
		move = new Move(rc);
		attack = new Attack(rc);
	}

	@Override
	public List<Action> selectActions(WorldInfo info, List<Goal> goals) {
		List<Action> result = new ArrayList<Action>();
		if (rand.nextBoolean()) {
			result.add(move);
		} else {
			result.add(attack);
		}
		return result;
	}

}
