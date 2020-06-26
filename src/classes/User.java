package classes;

public class User extends Helper {
	//min and max values for IMPUDENCE, should be created at the start of script
	private final int MIN_IMPUDENCE = 0, MAX_IMPUDENCE = 10;
	//IMPUDENCE will be used when on the floor we will get equal quantity of users and they directions
	private final int IMPUDENCE = getRandNumber(MIN_IMPUDENCE, MAX_IMPUDENCE);
	//floor on which user was born (user targetPos related of this value)
	private int floorName;
	//user name
	public String name = "user_";
	
	/**
	 * User - default constructor, initialize start state
	 * @param int floorName
	 */
	public User(int floorName, int maxFloorCount) {
		this.floorName = floorName;
		this.maxFloorCount = maxFloorCount;
		init();
	}
	
	/**
	 * Init - initialize start state
	 */
	private void init() {
		resetPosition();
		setName();
	}
	
	/**
	 * Set Floor Name - setter for floorName
	 * @param floorName
	 */
	public void setFloorName(int floorName) {
		this.floorName = floorName;
	}
	
	/**
	 * Get Floor Name - getter of floorName
	 * @return int
	 */
	public int getTargetPosition() {
		return targetPos;
	}
	
	/**
	 * Reset Position - setter for resetPosition
	 */
	public void resetPosition() {
		int min = ONE, max = maxFloorCount, rand;
		
		switch (this.floorName) {
			case MIN_FLOOR:
				min += min;
				break;
			case MAX_FLOOR:
				max -= min;
				break;
		}
		
		rand = getRandNumber(min, max);
		//avoid previous position
		targetPos = (rand != targetPos) ? rand : rand + min;
	}
	
	/**
	 * Get Impudence - getter of getImpudence
	 * @return int
	 */
	public int getImpudence() {
		return IMPUDENCE;
	}
	
	/**
	 * Set name - for test
	 */
	private void setName() {
		name = name + String.valueOf(counter);
		counter++;
	}
}