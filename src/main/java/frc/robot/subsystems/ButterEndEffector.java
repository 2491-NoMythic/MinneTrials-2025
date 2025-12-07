package frc.robot.subsystems;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkAnalogSensor;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ButterEndEffectorConstants;


public class ButterEndEffector extends SubsystemBase {
     TalonFX ButterInMotor;
     TalonFX ButterLeftInMotor;


    public ButterEndEffector(){
        ButterInMotor = new TalonFX(ButterEndEffectorConstants.BUTTER_IN_MOTOR_ID);
        ButterLeftInMotor = new TalonFX(ButterEndEffectorConstants.BUTTER_LEFT_IN_MOTOR_ID);
    }

    public void set(double speed){
        ButterInMotor.set(speed);
        ButterLeftInMotor.set(-speed);

    }
    public void stopButterMotor(){
        ButterInMotor.set(0);
        ButterLeftInMotor.set(0);
    }
    
    public void runButterMotor(double RPM) {
        ButterInMotor.set(RPM);
        ButterLeftInMotor.set(-RPM);
    }
}
