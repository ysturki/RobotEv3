package ev3Truck;

public abstract class Robot extends Thread implements BasicRobot {

	Pilot pilot;
	boolean running = true;
	
	Robot(Pilot pilot){
		this.pilot = pilot;
	}
	
	public void stopRobot(){
		running = false;
	}
	
	public Pilot getPilot() {
		return pilot;
	}

	public abstract void run();
}
