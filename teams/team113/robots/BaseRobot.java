package team113.robots;

import java.util.PriorityQueue;
import java.util.Queue;

import team113.common.Constants;
import team113.communication.Message;
import team113.pathing.PathingAlgorithm;
import team113.states.State;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.Robot;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;

public abstract class BaseRobot {

	protected RobotController rc;
	private Queue<Message> priorityQueue;
	private PathingAlgorithm pathingAlgorithm;
	private State state;

	public BaseRobot(RobotController rc) {
		this.rc = rc;
		priorityQueue = new PriorityQueue<Message>();
	}

	public void runProgram() {
		while (true) {
			listenToBroadcasts();
			checkForNearbyEnemy();
			analyzeGameState();
			performHighestPriorityTask();
		}
	}

	public void broadcast(Message message) {
		try {
			rc.broadcast(Constants.BROADCAST_CHANNEL, message.encode());
			if (Constants.VERBOSE) {
				rc.setIndicatorString(Constants.INDICATOR_MESSAGE, message.toString());
			}
		} catch (GameActionException e) {
			e.printStackTrace();
		}

	}

	public void listenToBroadcasts() {
		try {
			int encodedMessage = rc.readBroadcast(Constants.BROADCAST_CHANNEL);
			Message decodedMessage = Message.decode(encodedMessage);
			priorityQueue.add(decodedMessage);
		} catch (GameActionException e) {
			printErrorMessage(e);
		}
	}

	public void moveTowardsTarget(MapLocation mapLocation) {
		if (rc.isActive()) {
			pathingAlgorithm.moveTowards(mapLocation);
		}
	}

	abstract public void enemyInSightAction(RobotInfo robotInfo);

	public boolean isEnemyInRange() {
		Robot[] robots = senseNearbyEnemyRobots();
		return robots.length > 0;
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
			weakest = rc.senseRobotInfo(robots[0]);
			for (Robot robot : robots) {
				RobotInfo robotInfo = rc.senseRobotInfo(robot);
				if (robotInfo.health < weakest.health) {
					weakest = robotInfo;
				}
			}
		} catch (GameActionException e) {
			printErrorMessage(e);
		}

		return weakest;
	}

	public Robot[] senseNearbyEnemyRobots() {
		return rc.senseNearbyGameObjects(Robot.class, 10, rc.getTeam().opponent());
	}

	public void setPathingAlgorithm(PathingAlgorithm pathingAlgorithm) {
		this.pathingAlgorithm = pathingAlgorithm;
	}

	public MapLocation getLocation() {
		return rc.getLocation();
	}

	public RobotController getRobotController() {
		return rc;
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void checkForNearbyEnemy() {
		if (isEnemyInRange()) {
			enemyInSightAction(null);
		}
	}

	public void analyzeGameState() {

	}

	public void performHighestPriorityTask() {
		state.execute(this);
	}
}
