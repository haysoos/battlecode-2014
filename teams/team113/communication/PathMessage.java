package team113.communication;

import team113.pathfinding.Path;
import battlecode.common.MapLocation;

public class PathMessage implements MessagePayload {
	
	@SuppressWarnings("unused")
	private final Path<MapLocation> path = null;

	@Override
	public void decode(int[] encodedMessage) {
	}

	@Override
	public int[] encode() {
		return null;
	}

	@Override
	public PayloadType getPayloadType() {
		return PayloadType.PATH;
	}

}
