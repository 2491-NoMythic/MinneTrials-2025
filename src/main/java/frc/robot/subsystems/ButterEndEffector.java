package frc.robot.subsystems;
import com.revrobotics.spark.SparkAnalogSensor;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ButterEndEffectorConstants;


public class ButterEndEffector extends SubsystemBase {
     SparkMax ButterInMotor;
     SparkAnalogSensor ButterSensor;


    public ButterEndEffector(){
        ButterInMotor = new SparkMax(ButterEndEffectorConstants.BUTTER_IN_MOTOR_ID, null);
        ButterSensor = ButterInMotor.getAnalog();

    }

    public void set(double speed){
        ButterInMotor.set(speed);

    }
    public void stopButterMotor(){
        ButterInMotor.set(0);
    }
    
    public void runButterMotor(double RPM) {
        ButterInMotor.set(RPM);
    }
}
