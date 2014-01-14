package team1024.action;

import battlecode.common.GameActionException;
import battlecode.common.RobotType;
import team1024.common.Constants;
import team1024.rc.SoldierRC;
import team1024.worldinfo.WorldInfo;

public class ConstructNoiseTower implements Action {

	private SoldierRC rc;

	public ConstructNoiseTower(SoldierRC rc) {
		this.rc = rc;
	}
	
	@Override
	public void performAction(WorldInfo info) {
		if (!rc.isConstructing()) {
			try {
				rc.construct(RobotType.NOISETOWER);
				rc.setIndicatorString(Constants.INDICATOR_GENERAL,"I am constructing");
			} catch (GameActionException e) {
				rc.printErrorMessage(e);
			}		
		} else {
			rc.setIndicatorString(Constants.INDICATOR_GENERAL,"I am not constructing");
		}
	}

}
