package team1024.action;

import team1024.worldinfo.WorldInfo;

public abstract class Broadcast<R> implements Action {
	
	protected R rc;

	public Broadcast(R rc) {
		this.rc = rc;
	}
	
	@Override
	public void performAction(WorldInfo info) {
		broadcast(info);
	}

	public abstract void broadcast(WorldInfo info);	
	
}
