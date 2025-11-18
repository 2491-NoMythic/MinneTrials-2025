package frc.robot.subsystems;

import com.revrobotics.spark.SparkAnalogSensor;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ButterEndEffectorConstants;


public class ButterArm extends SubsystemBase{

    SparkMax ButterArmMotor;
    public Boolean limitSwitchPressed;

    public ButterArm(){

        ButterArmMotor = new SparkMax(ButterEndEffectorConstants.BUTTER_RAISE_MOTOR_ID, null);
    }

    public void set(double speed){
        ButterArmMotor.set(speed);
    }

    public void stopButterArmMotor(){
        ButterArmMotor.set(0);
    }

    public void raise(double radians, double timeframe){
        if(timeframe == 0) {timeframe = 2;} //Default value for timeframe is two seconds
        
        double speed = radians/(2 * ButterEndEffectorConstants.pi); //Convert radians to RPM
        speed = speed * (60/timeframe); //Convert RPM to rotations per timeframe

        if(speed > 0 && limitSwitchPressed){ //If you're moving the arm down and the limit switch is hit,
            return;                          //refuse to do so
        } else {
            ButterArmMotor.set(speed); //Otherwise, set the motor at that speed
        }
    }

    public void lower(double speed){

    }

    public void limitSwitchHit(Boolean pressed){
        limitSwitchPressed = pressed; //Update the limit switch state

        if(ButterArmMotor.get() > 0){ //If you're moving the arm down and the limit switch is hit,
            stopButterArmMotor();     //stop doing so
        }
    }



}
