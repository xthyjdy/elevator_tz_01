package classes;

public class Engine {
	//instance of Lift.class
	private Lift lift;
	//while running is true - lift should work 
	private boolean running = true;
	//amount milliseconds for delay operations (this.iteration)
	private int basicDelay = 500;
	
	/**
	 * Engine - default constructor
	 */
	public Engine() {
		init();
	}
	
	/**
	 * Start - method which response for app logic
	 */
	public void start() {
		Floor currentFloor;
		int liftUserCount;
			
		
		while(running) {
			
			currentFloor = lift.getFloors().get(lift.currentPos);
			liftUserCount = lift.getUsers().size();

			if (Helper.EMPTY == liftUserCount) {
				lift.determineDirection();
			}
			
			lift.writeToLog();
			lift.checkQueue();
			lift.goToNextFloor();

			//delay between iterations 
			liftDelay();
		}
		//show main log
		//lift.showLog();
	}
	
	/**
	 * Init - running start initialize
	 */
	private void init() {
		lift = new Lift();
		//set start floor position 
		lift.currentPos = lift.ONE;
		lift.setDirection(lift.MOVE_UP);
	}
	
	/**
	 * Lift Delay - set delay between iterations (depends of "this.basicDelay") 
	 */
	private void liftDelay() {
		try {
			Thread.sleep(basicDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Set Running - start/stop main app operations (iterations) 
	 * @param boolean state
	 */
	public void setRunning(boolean state) {
		running = state;
	}
}













