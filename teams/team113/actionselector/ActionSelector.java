package team113.actionselector;

import java.util.List;

import team113.action.Action;
import team113.rc.RC;
import team113.worldinfo.WorldInfo;

public interface ActionSelector {
	public List<Action> selectActions(WorldInfo info);
	public RC getRC();
}
