// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.PopcornIntake;
import frc.robot.subsystems.PopcornShooter;

/** An example command that uses an example subsystem. */
public class PopcornAuto extends SequentialCommandGroup {

  public PopcornAuto(Drivetrain drivetrain, PopcornIntake popcornIntake, PopcornShooter popcornShooter) {
    addCommands(
      new DriveAuto(drivetrain, 0, 0), 
      new RotationAuto(drivetrain, 0, 0),
      new ParallelCommandGroup(new DriveAuto(drivetrain, 0, 0), new PopcornIntakeAuto(popcornIntake, 0)),
      new PopcornShooterAuto(popcornShooter, 0)
    );
  }
  
}
