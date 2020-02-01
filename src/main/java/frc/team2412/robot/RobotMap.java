package frc.team2412.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Spark;

//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses. 
//remember to declare robot container at the bottom of this class 
public class RobotMap {

	// motors
	public static PWMSparkMax indexFrontMotor = new PWMSparkMax(0);
	public static PWMSparkMax indexBackMotor = new PWMSparkMax(1);
	public static Spark indexMidMotor = new Spark(2);

	// sensors
	public static DigitalInput back = new DigitalInput(7);
	public static DigitalInput backMid = new DigitalInput(6);
	public static DigitalInput mid = new DigitalInput(5);
	public static DigitalInput frontMid = new DigitalInput(4);
	public static DigitalInput front = new DigitalInput(3);

	// INDEXER CONTROLS THESE NOT INTAKE FYI
	public static DigitalInput intakeFront = new DigitalInput(8);
	public static DigitalInput intakeBack = new DigitalInput(9);

	// Robot container
	public static RobotContainer m_robotContainer = new RobotContainer();

	// OI
	public static OI m_OI = new OI(m_robotContainer);
}
