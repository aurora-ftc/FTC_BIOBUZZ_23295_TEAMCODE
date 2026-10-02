package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.constants.Constants.HWNames.*;
import static org.firstinspires.ftc.teamcode.constants.Constants.MAX_SPEED;

import org.firstinspires.ftc.teamcode.util.DcMotorGroup;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import dev.frozenmilk.util.units.angle.Angle;
//import dev.frozenmilk.util.units.angle.Angles;

public class Drive {
    private DcMotorEx flMotor, frMotor, blMotor, brMotor;
    private DcMotorGroup driveMotors;
    // private GoBildaPinpointDriver.GoBildaOdometryPods odo;

    public void init(HardwareMap hwMap) {
        flMotor = hwMap.get(DcMotorEx.class, FL_MOTOR);
        frMotor = hwMap.get(DcMotorEx.class, FR_MOTOR);
        blMotor = hwMap.get(DcMotorEx.class, BL_MOTOR);
        brMotor = hwMap.get(DcMotorEx.class, BR_MOTOR);

        // odo = hwMap.get(GoBildaPinpointDriver.GoBildaOdometryPods.class )

        driveMotors = new DcMotorGroup(flMotor, frMotor, blMotor, brMotor);

        driveMotors.setPower(0.0);
        driveMotors.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        driveMotors.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        flMotor.setDirection(DcMotorEx.Direction.REVERSE);
        frMotor.setDirection(DcMotorEx.Direction.FORWARD);
        brMotor.setDirection(DcMotorEx.Direction.FORWARD);
        blMotor.setDirection(DcMotorEx.Direction.REVERSE);
    }

    public void drive(double forward, double strafe, double rotate) {
        double flPower = forward + strafe + rotate;
        double frPower = forward - strafe - rotate;
        double blPower = forward - strafe + rotate;
        double brPower = forward + strafe - rotate;

        double maxPower = Math.max(Math.abs(flPower), Math.abs(frPower));
        maxPower = Math.max(maxPower, Math.abs(blPower));
        maxPower = Math.max(maxPower, Math.abs(brPower));

        if (maxPower > 1.0) {
            flPower /= maxPower;
            frPower /= maxPower;
            blPower /= maxPower;
            brPower /= maxPower;
        }

        flMotor.setPower(flPower * MAX_SPEED);
        frMotor.setPower(frPower * MAX_SPEED);
        blMotor.setPower(blPower * MAX_SPEED);
        brMotor.setPower(brPower * MAX_SPEED);
    }

//    public void driveFieldCentric(double forward, double strafe, double rotate) {
//        //updateOdoHeading();
//        double r = Math.hypot(strafe, forward);
//
//        //Converts X, Y coordinates to polar coordinates
//        Angle bearing = Angles.relativeRad(Math.atan2(forward, strafe));
//        Angle heading = Angles.relativeRad(/*current heading, 0 for now*/0);
//        Angle theta = bearing.minus(heading);
//
//        //Converts values back to X, Y coordinates from polar
//        double newForward = r * Math.sin(theta.getValue());
//        double newStrafe = r * Math.cos(theta.getValue());
//
//        drive(newForward, newStrafe, rotate);
//    }

}
