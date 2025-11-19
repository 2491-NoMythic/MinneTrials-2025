// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ButterArm;
import frc.robot.subsystems.ButterEndEffector;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ButterEndEffectorConstants;


/** An example command that uses an example subsystem. */
public class ButterLift extends Command {
  BooleanSupplier raiseButton;
  //BooleanSupplier lowerButton;
  
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ButterArm butterArm;

  /**
   * Creates a new ButterLift.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ButterLift(ButterArm subsystem, BooleanSupplier raiseButton) {
    butterArm = subsystem;
    this.raiseButton = raiseButton;
    //this.lowerButton = lowerButton;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (raiseButton.getAsBoolean()) {
     butterArm.raise(ButterEndEffectorConstants.BUTTER_ARM_RADIANS, ButterEndEffectorConstants.BUTTER_ARM_TIMEFRAME);
    }
    else {
      butterArm.raise(-ButterEndEffectorConstants.BUTTER_ARM_RADIANS, ButterEndEffectorConstants.BUTTER_ARM_TIMEFRAME);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
