package frc.team2412.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.robototes.sensors.Limelight;
import com.robototes.sensors.Limelight.CamMode;
import com.robototes.sensors.Limelight.LEDMode;
import com.robototes.sensors.Limelight.Pipeline;
import com.robototes.sensors.Limelight.SnapshotMode;
import com.robototes.sensors.Limelight.StreamMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

//This is the class in charge of all the motors, motor ids, and any other sensors the robot uses. 
//remember to declare robot container at the bottom of this class 
public class RobotMap {

	// DRIVEBASE SUBSYSTEM
	// -------------------------------------------------------------------------
	// DriveBase Motor ports
//	public static final int LEFT_FRONT_ID = 0;
//	public static final int LEFT_BACK_ID = 0;
//	public static final int RIGHT_FRONT_ID = 0;
//	public static final int RIGHT_BACK_ID = 0;
//
//	// DriveBase Motors
//	private static Talon leftFront = new Talon(LEFT_FRONT_ID);
//	private static Talon leftBack = new Talon(LEFT_BACK_ID);
//	private static Talon rightFront = new Talon(RIGHT_FRONT_ID);
//	private static Talon rightBack = new Talon(RIGHT_BACK_ID);

//	// DriveBase SpeedControllerGroups
//	public static SpeedControllerGroup leftSide = new SpeedControllerGroup(leftFront, leftBack);
//	public static SpeedControllerGroup rightSide = new SpeedControllerGroup(rightFront, rightBack);
//
//	// DriveBase DifferentialDrive
//	public static DifferentialDrive robotDrive = new DifferentialDrive(leftSide, rightSide);
//
//	// DriveBase Gyro
//	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	// Turret Subsystem
	public static final int turretMotorID = 1;
	public static WPI_TalonSRX turretMotor = new WPI_TalonSRX(turretMotorID);

	// Flywheel subsystem
	public static final int flywheelMotorID1 = 3;
	public static final int flywheelMotorID2 = 4;

	public static CANSparkMax flywheelMotor1 = new CANSparkMax(flywheelMotorID1, MotorType.kBrushless);
	public static CANSparkMax flywheelMotor2 = new CANSparkMax(flywheelMotorID2, MotorType.kBrushless);

	public static SpeedControllerGroup flywheelSpeedGroup = new SpeedControllerGroup(flywheelMotor1, flywheelMotor2);

	// Hood Subsystem
	public static final int HOOD_SERVO_PORT = 1;
	public static Servo hoodServo = new Servo(HOOD_SERVO_PORT);

	// ----------------------------------------------------------------------------------------------
	// Limelight subsystem
	public static NetworkTable limelightNetworkTable = NetworkTableInstance.getDefault().getTable("limelight");
	public static Limelight limelight = new Limelight(limelightNetworkTable, LEDMode.OFF, CamMode.VISION_PROCESSER,
			Pipeline.ZERO, StreamMode.STANDARD, SnapshotMode.OFF);

	// Robot container
	public static RobotContainer m_robotContainer = new RobotContainer();

	// OI
	public static OI m_OI = new OI(m_robotContainer);
}
