package ev3;

public class RemoteListener extends Thread implements BasicRobot{

	Pilot pilot;
	boolean running = true;
	
	RemoteListener(Pilot pilot){
		this.pilot = pilot;
	}
	
	public void stopRobot(){
		running = false;
	}
	
	public Pilot getPilot() {
		return pilot;
	}
	
	public void run() {
		
		//pilot.waitForAnyPress();
		
		pilot.setSpeed(pilot.getMaxSpeed());
		
		pilot.drawString("Started");
		
		while (running) {
			pilot.delay(100);
			int command = pilot.readIRCommands();
			switch(command) {
			case 0 :
				break;
			case 1 :
				pilot.turn(15);
				break;
			case 2 :
				pilot.turn(90);
				break;
			case 3 :
				pilot.turn(-15);
				break;
			case 4 :
				pilot.turn(-90);
				break;
			case 5 :
				pilot.stop();
				pilot.forward();
				break;
			case 8 :
				pilot.stop();
				pilot.backward();
				break;
			case 10 :
				pilot.moveTail();
				break;
			case 11 :
				pilot.stopTail();
				break;
			case 6 : case 7 : case 9 : 
				pilot.stop();
				break;
			default :
				System.out.println("What?");
				break;
			}
		}
		pilot.drawString("Bye Bye. :)");
		
	}
	
	public static void main(String[] args) {
		RemoteListener rl = new RemoteListener(new Pilot());
		(new ProgramStopper(rl)).start();
		rl.start();
	}
}
