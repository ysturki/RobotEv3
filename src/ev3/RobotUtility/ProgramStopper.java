package ev3;

public class ProgramStopper extends Thread{

	BasicRobot robot;
	Pilot pilot;
	
	ProgramStopper(BasicRobot robot){
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
