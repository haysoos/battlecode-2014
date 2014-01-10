package team113.strategies.hq;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameConstants;
import team113.common.Constants;
import team113.robots.BaseRobot;
import team113.states.State;

public class HQSpawningState implements State {

	@Override
	public void execute(BaseRobot robot) {
		if (robot.getRobotController().isActive()) {
			Direction direction = calculateBestDirectionToSpawn(robot);
			robot.setRobotIndicator(Constants.INDICATOR_GENERAL, "Spawning Solider at " + direction.toString());
			spawnSoliderRobot(direction, robot);
		}
	}

	private Direction calculateBestDirectionToSpawn(BaseRobot robot) {
		Direction direction = robot.getLocation().directionTo(robot.getRobotController().senseEnemyHQLocation());
		while (!robot.getRobotController().canMove(direction)) {
			direction.rotateRight();
		}
		return direction;
	}

	private void spawnSoliderRobot(Direction direction, BaseRobot robot) {
		try {
			robot.getRobotController().spawn(direction);
		} catch (GameActionException e) {
			if (!e.getMessage().contains("Maximum robot limit reached.")) {
				robot.printErrorMessage(e);
			}
			
		}
	}

}
