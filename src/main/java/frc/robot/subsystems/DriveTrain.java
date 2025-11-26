// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.OperatorConstants;

public class Drivetrain extends SubsystemBase {
  private final TalonFX LeftDrive; 
  private final TalonFX RightDrive;
  private final DifferentialDrive BothDrive;
  
  /** Creates a new Drivetrain. */
  public Drivetrain() {
    LeftDrive = new TalonFX((OperatorConstants.leftDriveID));
    RightDrive = new TalonFX((OperatorConstants.rightDriveID));
    BothDrive = new DifferentialDrive(LeftDrive::set, RightDrive::set);

    LeftDrive.setNeutralMode(NeutralModeValue.Brake);
    LeftDrive.setSafetyEnabled(false);
    RightDrive.setNeutralMode(NeutralModeValue.Brake);
    RightDrive.setSafetyEnabled(false);
    


  }

  public void adrive(double speed, double rotation){
    BothDrive.arcadeDrive(speed, rotation);
  }

  public void tdrive(double lspeed, double rspeed){
    BothDrive.tankDrive(lspeed, rspeed);
  }

  public void stop(){
    BothDrive.tankDrive(0, 0);
  }

  public Double GetPosition(){
    return LeftDrive.getPosition().getValueAsDouble(); 
  }

  public Double GetSpeed(){
    return LeftDrive.getVelocity().getValueAsDouble()/12*-1; 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
