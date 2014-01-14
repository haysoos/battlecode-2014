package team113.action;

import team113.common.Constants;
import team113.communication.Message;
import team113.communication.MessageIntent;
import team113.rc.SoldierRC;
import team113.worldinfo.WorldInfo;
import battlecode.common.RobotInfo;

public class SoldierBroadcast extends Broadcast<SoldierRC> {

	public SoldierBroadcast(SoldierRC rc) {
		super(rc);
	}

	@Override
	public void broadcast(WorldInfo info) {
		RobotInfo weakestEnemy = rc.getWeakestEnemyInRange(info.getNearbyEnemies());
		if (weakestEnemy != null) {
			Message message = new Message();
			message.setMapLocation(weakestEnemy.location);
			message.setMessageIntent(MessageIntent.ATTACK);
			rc.broadcast(Constants.BROADCAST_CHANNEL, message.encode());
		}		
	}

}
