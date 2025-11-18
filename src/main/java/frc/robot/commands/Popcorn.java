// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.PopcornConstants;
import frc.robot.subsystems.PopcornIntake;
import frc.robot.subsystems.PopcornShooter;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class Popcorn extends Command {
  /** Creates a new Popcorn. */
  private final PopcornShooter shooter;
  private final PopcornIntake intake;
  BooleanSupplier shootButton;
  BooleanSupplier intakeButton;

  public Popcorn(PopcornShooter pShooter, PopcornIntake pIntake, BooleanSupplier shootButton, BooleanSupplier intakeButton) {
    // Use addRequirements() here to declare subsystem dependencies.
    shooter = pShooter;
    intake = pIntake;
    this.shootButton = shootButton;
    this.intakeButton = intakeButton;
    addRequirements(pShooter);
    addRequirements(pIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter.setShooter(PopcornConstants.SHOOT_SPEED);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (shootButton.getAsBoolean()) {
     shooter.setElevator(PopcornConstants.ELEVATOR_SPEED);
    }
    else {
      shooter.stopElevator();
    }

    if (intakeButton.getAsBoolean()) {
      shooter.setShooter(PopcornConstants.SHOOT_SPEED);
      intake.setintake(PopcornConstants.INTAKE_SPEED);
     }
     else {
       shooter.stopShooter();
       intake.stopIntake();
     }

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
