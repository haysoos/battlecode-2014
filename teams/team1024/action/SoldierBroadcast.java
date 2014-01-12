package team1024.action;

import battlecode.common.Robot;
import battlecode.common.RobotInfo;
import team1024.common.Constants;
import team1024.communication.Message;
import team1024.communication.MessageIntent;
import team1024.rc.SoldierRC;
import team1024.worldinfo.WorldInfo;

public class SoldierBroadcast extends Broadcast<SoldierRC> {

	public SoldierBroadcast(SoldierRC rc) {
		super(rc);
	}

	@Override
	public void broadcast(WorldInfo info) {
		Robot[] enemies = info.getNearbyEnemies();
		RobotInfo weakestEnemy = rc.getWeakestEnemyInRange(enemies);
		if (weakestEnemy != null) {
			Message message = new Message();
			message.setMapLocation(weakestEnemy.location);
			message.setMessageIntent(MessageIntent.ATTACK);
			rc.broadcast(Constants.BROADCAST_CHANNEL, message.encode());
		}		
	}

}
