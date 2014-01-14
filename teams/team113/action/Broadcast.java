package team113.action;

import team113.worldinfo.WorldInfo;

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
