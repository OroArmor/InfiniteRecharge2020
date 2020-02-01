package frc.team2412.robot.Commands.flywheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team2412.robot.Subsystems.FlywheelSubsystem;

public class FlywheelDefault extends CommandBase {

	private FlywheelSubsystem m_flywheelSubsystem;

	public FlywheelDefault(FlywheelSubsystem subsystem) {
		this.m_flywheelSubsystem = subsystem;
		addRequirements(subsystem);
	}

	@Override
	public void execute() {
		m_flywheelSubsystem.Shoot();
	}

	public void end() {
		System.out.println("bad");
		m_flywheelSubsystem.Stop();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
