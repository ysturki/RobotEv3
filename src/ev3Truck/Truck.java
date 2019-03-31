package ev3Truck;

public class Truck extends Robot {
	
	Truck(Pilot pilot){
		super(pilot);
	}

	public void run() {
		pilot.setSpeed(pilot.getMaxSpeed());
		while(running) {

			pilot.setSpeed(pilot.getMaxSpeed());
			
			pilot.drawString("Started");
			
			while (running) {
				if(pilot.tsIsPressed()) {
					int rand = (int) (Math.random()*10) - 1;
					System.out.println(rand);
					switch(rand) {
					case 1 : pilot.forward();pilot.delay(500);pilot.backward();pilot.stop();pilot.moveTail(50);pilot.moveTail(-100);pilot.moveTail(50);break;
					case 2 : pilot.moveTail(50);pilot.backward();pilot.delay(500);pilot.moveTail(-100);pilot.forward();pilot.delay(500);pilot.backward();pilot.delay(500);pilot.stop();break;
					case 3 : pilot.backward();pilot.delay(3000);pilot.stop();break;
					case 4 : pilot.forward();pilot.delay(3000);pilot.stop();break;
					case 5 : pilot.moveTail(50);pilot.moveTail(-100);pilot.moveTail(50);break;
					case 6 :case 7 : case 8 : case 9 : case 0:  System.out.println("something");break;
					}
				}
				int command = pilot.readIRCommands();
				switch(command) {
				case 0 :
					break;
				case 1 :
					pilot.moveTail(-30);
					break;
				case 2 :
					pilot.forward();
					break;
				case 3 :
					pilot.moveTail(30);
					break;
				case 4 :
					pilot.backward();
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
					//pilot.moveTail();
					break;
				case 11 :
					//pilot.stopTail();
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
	}
	
	public static void main(String[] args) {
		Robot robot = new Truck(new Pilot(20, 112));
		(new ProgramStopper(robot)).start();
		robot.start();
		//(new RemoteListener(robot.getPilot())).start();
	}
}
