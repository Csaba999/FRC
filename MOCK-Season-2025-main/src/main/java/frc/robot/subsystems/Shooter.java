// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.XboxController;
import com.revrobotics.spark.SparkBase.ResetMode;


public class Shooter extends SubsystemBase {
  public static XboxController operatorXboxController = new XboxController(1);
  public static SparkMax complianceMotor = new SparkMax(6, MotorType.kBrushless);
  public static SparkMax shooterMotor = new SparkMax(5, MotorType.kBrushless);
  // Write hthe shooter code. the ID of the shooter motor is 5. 
  // Your task is to setup and confiugte the motor. And in the function shoot, have the motor have a speed set to 0.9. 
  // in stop motor the motor should stop
  // YOU are not setting up a controller, there is another method I will show next time we meet. 
  public Shooter() {
   
SparkMaxConfig config = new SparkMaxConfig();

config
    
    .idleMode(IdleMode.kCoast);
shooterMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
 complianceMotor.configure(config,  ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }


  public void shoot() {
     shooterMotor.set(0.9);
     complianceMotor.set(0.05);
  }
  

  public void compliance() {
    complianceMotor.set(0.05);

  }
public void stopMotor(){
    shooterMotor.stopMotor();
    complianceMotor.stopMotor();
  }

  @Override
  public void periodic(){
   // USE right bumber
      if(operatorXboxController.getRightBumperButton())
      {
        shoot(); 
      }
      if(operatorXboxController.getRightBumperButtonReleased())
      {
       stopMotor();
      }
  }
}
