package team1024.action;

import team1024.common.Constants;
import team1024.communication.Message;
import team1024.communication.MessageIntent;
import team1024.rc.SoldierRC;
import team1024.worldinfo.WorldInfo;
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
