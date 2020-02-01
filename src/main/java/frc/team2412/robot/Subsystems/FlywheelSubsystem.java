package frc.team2412.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team2412.robot.Commands.flywheel.FlywheelStopCommand;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

/*
@Author Rahul Singh
*/

public class FlywheelSubsystem extends SubsystemBase implements Loggable {

	@Log.SpeedController
	private SpeedControllerGroup m_flywheelMotors;

	public FlywheelSubsystem(CANSparkMax flywheelMotor1, CANSparkMax flywheelMotor2) {

		flywheelMotor1.setIdleMode(IdleMode.kCoast);
		flywheelMotor2.setIdleMode(IdleMode.kCoast);

		flywheelMotor2.setInverted(true);
		this.m_flywheelMotors = new SpeedControllerGroup(flywheelMotor1, flywheelMotor2);

		setDefaultCommand(new FlywheelStopCommand(this));
	}

	public void Shoot() {
		m_flywheelMotors.set(0.75);
	}

	public void Stop() {
		m_flywheelMotors.set(0.0);
	}
}
