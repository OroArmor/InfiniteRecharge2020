/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2412.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.robototes.math.MathUtils;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import io.github.oblarg.oblog.Logger;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
@SuppressWarnings("unused")
public class Robot extends TimedRobot {

	// Have instances of robot container and OI for easy access
	private RobotContainer m_robotContainer = RobotMap.m_robotContainer;
	private OI m_OI = RobotMap.m_OI;

	int[] left = { 2, 12, 6 };
	int[] right = { 5, 9, 8 };

	WPI_TalonSRX[] leftM = { new WPI_TalonSRX(left[0]), new WPI_TalonSRX(left[1]), new WPI_TalonSRX(left[2]) };
	WPI_TalonSRX[] rightM = { new WPI_TalonSRX(right[0]), new WPI_TalonSRX(right[1]), new WPI_TalonSRX(right[2]) };

	SpeedControllerGroup leftC = new SpeedControllerGroup(leftM[0], leftM[1], leftM[2]);
	SpeedControllerGroup rightC = new SpeedControllerGroup(rightM[0], rightM[1], rightM[2]);

	DifferentialDrive drive = new DifferentialDrive(leftC, rightC);

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_robotContainer.m_TurretSubsystem.initTurretEncoder();
		Logger.configureLoggingAndConfig(this, false);
	}

	/**
	 * This function is called every robot packet, no matter the mode. Use this for
	 * items like diagnostics that you want ran during disabled, autonomous,
	 * teleoperated and test.
	 *
	 * <p>
	 * This runs after the mode specific periodic functions, but before LiveWindow
	 * and SmartDashboard integrated updating.
	 */

	@Override
	public void robotPeriodic() {
		CommandScheduler.getInstance().run();
		Logger.updateEntries();
		System.out.println();
	}

	/**
	 * This function is called once when autonomous is started
	 */
	@Override
	public void autonomousInit() {

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		drive.arcadeDrive(m_OI.driverStick.getY(),
				MathUtils.map(MathUtils.cube(-m_OI.driverStick.getTwist()), -1, 1, -0.5, 0.5));
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
