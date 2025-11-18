package frc.robot.subsystems;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PopcornIntake extends SubsystemBase{
    SparkMax intakeMotor;

    public PopcornIntake(){
        intakeMotor = new SparkMax(0, null);
    }

    public void setintake(double speed){
        intakeMotor.set(speed);
    }

    public void stopIntake(){
        intakeMotor.set(0);
    }

    public void runIntakeUp(double RPM){
        intakeMotor.set(RPM);
    }
}
