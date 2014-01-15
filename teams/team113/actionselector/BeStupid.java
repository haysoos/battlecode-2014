package team113.actionselector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import team113.action.Action;
import team113.action.Attack;
import team113.action.Broadcast;
import team113.action.Move;
import team113.action.SoldierBroadcast;
import team113.rc.RC;
import team113.rc.SoldierRC;
import team113.worldinfo.WorldInfo;

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
	public List<Action> selectActions(WorldInfo info) {
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
