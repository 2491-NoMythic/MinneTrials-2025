package frc.robot.subsystems;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PopcornIntake extends SubsystemBase{
    SparkMax intakeMotor;
    SparkBaseConfig intakeConfig;
    public PopcornIntake(){
        intakeMotor = new SparkMax(0, null);
        intakeConfig = new SparkMaxConfig();
        intakeConfig.smartCurrentLimit(25);
        intakeMotor.configure(intakeConfig, null, null);
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
