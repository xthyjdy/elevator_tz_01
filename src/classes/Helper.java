package classes;

/**
 * Helper - basic class which provide low level methods, constants and variables
 * @author vch
 */
public class Helper {
	//for set unique name for users
	public static int counter = 0;
	//constant which were created for avoid (unknown) text/number in the code 
	public static final int  MISMATCH = -1, EMPTY = 0, ONE = 1, FIVE = 5, TEN = 10, TWENTY = 20;
	//the highest floor
	public static final int MAX_FLOOR = TWENTY;
	//the lowest floor
	public static final int MIN_FLOOR = ONE;
	//constant show what we should do now  
	public static final boolean STATE_ACTIVE = true, STATE_INIT = false;
	//positions of items and default sets
	protected int startPos = EMPTY, targetPos = EMPTY, currentPos = EMPTY, direction = EMPTY, maxFloorCount = EMPTY;
	
	public static int getRandNumber(int min, int max) {
		int r = (int)((Math.random() * max)); 
		return (r > min) ? r : r + min;
	}
}
