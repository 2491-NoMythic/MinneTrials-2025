package frc.robot.subsystems;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PopcornConstants;

public class PopcornShooter extends SubsystemBase{
    TalonFX elevatorMotor;
    TalonFX shooterMotor;
    public PopcornShooter(){
        elevatorMotor = new TalonFX(PopcornConstants.ELEVATOR_MOTOR_ID);
        shooterMotor = new TalonFX(PopcornConstants.SHOOTER_MOTOR_ID);
        elevatorMotor.getConfigurator().apply(new TalonFXConfiguration()
            .withCurrentLimits(new CurrentLimitsConfigs()
                .withStatorCurrentLimit(50)
                .withStatorCurrentLimitEnable(true)));
        shooterMotor.getConfigurator().apply(new TalonFXConfiguration()
            .withCurrentLimits(new CurrentLimitsConfigs()
                .withStatorCurrentLimitEnable(true)
                .withStatorCurrentLimit(10))
            .withMotorOutput(new MotorOutputConfigs()
                .withInverted(InvertedValue.Clockwise_Positive)));
    }

    public void setElevator(double speed){
        elevatorMotor.set(speed);
    }

    public void setShooter(double speed){
       shooterMotor.set(speed);
    }

    public void setShooterVoltage(double volts) {
        shooterMotor.setControl(new VoltageOut(volts));
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
