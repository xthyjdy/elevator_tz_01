package classes;

import java.util.ArrayList;

/**
 * UserHelper - basic class which provide low and high levels methods, constants and variables
 * @author vch
 */
public class UserHepler extends Helper {

	//list of users on this floor, instances of User.class  
	protected ArrayList<User> users = new ArrayList<User>();
	
	/**
	 * Add User - add User to list
	 * @param User user
	 */
	public void addUser(User user) {
		users.add(user);
	}
	
	/**
	 * Remove User - remove User from list
	 * @param int userNumber
	 */
	public void removeUser(int userNumber) {
		users.remove(userNumber);
	}
	
	/**
	 * Get Users - get list of users (on this floor)
	 * @return
	 */
	public ArrayList<User> getUsers() {
		return users;
	}
	
	/**
	 * Set Users - setter for users
	 * @param ArrayList<User> users
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	/**
	 * Remove Users - remove specified users
	 * @param userForThrowOut
	 */
	public void removeUsers(ArrayList<User> userForThrowOut) {} 
}

















