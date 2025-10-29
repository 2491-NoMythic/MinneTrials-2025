// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;


/** An example command that uses an example subsystem. */
public class Drive extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private DriveTrain commandDriveTrain;
  private Joystick commandJoystick;

  private Drive drive;

  public Drive(DriveTrain drivesystem, Joystick drivejoy) {
    commandDriveTrain = drivesystem;
    commandJoystick = drivejoy;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivesystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Uses the getThrottle function thing to set how much we have moved our throttle(the little switch)
    double yAxis = commandJoystick.getY();

    // If the absolute value of our throttle(how much we are moving the stick forward or back) is less than our deadzone, don't move.
    if (Math.abs(yAxis) < Constants.deadZone){
      yAxis = 0;
    }
    // Uses our previously made Arcade drive and throttle to set how much moving the joystick will rotate or move the robot)
      commandDriveTrain.adrive(yAxis*Constants.maxDrivetrainPow, commandJoystick.getZ()*-0.50);
      
    // double curPosition = commandDrivetrain.GetPosition();
    // System.out.println(curPosition);
     
      double curSpeed = commandDriveTrain.GetSpeed();
      System.out.println(curSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
