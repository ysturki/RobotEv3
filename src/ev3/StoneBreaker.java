package ev3;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class StoneBreaker {

	public static void main(String[] args) {

		MovePilot pilot = new MovePilot(20, 112, Motor.B, Motor.C);
		EV3IRSensor ir = new EV3IRSensor(SensorPort.S4);
		SampleProvider sp = ir.getDistanceMode();
		RegulatedMotor tail = (Motor.A);

		Button.waitForAnyPress();
		pilot.setLinearSpeed(pilot.getLinearSpeed() / 2);
		boolean bol = true;
		
		while (bol) {
			pilot.backward();
			int distance = readDistance(ir,sp);
			System.out.println(distance);
			if (distance < 25) {
				pilot.stop();
				pilot.travel(10);
				pilot.rotate(180);
				tail.backward();
				pilot.travel(100);
				tail.stop();
				pilot.rotate(180);
				pilot.backward();
			}
			
			int button = Button.waitForAnyPress(1000);
			switch (button) {
			case 1:
			case 2:
			case 4:
			case 8:
				bol = false;
				break;
			default:
				continue;
			}
		}
	}

	public static int readDistance(EV3IRSensor ir, SampleProvider sp) {
		
		sp = ir.getDistanceMode();
		int distance = 255;

		float[] sample = new float[sp.sampleSize()];
		sp.fetchSample(sample, 0);
		distance = (int) sample[0];
		

		return distance;

	}
}
