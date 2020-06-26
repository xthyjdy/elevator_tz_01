package classes;

import java.util.ArrayList;
//System.out.println();
public class Floor extends UserHepler {
	//min and max values for users should be created at the start of script
	private final int MIN_USERS_COUNT = 0, MAX_USERS_COUNT = 10;
	//quantity of users which should be created at the start of script  
	private final int USERS_COUNT = 1;//getRandNumber(MIN_USERS_COUNT, MAX_USERS_COUNT);//!!!!!!!!!!!!!!!
	//name(number) of floor 
	private int floorName;
	
	/**
	 * Floor - default constructor, initialize start state
	 * @param int floorName
	 */
	public Floor (int floorName, int maxFloorCount) {
		this.floorName = floorName;
		this.maxFloorCount = maxFloorCount;
		init();
	}
	
	/**
	 * Init - initialize start state
	 */
	private void init() {
		//fill the floor with users
		if (EMPTY != USERS_COUNT) {
			for (int i = 0, len = USERS_COUNT - 1; i <= len; i++) {
				User user = new User(floorName, maxFloorCount);
				users.add(user);
			}
		}
	}
	
	/**
	 * Get Name - getter of object name (floor number) 
	 * @return
	 */
	public int getName() {
		return floorName;
	}
	
	@Override
	public void removeUsers(ArrayList<User> userForThrowOut) {
		for (User user : userForThrowOut) {
			if (MISMATCH != users.indexOf(user)) {
				users.remove(user);
			}
		}
	}

}










