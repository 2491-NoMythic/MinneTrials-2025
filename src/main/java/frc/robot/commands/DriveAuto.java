// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class DriveAuto extends Command {
  Drivetrain drivetrain;
  double driveSpeed, time;
  Timer driveTimer;
  
  /** Creates a new DriveTime. */
  public DriveAuto(Drivetrain drivetrain, double speed, double time) { 
    this.drivetrain=drivetrain;
    this.time=time;
    this.driveSpeed=speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    driveTimer=new Timer();
    

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTimer.start();    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.adrive(-driveSpeed, 0);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return driveTimer.get()>time;
   
  }
}