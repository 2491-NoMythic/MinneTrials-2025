package frc.robot.subsystems;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PopcornShooter extends SubsystemBase{
    SparkMax elevatorMotor;
    SparkMax shooterMotor;
    public PopcornShooter(){
        elevatorMotor = new SparkMax(0, null);
        shooterMotor = new SparkMax(0, null);
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
