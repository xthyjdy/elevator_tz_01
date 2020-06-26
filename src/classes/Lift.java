package classes;

import java.util.ArrayList;

public class Lift extends UserHepler {
	//min and max start values for floors which should be created at the start of script 
	private final int MIN_FLOOR_COUNT = FIVE, MAX_FLOOR_COUNT = TWENTY;
	//quantity of floors which should be created at the start of script
	private final int FLOOR_COUNT = 5;//getRandNumber(MIN_FLOOR_COUNT, MAX_FLOOR_COUNT);//!!!!!!!!!!!!!!!!!!!!!
	//list of all floors
	private ArrayList<Floor> floors = new ArrayList<Floor>();
	//list of users in lift
	private ArrayList<User> users = new ArrayList<User>();
	//log
	private ArrayList<String> log = new ArrayList<String>();
	//lift direction and max capacity
	public final int MOVE_UP = 1, MOVE_DOWN = -1, LIFT_MAX_CAPACITY = FIVE;
	
	/**
	 * Lift - default constructor, initialize start state
	 */
	public Lift() {
		init();
	}
	
	/**
	 * Init - initialize start state
	 */
	private void init() {
		//fill the lift shaft with floors
		for (int i = 1, len = FLOOR_COUNT; i <= len; i++) {
			Floor floor = new Floor(i, FLOOR_COUNT);
			floors.add(floor);
		}
	}
	
	/**
	 * Check Queue - method which regulates transfer people from floor to lift and back
	 */
	public void checkQueue() {
//		System.out.println("currentPos - " + currentPos + "_____________________");
		Floor currentFloor = getFloors().get(currentPos);
		//list will be filled users to remove from lift
		ArrayList<User> removeUsersFromLift = new ArrayList<User>();
		//list will be filled users to remove from floor
		ArrayList<User> removeUsersFromFloor = new ArrayList<User>();

		
		int userCount = users.size(),
				currentUserDirection = EMPTY;

		if (EMPTY != userCount) {
			for (int i = 0, userLen = userCount - 1; i <= userLen; i++) {
				User user = users.get(i);
				//if this floor is user targetPos - throw out him from the lift
				if (user.targetPos == currentPos) {
					//define users list which should be removed from lift
					removeUsersFromLift.add(user);
					//set new targetPos to user
					user.resetPosition();
					//add user to the floor
					currentFloor.getUsers().add(user);
				}
			}
			if (EMPTY != removeUsersFromLift.size()) {
				this.removeUsers(removeUsersFromLift);
			}
		}
		
		if (checkFreeSpace()) {
			for (int i = 0, floorUserLen = currentFloor.getUsers().size() - 1; i <= floorUserLen; i++) {
				User user = currentFloor.getUsers().get(i);
				//define user direction
				currentUserDirection = (user.targetPos > currentPos) ? MOVE_UP : MOVE_DOWN;
				
				//if user direction equal to lift direction and lift has free space - user transfer to lift
				if (currentUserDirection == direction) {
					if (checkFreeSpace()) {
						//add user to lift
						users.add(user);
						//define users list which should be removed from floor
						removeUsersFromFloor.add(user);
						//reset lift.targetPos
						setTargetPosition(user.getTargetPosition());
					} else {
						break;
					}
				}
			}
			
			if (EMPTY != removeUsersFromFloor.size()) {
				currentFloor.removeUsers(removeUsersFromFloor);
			}
		}
	}
	
	/**
	 * Go To Next Floor - method which increase current floor to current directory
	 */
	public void goToNextFloor() {
		if (MOVE_UP == direction && currentPos == FLOOR_COUNT - 1) {
			direction = MOVE_DOWN;
		}
		
		if (MOVE_DOWN == direction && currentPos == ONE) {
			direction = MOVE_UP;
		}
		
		currentPos += direction;
	}
	
