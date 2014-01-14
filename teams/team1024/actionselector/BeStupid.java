package team1024.actionselector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import team1024.action.Action;
import team1024.action.Attack;
import team1024.action.Broadcast;
import team1024.action.Move;
import team1024.action.SoldierBroadcast;
import team1024.goals.Goal;
import team1024.rc.RC;
import team1024.rc.SoldierRC;
import team1024.worldinfo.WorldInfo;

public class BeStupid implements ActionSelector {
	private final Random rand = new Random();
	
	private final Move move;
	private final Attack attack;
	private final Broadcast<SoldierRC> broadcast;
	private SoldierRC rc;
	
	public BeStupid(SoldierRC rc) {
		this.rc = rc;
		move = new Move(rc);
		attack = new Attack(rc);
		broadcast = new SoldierBroadcast(rc);
	}

	@Override
	public List<Action> selectActions(WorldInfo info, List<Goal> goals) {
		List<Action> result = new ArrayList<Action>();
		if (rand.nextBoolean()) {
			result.add(move);
		} else {
			result.add(attack);
		}
		result.add(broadcast);
		return result;
	}

	@Override
	public RC getRC() {
		return rc;
	}

}
