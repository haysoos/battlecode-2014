package team113.rc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import team113.common.Constants;
import team113.communication.Message;
import battlecode.common.GameActionException;
import battlecode.common.GameObject;
import battlecode.common.MapLocation;
import battlecode.common.Robot;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;
import battlecode.common.Team;
import battlecode.common.TerrainTile;

/** 
 * Wrapper to RobotController that provides a view to only the methods that
 * all robot types can call. 
 **/
public class RC {

	protected final RobotController rc;

	public RC(RobotController rc) {
		this.rc = rc;
	}

	public void attackRobot(RobotInfo robotInfo) {
		try {
			if (rc.isActive()) {
				rc.attackSquare(robotInfo.location);
			}
		} catch (GameActionException e) {
			printErrorMessage(e);
		}
	}

	public RobotInfo getWeakestEnemyInRange(Robot[] robots) {
		if (robots == null || robots.length == 0) {
			return null;
		}
		RobotInfo weakest = null;
		try {
			for (Robot robot : robots) {
				if (rc.canSenseObject(robot)) {
					RobotInfo robotInfo = rc.senseRobotInfo(robot);
					if (weakest == null) {
						weakest = robotInfo;
						continue;
					}
					if (robotInfo.health < weakest.health) {
						weakest = robotInfo;
					} else if (isNewRobotCloserThanWeakest(weakest, robotInfo)) {
						weakest = robotInfo;
					}
				}
			}
		} catch (GameActionException e) {
			printErrorMessage(e);
		}

		return weakest;
	}

	public RobotInfo getWeakestEnemyInRange(List<RobotInfo> robots) {
		if (robots == null || robots.size() == 0) {
			return null;
		}
		RobotInfo weakest = null;
		for (RobotInfo robot : robots) {

			if (weakest == null) {
				weakest = robot;
				continue;
			}
			if (robot.health < weakest.health) {
				weakest = robot;
			} else if (isNewRobotCloserThanWeakest(weakest, robot)) {
				weakest = robot;
			}
		}
		
		return weakest;
	}

	private boolean isNewRobotCloserThanWeakest(RobotInfo weakest, RobotInfo robotInfo) {
		return robotInfo.location.distanceSquaredTo(getLocation()) < weakest.location.distanceSquaredTo(getLocation());
	}

	public List<RobotInfo> senseNearbyEnemyRobots() {
		Robot[] robotsArray = rc.senseNearbyGameObjects(Robot.class, 10, rc.getTeam().opponent());
		List<Robot> robots = Arrays.asList(robotsArray);
		List<RobotInfo> robotInfo = new ArrayList<RobotInfo>();

		for (Robot robot : robots) {
			if (canSenseObject(robot)) {
				try {
					RobotInfo ri = rc.senseRobotInfo(robot);
					if (ri.type != RobotType.HQ) {
						robotInfo.add(ri);
					}
				} catch (GameActionException e) {
					printErrorMessage(e);
				}

			}
		}

		return robotInfo;

	}

	public MapLocation getLocation() {
		return rc.getLocation();
	}

	public void setRobotIndicator(int index, String indicatorMessage) {
		if (Constants.VERBOSE) {
			rc.setIndicatorString(index, indicatorMessage);
		}
	}

	public void printErrorMessage(GameActionException e) {
		setRobotIndicator(Constants.INDICATOR_GENERAL, e.getMessage());
		if (Constants.VERBOSE) {
			e.printStackTrace();
		}
	}

	public void addMatchObservation(String arg) {
		rc.addMatchObservation(arg);
	}

	public void breakpoint() {
		rc.breakpoint();
	}

	public void broadcast(int channel, int encodedMessage) {
		try {
			rc.broadcast(channel, encodedMessage);
			setIndicatorString(Constants.INDICATOR_MESSAGE, Message.decode(encodedMessage).toString());
		} catch (GameActionException e) {
			printErrorMessage(e);
		}
	}

	public boolean canSenseObject(GameObject arg0) {
		return rc.canSenseObject(arg0);
	}

