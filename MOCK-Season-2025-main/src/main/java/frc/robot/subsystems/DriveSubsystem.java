// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.io.ObjectInputFilter.Config;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  public static SparkMax leftOne = new SparkMax(1, MotorType.kBrushless);
  public static SparkMax leftTwo = new SparkMax(2, MotorType.kBrushless);
    public static  SparkMax rightOne = new SparkMax(3, MotorType.kBrushless);
    public static SparkMax rightTwo = new SparkMax(4, MotorType.kBrushless);
    public SparkMaxConfig left = new SparkMaxConfig();
    public SparkMaxConfig right = new SparkMaxConfig();

   public XboxController driveController = new XboxController(0);

  public DriveSubsystem() {
    left
    .inverted(false)
    .idleMode(IdleMode.kCoast);
    
    leftOne.configure(left, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    leftTwo.configure(left, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    right
    .inverted(true)
    .idleMode(IdleMode.kCoast); 

    rightOne.configure(right, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightTwo.configure(right, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    leftOne.set(driveController.getLeftY());
    leftTwo.set(driveController.getLeftY());
    rightOne.set(driveController.getRightY());
    rightTwo.set(driveController.getRightY());

    if(driveController.getLeftY() <0.05 && driveController.getLeftY() >-0.05){
      leftOne.stopMotor();
      leftTwo.stopMotor();
    }

    if(driveController.getRightY() <0.05 && driveController.getRightY() >-0.05){
      rightOne.stopMotor();
      rightTwo.stopMotor();
    }
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
