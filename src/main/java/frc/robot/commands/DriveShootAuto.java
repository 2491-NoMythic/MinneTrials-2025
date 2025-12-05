package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.PopcornIntake;
import frc.robot.subsystems.PopcornShooter;


public class DriveShootAuto extends SequentialCommandGroup{

    public DriveShootAuto(Drivetrain drivetrain,PopcornShooter popcornShooter){
        addCommands(
            new InstantCommand(()->popcornShooter.setShooterVoltage(9)),
            new DriveAuto(drivetrain,0.4,3),
            new PopcornShooterAuto(popcornShooter, 12)
                  );
    }

    
}
