package team113.action;

import team113.communication.MapLocationMessage;
import team113.communication.MapLocationMessage.MessageIntent;
import team113.communication.MessageTransmitter;
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
			MapLocationMessage message = new MapLocationMessage();
			message.setMapLocation(weakestEnemy.location);
			message.setMessageIntent(MessageIntent.ATTACK);
			MessageTransmitter.INSTANCE.broadcast(rc, message);
		}		
	}

}
