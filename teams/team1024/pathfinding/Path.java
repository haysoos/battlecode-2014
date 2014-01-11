package team1024.pathfinding;

public class Path<E> {
	private final E mHead;
	
	private final Path<E> mTail;
	
	private final int mLength;
	
	private final double mCost;
	
	public Path(E head) {
		mHead = head;
		mTail = null;
		mLength = 1;
		mCost = 0;
	}
	
	private Path(double additionalCost, E head, Path<E> tail) {
		mHead = head;
		mTail = tail;
		mLength = tail.length() + 1;
		mCost = tail.getCost() + additionalCost;
		
	}
	
	public E getHead() {
		return mHead;
	}
	
	public Path<E> getTail() {
		return mTail;
	}
	
	public Path<E> addHead(E head, double additionalCost) {
		return new Path<E>(additionalCost, head, this);
	}
	
	public int length() {
		return mLength;
	}
	
	public double getCost() {
		return mCost;
	}
}
