package ev3Truck;

public class ProgramStopper extends Thread{

	Robot robot;
	Pilot pilot;
	
	ProgramStopper(Robot robot){
		this.robot = robot;
		pilot = robot.getPilot();
	}
	
	public void run() {
		while (true) {
			pilot.waitForAnyPress();
			robot.stopRobot();
			break;
		}
	}
	
}