	/**
	 * Check Free Space - method which checks that  lift won't get excess users
	 * @return boolean
	 */
	private boolean checkFreeSpace() {
		return (users.size() >= LIFT_MAX_CAPACITY) ? false : true;
	}
	
	/**
	 * Determine Direction - determine and set current lift direction, based on voices/impudence of users
	 * (for get users with equal direction)
	 */
	public void determineDirection() {
		int up = EMPTY, 
				down = EMPTY,
				len = EMPTY;
		Floor currentFloor = this.getFloors().get(currentPos);		
		len = currentFloor.getUsers().size();
		
		if (EMPTY == direction) {
			if (currentPos == ONE) {
				direction = MOVE_UP;
			} else if (currentPos == FLOOR_COUNT) {
				direction = MOVE_DOWN;
			} else if (Helper.EMPTY != len) {
				for (int i = 0; i <= len - 1; i++) {
					User user = currentFloor.getUsers().get(i);
					
					//get direction from users targetPos
					if (user.getTargetPosition() > currentPos) {
						up++;
					} else if (user.getTargetPosition() < currentPos) {
						down++;
					}
					
					//if amount of users desires direction are equal, set direction based on impudence 
					if (up == down) {
						if (user.getTargetPosition() > currentPos) {
							up += user.getImpudence();
						} else if (user.getTargetPosition() < currentPos) {
							down += user.getImpudence();
						}
					}
				}
				direction = (up > down) ? MOVE_UP : MOVE_DOWN;
			} else {
				//default direction for middle level floor (rarity)
				direction = MOVE_UP;
			}
		}
	}
	
	/**
	 * Set Direction - basic setter for direction
	 */
	public void setDirection(int newDirection) {
		this.direction = direction;
	}
	
	/**
	 * Get Direction - basic getter of direction  
	 * @return int
	 */
	public int getDirection() {		
		return direction;
	}
	
	/**
	 * Get Floors - getter of floors
	 */
	public ArrayList<Floor> getFloors() {
		return floors;
	}
	
	/**
	 * Set Target Position - setter for target position
	 * @param int pos
	 */
	private void setTargetPosition(int position) {
		if (MOVE_UP == direction) {
			targetPos = (position > targetPos) ? position : targetPos;
		} else if (MOVE_DOWN == direction) {
			targetPos = (position < targetPos) ? position : targetPos;
		}
	}
	
	@Override
	public void setUsers(ArrayList<User> newList) {
		users = newList;
	}
	
	@Override
	public void removeUsers(ArrayList<User> userForThrowOut) {
		for (User user : userForThrowOut) {
			if (MISMATCH != users.indexOf(user)) {
				users.remove(user);
			}
		}
	}
	
	/**
	 * Show Log - method which show log
	 */
	public void showLog() {
		System.out.println("-------------MAIN LOG----------------");
		for (String l : log) {
			System.out.println(l);
		}
		System.out.println("-------------LOG END-----------------");
	}
	
	/**
	 * Write To Log - method which save log
	 */
	public void writeToLog() {
		char border = '|';
		char userSeparator = ';';
		char direct = (MOVE_UP == direction) ? '^' : 'v';
		StringBuffer b = new StringBuffer();		
		String lb = System.lineSeparator();
		String liftPos = "______floor_" + currentPos + "______";
		ArrayList<Floor> floors = getFloors();
		int floorCount = floors.size();
		
		//start building log display
		b.append(liftPos + lb);
		
		for (int i = floorCount - 1; i >= 1; i--) {
			ArrayList<User> users = floors.get(i).getUsers();
			String item = "";
			String floorName = "f-" + i;
			
			b.append(floorName);
			b.append(border);
			b.append(direct);
			b.append(border);
			for (int j = 0; j <= users.size() - 1; j++) {
				item += users.get(j).getTargetPosition() + "; ";
				b.append(item);
			}
			b.append(lb);
		}
		//end of log building
		
		//add screenshot to main log file
		log.add(b.toString());
		//display log
		System.out.println(b.toString());
	}
}