// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 4;
    public static final int leftDriveID = 1;
    public static final int rightDriveID = 2;
    public static final int joystickPort = 0;
  }
  public static class ButterEndEffectorConstants {
    public static final int BUTTER_IN_MOTOR_ID = 0;
    public static final int BUTTER_RAISE_MOTOR_ID = 0;
    public static final double pi = 3.1415926;
  }

  public static final double deadZone = 0.15;
  //The constant that we will mutiply with our speed to keep it below 10.5 ft per second. If we find that moving the stick to 0.5 is 9fs,
  // and 0.6 is 11fs, we will multiply our speed by 0.5(in Drivetrain), so that it's max speed cannot be greater than 0.5. (1*0.5 = 0.5)
  public static final double maxDrivetrainPow = 0.75;
}
