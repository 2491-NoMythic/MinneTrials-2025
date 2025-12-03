package frc.robot.subsystems;

import java.io.PrintStream;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.revrobotics.spark.SparkAnalogSensor;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ButterEndEffectorConstants;


public class ButterArm extends SubsystemBase{

    TalonFX ButterArmMotor;

    DigitalInput upperLimitSwitch;
    DigitalInput lowerLimitSwitch;

    double currentSpeed;

    TalonFXConfiguration ButterArmConfig;

    public ButterArm(){
        ButterArmMotor = new TalonFX(ButterEndEffectorConstants.BUTTER_RAISE_MOTOR_ID);

        ButterArmConfig = new TalonFXConfiguration()
            .withCurrentLimits(new CurrentLimitsConfigs()
                .withStatorCurrentLimit(25)
                .withSupplyCurrentLimit(50)
                .withStatorCurrentLimitEnable(true)
                .withSupplyCurrentLimitEnable(true))
            .withMotorOutput(new MotorOutputConfigs()
                .withInverted(InvertedValue.Clockwise_Positive));

        ButterArmMotor.getConfigurator().apply(ButterArmConfig);
    }

    public void set(double speed){
        currentSpeed = speed;
        ButterArmMotor.set(speed);
    }

    public void stopButterArmMotor(){
        currentSpeed = 0;
        ButterArmMotor.set(0);
    }

    /** Transforms an angle in radians into a number of motor rotations. */
    public double whatsThatInRotations(double radians){
        return radians/(2 * ButterEndEffectorConstants.pi); //Convert radians to rotations
    }

    /** Transforms an number of motor rotations into an angle in radians. */
    public double whatsThatInRadians(double rotations){
        return rotations * 2 * ButterEndEffectorConstants.pi;
    }

    public void startButterArmMotor(double radians, double timeframe){
        if(timeframe == 0) {timeframe = 2;} //Default value for timeframe is two seconds
        
        double rotations = whatsThatInRotations(radians);//double rotations = radians/(2 * ButterEndEffectorConstants.pi); //Convert radians to rotations
        double speed = rotations * (60/timeframe);  //Convert rotations to speed (RPM) over timeframe
                                                    //This is effectively distributing the single (decimal) number of rotations
                                                    //over the period of time that we are going to be running the motor for.
        currentSpeed = speed;
        
        if(!checkLimits(ButterArmMotor)) {
            set(speed);
        }

    }

    public Boolean checkLimits(TalonFX butterArmMotor2){
        double currentRotation = whatsThatInRadians(ButterArmMotor.getPosition().getValueAsDouble());
        if(currentSpeed > 0 && currentRotation >= ButterEndEffectorConstants.BUTTER_UPPER_LIMIT){
            System.out.println("Cancelled raising ButterArm because it's too high at (in rotations) " + currentRotation + " while the upper limit is (in rotations) " + ButterEndEffectorConstants.BUTTER_UPPER_LIMIT);

            stopButterArmMotor();
            return true;
        } else if(currentSpeed < 0 && currentRotation <= ButterEndEffectorConstants.BUTTER_LOWER_LIMIT) {
            System.out.println("Cancelled lowering ButterArm because it's too low at (in rotations) " + currentRotation + " while the lower limit is (in rotations) " + ButterEndEffectorConstants.BUTTER_LOWER_LIMIT);

            stopButterArmMotor();
            return true;
        } else {
            return false;
        }
        
    }
     
    public void setVoltage(double Voltage){
        ButterArmMotor.setControl(new VoltageOut(Voltage));
    }
}
