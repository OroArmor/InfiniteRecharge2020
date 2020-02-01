package frc.team2412.robot;

import frc.team2412.robot.Subsystems.FlywheelSubsystem;
import frc.team2412.robot.Subsystems.HoodSubsystem;
import frc.team2412.robot.Subsystems.LimelightSubsystem;
import frc.team2412.robot.Subsystems.TurretSubsystem;
import io.github.oblarg.oblog.annotations.Log;

// this is the class for containing all the subsystems and OI of the robot
public class RobotContainer {

	@Log(name = "Limelight Subsystem")
	public LimelightSubsystem m_LimelightSubsystem;

	@Log(name = "Turret Subsystem")
	public TurretSubsystem m_TurretSubsystem;

	@Log(name = "Flywheel Subsystem")
	public FlywheelSubsystem m_FlywheelSubsystem;

	@Log(name = "Hood Subsystem")
	public HoodSubsystem m_HoodSubsystem;

	public RobotContainer() {
		// create and instance of example subsystem with the id from robot map

		m_LimelightSubsystem = new LimelightSubsystem(RobotMap.limelight);

		m_TurretSubsystem = new TurretSubsystem(RobotMap.turretMotor, m_LimelightSubsystem);

		m_FlywheelSubsystem = new FlywheelSubsystem(RobotMap.flywheelMotor1, RobotMap.flywheelMotor2);

		m_HoodSubsystem = new HoodSubsystem(RobotMap.hoodServo);
	}
}
