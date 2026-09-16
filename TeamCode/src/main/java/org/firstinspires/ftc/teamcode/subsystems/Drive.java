package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.constants.Constants.HWNames.*;
import static org.firstinspires.ftc.teamcode.constants.Constants.MAX_SPEED;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.util.DcMotorGroup;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Locale;

public class Drive {
    private DcMotorEx flMotor, frMotor, blMotor, brMotor;
    private DcMotorGroup driveMotors;

    public void init(HardwareMap hwMap) {
        flMotor = hwMap.get(DcMotorEx.class, FL_MOTOR);
        frMotor = hwMap.get(DcMotorEx.class, FR_MOTOR);
        blMotor = hwMap.get(DcMotorEx.class, BL_MOTOR);
        brMotor = hwMap.get(DcMotorEx.class, BR_MOTOR);

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

        double maxPower = 1.0;
        maxPower = Math.max(brPower, Math.max(blPower, Math.max(flPower, frPower)));

        flMotor.setPower(MAX_SPEED * flPower / maxPower);
        frMotor.setPower(MAX_SPEED * frPower / maxPower);
        blMotor.setPower(MAX_SPEED * blPower / maxPower);
        brMotor.setPower(MAX_SPEED * brPower / maxPower);
    }

    public void driveFieldCentric(double forward, double strafe, double rotate) {

    }

}
