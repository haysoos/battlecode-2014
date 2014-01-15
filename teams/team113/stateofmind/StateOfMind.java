package team113.stateofmind;

public interface StateOfMind {

	/**
	 * Sense the state of the world. Think about what to do.
	 * @return this or a new StateOfMind if we changed our mind after thinking.
	 */
	StateOfMind think();
	
	/**
	 * Perform an action. 
	 */
	void act();
}
