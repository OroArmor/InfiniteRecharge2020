package frc.team2412.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team2412.robot.Commands.flywheel.FlywheelDefault;

//This is the class in charge of all the buttons and joysticks that the drivers will use to control the robot
public class OI {

	// Joystick ports
	public static final int DRIVER_STICK_PORT = 0;
	public static final int CODRIVER_STICK_PORT = 1;

	// Joysticks
	public Joystick driverStick = new Joystick(DRIVER_STICK_PORT);
	public Joystick codriverStick = new Joystick(CODRIVER_STICK_PORT);

	// Buttons
	public Button exampleSubsystemMethod = new JoystickButton(driverStick, 2);

	// Constructor to set all of the commands and buttons
	public OI(RobotContainer robotContainer) {
		// telling the button that when its pressed to execute example command with the
		// robot container's instance of example subsystem

		exampleSubsystemMethod.whileHeld(new FlywheelDefault(robotContainer.m_FlywheelSubsystem));

	}
}
