package team1024.action;

import java.util.HashSet;
import java.util.Set;

import team1024.pathfinding.AStar;
import team1024.pathfinding.AStar.GraphView;
import team1024.pathfinding.AStar.Heuristic;
import team1024.pathfinding.AStar.Metric;
import team1024.pathfinding.Path;
import team1024.rc.SoldierRC;
import team1024.worldinfo.WorldInfo;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.TerrainTile;

public class Move implements Action {
	private final SoldierRC rc;

	private Path<MapLocation> path;

	public Move(SoldierRC rc) {
		this.rc = rc;
	}

	public boolean prepareToMove(MapLocation target) {
		TheHeuristicClass h = new TheHeuristicClass();
		TheClass c = new TheClass(rc);
		AStar<MapLocation> astar = new AStar<MapLocation>(h, c, c);
		path = astar.findPath(target, rc.getLocation());

		if (path == null) {
			return false;
		}
		if (!path.getHead().equals(rc.getLocation())) {
			path = null;
			return false;
		}
		path = path.getTail();
		return true;
	}

	public boolean needsPath() {
		return path == null;
	}

	@Override
	public void performAction(WorldInfo info) {
		if (needsPath()) {
			prepareToMove(info.getGoalLocation());
		}
		if (needsPath()) {
			return;
		}
		try {
			Direction dir = rc.getLocation().directionTo(path.getHead());
			if (dir == Direction.OMNI || dir == Direction.NONE) {
				path = null;
				return;
			}
			if (rc.canMove(dir)) {
				rc.sneak(dir);
			    path = path.getTail();
			} 
		} catch (GameActionException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static class TheHeuristicClass implements Heuristic<MapLocation> {
		@Override
		public double evaluate(MapLocation node, MapLocation target) {
			double dx = Math.abs(node.x - target.x);
			double dy = Math.abs(node.y - target.y);
			return Math.abs(dx - dy) + (Math.min(dx, dy) * Math.sqrt(2));
		}
	}

	public static class TheClass implements Metric<MapLocation>,
			GraphView<MapLocation> {
		private final SoldierRC rc;

		public TheClass(SoldierRC rc) {
			this.rc = rc;
		}

		@Override
		public Set<MapLocation> getNeighbors(MapLocation node) {
			Set<MapLocation> result = new HashSet<MapLocation>();
			MapLocation[] neighbors = MapLocation.getAllMapLocationsWithinRadiusSq(node, 2);
			for (MapLocation loc : neighbors) {
				if (rc.senseTerrainTile(loc) == TerrainTile.OFF_MAP ||
					rc.senseTerrainTile(loc) == TerrainTile.VOID) {
					continue;
				}
				result.add(loc);	
			}
			return result;
		}

		@Override
		public double evaluate(MapLocation node, MapLocation target) {
			double factor = 1;
			if (rc.senseTerrainTile(target) == TerrainTile.ROAD) {
				factor = 1.2;
			}
			boolean xCompare = node.x == target.x;
			boolean yCompare = node.y == target.y;
			if (xCompare ^ yCompare) {
				return factor;
			} else if (xCompare && yCompare) {
				return 0;
			} else {
				return Math.sqrt(2) * factor;
			}
		}
	}
}
