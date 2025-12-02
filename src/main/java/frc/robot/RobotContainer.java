// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.Drive;
import frc.robot.commands.DriveAuto;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.PopcornAuto;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.PopcornIntake;
import frc.robot.subsystems.PopcornShooter;
import frc.robot.subsystems.ButterArm;
import frc.robot.subsystems.ButterEndEffector;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain conDrivetrain = new Drivetrain();
  private final PopcornShooter conPopcornShooter = new PopcornShooter();
  private final PopcornIntake conPopcornIntake = new PopcornIntake();
  private final ButterEndEffector conButterEndEffector = new ButterEndEffector();
  private final ButterArm conButterArm = new ButterArm();
  private final Joystick conJoystick = new Joystick(OperatorConstants.joystickPort);
  private final PS4Controller controller = new PS4Controller(ControllerConstants.kControllerPort);
  private SendableChooser<SequentialCommandGroup> chooser;


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    chooser = new SendableChooser<SequentialCommandGroup>();

    chooser.addOption("DriveStraight", new SequentialCommandGroup(new DriveAuto(conDrivetrain, 0, 0)));
    chooser.addOption("PopcornAuto", new PopcornAuto(conDrivetrain, conPopcornIntake, conPopcornShooter));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    conDrivetrain.setDefaultCommand(new Drive(conDrivetrain, conJoystick));

    new Trigger(()->controller.getRightY() < -0.5).whileTrue(new StartEndCommand(()->conPopcornShooter.setElevator(0.2), ()->conPopcornShooter.stopElevator(), conPopcornShooter));
    new Trigger(()->controller.getRightY() > 0.5).whileTrue(new StartEndCommand(()->conPopcornShooter.setElevator(-0.2), ()->conPopcornShooter.stopElevator(), conPopcornShooter));
    new Trigger(()->controller.getR1Button()).whileTrue(new StartEndCommand(()->conButterEndEffector.set(-0.2), ()->conButterEndEffector.stopButterMotor(), conButterEndEffector));
    new Trigger(()->controller.getR2Button()).whileTrue(new StartEndCommand(()->conButterEndEffector.set(1), ()->conButterEndEffector.stopButterMotor(), conButterEndEffector));
   // new Trigger(()->controller.)
  }

  public void teleOpInit() {
    conPopcornShooter.setShooter(0.3);
    conPopcornIntake.setintake(0.3);
  }

  public void disabledInit() {
    conPopcornShooter.stopShooter();
    conPopcornIntake.stopIntake();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return chooser.getSelected();
  }
}