	public boolean canSenseSquare(MapLocation arg0) {
		return rc.canSenseSquare(arg0);
	}

	public double getActionDelay() {
		return rc.getActionDelay();
	}

	public long getControlBits() {
		return rc.getControlBits();
	}

	public double getHealth() {
		return rc.getHealth();
	}

	public int getMapHeight() {
		return rc.getMapHeight();
	}

	public int getMapWidth() {
		return rc.getMapWidth();
	}

	public Robot getRobot() {
		return rc.getRobot();
	}

	public double getShields() {
		return rc.getShields();
	}

	public Team getTeam() {
		return rc.getTeam();
	}

	public long[] getTeamMemory() {
		return rc.getTeamMemory();
	}

	public RobotType getType() {
		return rc.getType();
	}

	public boolean isActive() {
		return rc.isActive();
	}

	public int readBroadcast(int arg0) throws GameActionException {
		return rc.readBroadcast(arg0);
	}

	public void resign() {
		rc.resign();
	}

	public int roundsUntilActive() {
		return rc.roundsUntilActive();
	}

	public void selfDestruct() throws GameActionException {
		rc.selfDestruct();
	}

	public Robot[] senseBroadcastingRobots() {
		return rc.senseBroadcastingRobots();
	}

	public Robot[] senseBroadcastingRobots(Team arg0) {
		return rc.senseBroadcastingRobots(arg0);
	}

	public double[][] senseCowGrowth() {
		return rc.senseCowGrowth();
	}

	public double senseCowsAtLocation(MapLocation arg0)
			throws GameActionException {
		return rc.senseCowsAtLocation(arg0);
	}

	public MapLocation senseEnemyHQLocation() {
		return rc.senseEnemyHQLocation();
	}

	public MapLocation senseHQLocation() {
		return rc.senseHQLocation();
	}

	public MapLocation senseLocationOf(GameObject arg0)
			throws GameActionException {
		return rc.senseLocationOf(arg0);
	}

	public <T extends GameObject> T[] senseNearbyGameObjects(Class<T> arg0) {
		return rc.senseNearbyGameObjects(arg0);
	}

	public <T extends GameObject> T[] senseNearbyGameObjects(Class<T> arg0,
			int arg1) {
		return rc.senseNearbyGameObjects(arg0, arg1);
	}

	public <T extends GameObject> T[] senseNearbyGameObjects(Class<T> arg0,
			int arg1, Team arg2) {
		return rc.senseNearbyGameObjects(arg0, arg1, arg2);
	}

	public <T extends GameObject> T[] senseNearbyGameObjects(Class<T> arg0,
			MapLocation arg1, int arg2, Team arg3) {
		return rc.senseNearbyGameObjects(arg0, arg1, arg2, arg3);
	}

	public GameObject senseObjectAtLocation(MapLocation arg0)
			throws GameActionException {
		return rc.senseObjectAtLocation(arg0);
	}

	public MapLocation[] sensePastrLocations(Team arg0) {
		return rc.sensePastrLocations(arg0);
	}

	public int senseRobotCount() {
		return rc.senseRobotCount();
	}

	public RobotInfo senseRobotInfo(Robot arg0) throws GameActionException {
		return rc.senseRobotInfo(arg0);
	}

	public TerrainTile senseTerrainTile(MapLocation arg0) {
		return rc.senseTerrainTile(arg0);
	}

	public void setIndicatorString(int arg0, String arg1) {
		if (Constants.VERBOSE) {
			rc.setIndicatorString(arg0, arg1);
		}		
	}

	public void setTeamMemory(int arg0, long arg1) {
		rc.setTeamMemory(arg0, arg1);
	}

	public void setTeamMemory(int arg0, long arg1, long arg2) {
		rc.setTeamMemory(arg0, arg1, arg2);
	}

	public void suicide() {
		rc.suicide();
	}

	public void wearHat() throws GameActionException {
		rc.wearHat();		
	}

	public void yield() {
		rc.yield();		
	}
}
