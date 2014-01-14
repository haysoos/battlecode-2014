package team1024.action;

import battlecode.common.GameActionException;
import battlecode.common.RobotType;
import team1024.action.Action;
import team1024.rc.SoldierRC;
import team1024.worldinfo.WorldInfo;

public class ConstructPASTR implements Action {

	private SoldierRC rc;

	public ConstructPASTR(SoldierRC rc) {
		this.rc = rc;
	}

	@Override
	public void performAction(WorldInfo info) {
		
		if (!rc.isConstructing()) {
			try {
				rc.construct(RobotType.PASTR);
			} catch (GameActionException e) {
				rc.printErrorMessage(e);
			}		
		}
		
	}

}
