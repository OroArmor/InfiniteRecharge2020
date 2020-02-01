package frc.team2412.robot;

import frc.team2412.robot.Subsystems.IndexerSubsystem;
import io.github.oblarg.oblog.annotations.Log;

// this is the class for containing all the subsystems and OI of the robot
public class RobotContainer {

	@Log(name = "Indexer Subsystem")
	public IndexerSubsystem m_IndexerSubsystem;

	public RobotContainer() {

		m_IndexerSubsystem = new IndexerSubsystem(RobotMap.indexFrontMotor, RobotMap.indexMidMotor,
				RobotMap.indexBackMotor, RobotMap.front, RobotMap.frontMid, RobotMap.mid, RobotMap.backMid,
				RobotMap.back, RobotMap.intakeFront, RobotMap.intakeBack);

	}
}
