package team1024.pathfinding;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar<N> {
	private final Heuristic<N> mHeuristic;
	private final Metric<N> mMetric;
	private final GraphView<N> mGraphView;
	
	public AStar(Heuristic<N> heuristic, Metric<N> metric, GraphView<N> graphView) {
		mHeuristic = heuristic;
		mMetric = metric;
		mGraphView = graphView;
	}
		
    public Path<N> findPath(final N source, final N target) {
    	Set<N> visited = new HashSet<N>();
    	
    	PriorityQueue<Path<N>> queue =
    			new PriorityQueue<Path<N>>(10, new PathComparator<N>(mHeuristic, target));
    	queue.add(new Path<N>(source));
    	
    	while (queue.size() > 0) {
    	  Path<N> next = queue.poll();
    	  
    	  if (next.getHead().equals(target)) {
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
			double c1 = p1.getCost() + 3 * mHeuristic.evaluate(p1.getHead(), mTarget);
			double c2 = p2.getCost() + 3 * mHeuristic.evaluate(p2.getHead(), mTarget);
			return Double.compare(c1, c2);
		}
    }
}
