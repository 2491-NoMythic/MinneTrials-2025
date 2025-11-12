// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;


/** An example command that uses an example subsystem. */
public class Drive extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private Drivetrain commandDrivetrain;
  private Joystick commandJoystick;

  private Drive drive;

  public Drive(Drivetrain drivesystem, Joystick drivejoy) {
    commandDrivetrain = drivesystem;
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
      commandDrivetrain.adrive(commandJoystick.getZ()*0.50, -yAxis*Constants.maxDrivetrainPow); //Rotation and speed are swapped from what the tooltip would imply?
      
    // double curPosition = commandDrivetrain.GetPosition();
    // System.out.println(curPosition);
     
      double curSpeed = commandDrivetrain.GetSpeed();
      System.out.println(curSpeed);
      System.out.println(yAxis);
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
