// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.PopcornIntake;
import frc.robot.subsystems.PopcornShooter;

/** An example command that uses an example subsystem. */
public class PopcornAuto extends SequentialCommandGroup {

  public PopcornAuto(Drivetrain drivetrain, PopcornIntake popcornIntake, PopcornShooter popcornShooter) {
    addCommands(
      new InstantCommand(()->popcornShooter.setShooterVoltage(9)),
      new DriveAuto(drivetrain, 0.4, 4), 
      new RotationAuto(drivetrain, -0.4, 1.5),
      new ParallelCommandGroup(new DriveAuto(drivetrain, 0.4, 3.5), new PopcornIntakeAuto(popcornIntake, 3.5)),
      new ParallelRaceGroup(new PopcornShooterAuto(popcornShooter, 7), new PopcornIntakeAuto (popcornIntake,7))
    );
  }
  
}
