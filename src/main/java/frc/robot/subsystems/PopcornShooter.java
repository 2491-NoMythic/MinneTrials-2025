package frc.robot.subsystems;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PopcornConstants;

public class PopcornShooter extends SubsystemBase{
    SparkMax elevatorMotor;
    SparkMax shooterMotor;
    public PopcornShooter(){
        elevatorMotor = new SparkMax(PopcornConstants.ELEVATOR_MOTOR_ID, null);
        shooterMotor = new SparkMax(PopcornConstants.SHOOTER_MOTOR_ID, null);
    }

    public void setElevator(double speed){
        elevatorMotor.set(speed);
    }

    public void setShooter(double speed){
       shooterMotor.set(speed);
    }

    public void stopShooter(){
        shooterMotor.set(0);
    }

    public void stopElevator(){
        elevatorMotor.set(0);
      }

    public void runElevatorUp(double RPM){
        elevatorMotor.set(RPM);
      }
}
