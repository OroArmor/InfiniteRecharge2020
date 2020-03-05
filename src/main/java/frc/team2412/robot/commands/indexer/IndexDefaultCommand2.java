package frc.team2412.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team2412.robot.RobotState;
import frc.team2412.robot.RobotState.IntakeDirection;
import frc.team2412.robot.RobotState.UnbalancedSide;
import frc.team2412.robot.subsystems.IndexerMotorSubsystem;
import frc.team2412.robot.subsystems.IndexerSensorSubsystem;

public class IndexDefaultCommand2 extends CommandBase {
  private IndexerMotorSubsystem m_indexerMotorSubsystem;
  private IndexerSensorSubsystem m_indexerSensorSubsystem;
  private static IntakeDirection s_lastIntakeDirection = IntakeDirection.NONE;

  private static enum IndexDirection {
    IN, OUT, OFF, DISABLED
  }

  private static enum IndexCommandEntry {
    //    Bit flags                     Intake Off                              Intake On
    //  Valid   expected     Front motor          Back motor         Front motor          Back motor
    // Don't move, as there's nothing in the outer slots -or-
    // Back side has inner and outer slots full, front side only has middle slot full
    A(0b100001, 0b000000, IndexDirection.OFF, IndexDirection.OFF, IndexDirection.OFF, IndexDirection.OFF),
    B(0b101111, 0b101010, IndexDirection.OFF, IndexDirection.OFF, IndexDirection.OFF, IndexDirection.OFF),

    // Back side has outer slot full and inner slot empty, so move back ball(s) in
    // Front side has outer slot empty and inner or middle slot full, so don't move
    C(0b101111, 0b100010, IndexDirection.OFF, IndexDirection.IN,  IndexDirection.OFF, IndexDirection.IN),
    D(0b101101, 0b100100, IndexDirection.OFF, IndexDirection.IN,  IndexDirection.OFF, IndexDirection.IN),

    // Back side has outer and inner slots full, so don't move.  Front side has inner slot full so disable taking more balls
    E(0b101100, 0b101100, IndexDirection.OFF, IndexDirection.OFF, IndexDirection.DISABLED, IndexDirection.DISABLED),
    // Back side has outer slot full and inner slot empty, so move balls in.  Front side has inner slot full so disable taking more balls
    F(0b101101, 0b100101, IndexDirection.OFF, IndexDirection.IN,  IndexDirection.DISABLED, IndexDirection.DISABLED),

    // Back side has outer and inner slots full and front side is empty, so move front side balls in if intake is on
    G(0b101111, 0b101000, IndexDirection.OFF, IndexDirection.OFF, IndexDirection.IN,  IndexDirection.OFF),

    // Back side has outer slot empty and front side has inner slot empty and outer slot full, so move front side in
    H(0b100101, 0b000001, IndexDirection.IN,  IndexDirection.OFF, IndexDirection.IN,  IndexDirection.OFF),
    // Back side has inner and outer slots full, and front side has inner slot empty and outer slot full, so move front side in
    J(0b101101, 0b101001, IndexDirection.IN,  IndexDirection.OFF, IndexDirection.IN,  IndexDirection.OFF),

    // Back side has outer slot full and inner slot open, and front side has inner and middle open.  Move back side in, and front side in if intake is on 
    K(0b101110, 0b100000, IndexDirection.OFF, IndexDirection.IN,  IndexDirection.IN,  IndexDirection.IN),

    // Back side has outer slot full and inner slot open, and front side has inner open and middle and outer full.  Move both sides in
    L(0b101111, 0b100011, IndexDirection.IN,  IndexDirection.IN,  IndexDirection.IN,  IndexDirection.IN),

    // Back side has outer slot open and front side has inner and outer slots full, so move back side out and front side in
    M(0b100101, 0b000101, IndexDirection.IN,  IndexDirection.OUT, IndexDirection.IN,  IndexDirection.OUT);

    public final int validBits;
    public final int expectedBits;
    private final IndexDirection intakeOffFrontIndexDir;
    private final IndexDirection intakeOffBackIndexDir;
    private final IndexDirection intakeOnFrontIndexDir;
    private final IndexDirection intakeOnBackIndexDir;

    // Get index motor directions for a given Intake state
    public IndexDirection getFrontIndexDirection(boolean intakeOn) {
      return intakeOn ? intakeOnFrontIndexDir : intakeOffFrontIndexDir;
    }

    public IndexDirection getBackIndexDirection(boolean intakeOn) {
      return intakeOn ? intakeOnBackIndexDir : intakeOffBackIndexDir;
    }

    private IndexCommandEntry(int validBits, int expectedBits, IndexDirection intakeOffFrontIndexDir, IndexDirection intakeOffBackIndexDir,
    IndexDirection intakeOnFrontIndexDir, IndexDirection intakeOnBackIndexDir) {
      assert ((validBits & expectedBits) == expectedBits);
      this.validBits = validBits;
      this.expectedBits = expectedBits;
      this.intakeOffFrontIndexDir = intakeOffFrontIndexDir;
      this.intakeOffBackIndexDir = intakeOffBackIndexDir;
      this.intakeOnFrontIndexDir = intakeOnFrontIndexDir;
      this.intakeOnBackIndexDir = intakeOnBackIndexDir;
    }
  }

  public IndexDefaultCommand2(IndexerMotorSubsystem indexerMotorSubsystem,
      IndexerSensorSubsystem indexerSensorSubsystem) {
    m_indexerMotorSubsystem = indexerMotorSubsystem;
    m_indexerSensorSubsystem = indexerSensorSubsystem;
    addRequirements(indexerMotorSubsystem);
  }

  @Override
  public void execute() {
    IntakeDirection intakeDirection = RobotState.getintakeDirection();
    if (intakeDirection == IntakeDirection.NONE) {
      if (s_lastIntakeDirection == IntakeDirection.BACK) {
        intakeDirection = IntakeDirection.BACK;
      } else {
        intakeDirection = IntakeDirection.FRONT;
      }
    } else {
      s_lastIntakeDirection = intakeDirection;
    }

    int sensorBitmap = 0;
    switch (intakeDirection) {
    case BOTH:
    case FRONT:
      sensorBitmap = m_indexerSensorSubsystem.getSensorBitmapFrontLSB();
      break;
    case BACK:
      sensorBitmap = m_indexerSensorSubsystem.getSensorBitmapBackLSB();
      break;
    case NONE:
      assert (false);
      break;
    }

    System.out.println("222");
    // m_indexerMotorSubsystem.setMidMotor(-0.2);
    if (m_indexerSensorSubsystem.getIndexFrontSensorValue()) {
      // m_indexerMotorSubsystem.setFrontMotor(-1);
      m_indexerMotorSubsystem.stopFrontPID(-7);
      // if (!m_indexerSensorSubsystem.getIndexBackInnerSensorValue()){
      m_indexerMotorSubsystem.stopBackPID(7);
      // else
      // m_indexerMotorSubsystem.setBackMotor(0);
    } else
      m_indexerMotorSubsystem.setFrontMotor(0);
    if (e && m_indexerSensorSubsystem.allInnerSensorsOn()) {
      m_indexerMotorSubsystem.stopFrontPID(-1);
      // m_indexerMotorSubsystem.stopBackPID(1);
      e = false;
    }
    // if(m_indexerSensorSubsystem.getIndexFrontInnerSensorValue())
    // m_indexerMotorSubsystem.setMidMotor(-0.3);

  }

  @Override
  public boolean isFinished() {
    return true;
  }

  private int clearAllFlags(int value, int flags) {
    return value &= ~flags;
  }
}