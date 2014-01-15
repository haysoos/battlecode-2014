package team113.pathfinding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar<N> {
	private final Heuristic<N> mHeuristic;
	private final Metric<N> mMetric;
	private final GraphView<N> mGraphView;
	
	private final List<Path<N>> seedPaths = new ArrayList<Path<N>>();
	
	public AStar(Heuristic<N> heuristic, Metric<N> metric, GraphView<N> graphView) {
		mHeuristic = heuristic;
		mMetric = metric;
		mGraphView = graphView;
	}
	
	public void addSeedPath(Path<N> seedPath) {
		seedPaths.add(seedPath);
	}
		
    public Path<N> findPath(final N source, final N target) {
    	Set<N> visited = new HashSet<N>();
    	
    	PriorityQueue<Path<N>> queue =
    			new PriorityQueue<Path<N>>(10, new PathComparator<N>(mHeuristic, source));
    	queue.add(new Path<N>(target));
    	
    	for (Path<N> seedPath : seedPaths) {
    		Path<N> path = seedPath;
    		while (path != null) {
    			queue.add(path);
    			path = path.getTail();
    		}
    	}
    	seedPaths.clear();
    	
    	while (queue.size() > 0) {
    	  Path<N> next = queue.poll();
    	  
    	  if (next.getHead().equals(source)) {
    		  return next;
    	  }   
    	  
    	  visited.add(next.getHead());
    	  
    	  Set<N> neighbors = mGraphView.getNeighbors(next.getHead());
    	  for (N elt : neighbors) {
    		  if (visited.contains(elt)) {
    			  continue;
    		  }
    		  double cost = mMetric.evaluate(next.getHead(), elt);
    		  queue.add(next.addHead(elt, cost));
    	  }    	
    	}
    	return null;
    }
    
    public interface Heuristic<N> {
    	double evaluate(N node, N target);
    }
    
    public interface Metric<N> {
    	double evaluate(N node1, N node2);
    }
    
    public interface GraphView<N> {
    	Set<N> getNeighbors(N node);
    }
    
    private static final class PathComparator<N> implements Comparator<Path<N>> {
    	private final Heuristic<N> mHeuristic;
    	private final N mTarget;
    	
    	public PathComparator(Heuristic<N> heuristic, N target) {
    		mHeuristic = heuristic;
    		mTarget = target;    				
    	}
    	
		@Override
		public int compare(Path<N> p1, Path<N> p2) {
			double c1 = p1.getCost() + 1.5 * mHeuristic.evaluate(p1.getHead(), mTarget);
			double c2 = p2.getCost() + 1.5 * mHeuristic.evaluate(p2.getHead(), mTarget);
			return Double.compare(c1, c2);
		}
    }
}
