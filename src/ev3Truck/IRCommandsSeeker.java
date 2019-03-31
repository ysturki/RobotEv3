package ev3Truck;

public class IRCommandsSeeker extends Thread{

	Pilot pilot;
	
	IRCommandsSeeker(Pilot pilot){
		this.pilot = pilot;
	}
	
	public void run() {
		
		while (true) {
			pilot.readIRCommands();
		}
		
	}
}
