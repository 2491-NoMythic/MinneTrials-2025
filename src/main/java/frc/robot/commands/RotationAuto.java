// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class RotationAuto extends Command {
  Drivetrain drivetrain;
  double rotation, time;
  Timer driveTimer;
  
  /* Creates a new DriveTime. */
  public RotationAuto(Drivetrain drivetrain, double rotation, double time) { 
    this.drivetrain = drivetrain;
    this.time = time;
    this.rotation = rotation; // The robot's rotation rate -1.0 to 1.0 CounterClockwise is positive.
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    driveTimer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.adrive(0, rotation);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return driveTimer.get() > time;
  }
}