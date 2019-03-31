package ev3;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class Pilot {
	
	MovePilot pilot ;
	EV3IRSensor ir ;
	SampleProvider sp;
	RegulatedMotor tail;
	
	public Pilot() {
		
		pilot = new MovePilot(20, 112, Motor.B, Motor.C);
		ir = new EV3IRSensor(SensorPort.S4);
		sp = ir.getDistanceMode();
		tail = (Motor.A);
	}
	
	void delay(int time) {
		Delay.msDelay(time);
	}
	
	void forward() {
		pilot.backward();
	}
	
	void backward() {
		pilot.forward();
	}
	
	void turn180() {
		pilot.rotate(180);
	}
	
	void turn(int angle) {
		pilot.rotate(angle);
	}
	
	void stop() {
		pilot.stop();
	}
	
	void moveTail(int angle) {
		tail.rotate(angle);
	}
	
	void moveTail() {
		tail.forward();
	}
	
	void moveTailInTime(int time) {
		moveTail();
		delay(time);
		stopTail();
	}
	
	void stopTail() {
		tail.stop();
	}
	
	void setSpeed(double speed) {
		pilot.setLinearSpeed(speed);
	}
	
	void setRotatingSpeed(double speed) {
		pilot.setAngularSpeed(speed);
	}
	
	void drawString(String str) {

		LCD.drawString(str, 0, 0);
		LCD.refresh();
		delay(1000);
		LCD.clear();
		
	}
	
	void clearScreen() {
		LCD.refresh();
		LCD.clear();
		LCD.refresh();
	}
	
	void waitForAnyPress() {
		Button.waitForAnyPress();
	}
	
	boolean waitForAnyPressInTime(int time) {
		boolean bol;
		int button = Button.waitForAnyPress(time);
		switch (button) {
		case 1:
		case 2:
		case 4:
		case 8:
			bol = true;
			break;
		default:
			bol=false;
			break;
		}
		return bol;
	}
	
	int readDistance() {
		sp = ir.getDistanceMode();
		int distance = 255;

		float[] sample = new float[sp.sampleSize()];
		sp.fetchSample(sample, 0);
		distance = (int) sample[0];
		

		return distance;
	}
	
	int readIRCommands() {
		return ir.getRemoteCommand(0);
	}
	
	double getSpeed() {
		return pilot.getLinearSpeed();
	}
	
	double getMaxSpeed() {
		return pilot.getMaxLinearSpeed();
	}
	
	double getRotatingSpeed() {
		return pilot.getAngularSpeed();
	}
	
	double getMaxRotatingSpeed() {
		return pilot.getMaxAngularSpeed();
	}
	
	MovePilot getPilot() {
		return pilot;
	}
	
	EV3IRSensor getIR() {
		return ir;
	}
	
	SampleProvider getSP() {
		return sp;
	}
	
	RegulatedMotor getTail() {
		return tail;
	}
	
}
