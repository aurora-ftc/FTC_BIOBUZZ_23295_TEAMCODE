package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import

import org.firstinspires.ftc.teamcode.constants.Constants.*;

public class Drive {
    DcMotorEx flMotor, frMotor, blMotor, brMotor;

    public void init(HardwareMap hwMap) {
        flMotor = hwMap.get(DcMotorEx.class, HWNames.flMotor);
        frMotor = hwMap.get(DcMotorEx.class, "flmotor");
        blMotor = hwMap.get(DcMotorEx.class, "flmotor");
        brMotor = hwMap.get(DcMotorEx.class, "flmotor");

        brMotor.setDirection(DcMotorEx.Direction.REVERSE);
        blMotor.setDirection(DcMotorEx.Direction.REVERSE);
    }

    public void drive(double forward, double strafe, double rotate) {
        double flPower = forward + strafe + rotate;
        double flPower = forward + strafe + rotate;
        double flPower = forward + strafe + rotate;
        double flPower = forward + strafe + rotate;

        double maxPower = Math.max(//all that stuff)

        //Divide all by maxPower

        //Set Power
    }

}
