package team113.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import team113.pathfinding.AStar;
import team113.pathfinding.AStar.GraphView;
import team113.pathfinding.AStar.Heuristic;
import team113.pathfinding.AStar.Metric;
import team113.pathfinding.Path;
import team113.rc.SoldierRC;
import team113.worldinfo.WorldInfo;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.TerrainTile;

public class Move implements Action {
	private final SoldierRC rc;
		
	private Path<MapLocation> path;
	private MapLocation currentTarget;
	private boolean deveated;
	
	// TODO decouple state from the move action.
	public Move(SoldierRC rc) {
		this.rc = rc;
	}
	
	public boolean updateTarget(MapLocation newTarget) {
		
		currentTarget = newTarget;
		
		TheHeuristicClass h = new TheHeuristicClass();
		TheClass c = new TheClass(rc);
		AStar<MapLocation> astar = new AStar<MapLocation>(h, c, c);
		MapLocation source = rc.getLocation();
		path = astar.findPath(source, currentTarget);
		
		if (path != null) {
			path = path.getTail();
			return true;
		}
		
		return false;		
	}	
	
	// call when we decided to deviate from path, and want to fix it.
	private void fixDeviation() {
		TheHeuristicClass h = new TheHeuristicClass();
		TheClass c = new TheClass(rc);
		AStar<MapLocation> astar = new AStar<MapLocation>(h, c, c);
		astar.addSeedPath(path.getTail());
		MapLocation source = rc.getLocation();
		path = astar.findPath(source, currentTarget);
		
		if (path != null) {
			path = path.getTail();
		}
		
		deveated = false;
	}
	
	public MapLocation currentTarget() {
		return currentTarget;
	}
	
	public boolean atTarget(int sqDist) {
		return rc.getLocation().distanceSquaredTo(currentTarget) <= sqDist;
	}
	
	public boolean havePath() {
		return path != null;
	}

	@Override
	public void performAction(WorldInfo info) {
		if (path == null) {
			return;
		}
		if (deveated) {
			fixDeviation();
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
				return;
			}
			Direction deviatedDir = dir.rotateLeft();
			if (rc.canMove(deviatedDir)) {
				rc.move(deviatedDir);
				deveated = true;
				return;
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
		private Map<MapLocation, TerrainTile> sensedTerrainMap = new HashMap<MapLocation, TerrainTile>(100);

		public TheClass(SoldierRC rc) {
			this.rc = rc;
		}

		@Override
		public Set<MapLocation> getNeighbors(MapLocation node) {
			Set<MapLocation> result = new HashSet<MapLocation>();
			MapLocation[] neighbors = MapLocation
					.getAllMapLocationsWithinRadiusSq(node, 2);
			for (MapLocation loc : neighbors) {
				
				TerrainTile sensedTerrainTile = null;
				if (sensedTerrainMap .containsKey(loc)) {
					sensedTerrainTile = sensedTerrainMap.get(loc);					
				} else {
					sensedTerrainTile = rc.senseTerrainTile(loc);
					sensedTerrainMap.put(loc, sensedTerrainTile);		
				}
				
				if (sensedTerrainTile == TerrainTile.OFF_MAP ||
						sensedTerrainTile == TerrainTile.VOID) {
					continue;
				}

				result.add(loc);
			}
			return result;
		}

		@Override
		public double evaluate(MapLocation node, MapLocation target) {
			double factor = 1;
			TerrainTile sensedTerrainTile = null;
			if (sensedTerrainMap .containsKey(target)) {
				sensedTerrainTile = sensedTerrainMap.get(target);					
			} else {
				sensedTerrainTile = rc.senseTerrainTile(target);
				sensedTerrainMap.put(target, sensedTerrainTile);		
			}
			
			if (sensedTerrainTile == TerrainTile.ROAD) {
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
