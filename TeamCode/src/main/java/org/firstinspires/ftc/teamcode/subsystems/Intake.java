package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.constants.Constants.HWNames.INTAKE;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    DcMotorEx intake;
    public void init(HardwareMap hwMap) {
        intake = hwMap.get(DcMotorEx.class, INTAKE);

        intake.setPower(0.0);
        intake.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        intake.setDirection(DcMotorEx.Direction.REVERSE);
        intake.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void on() {
        intake.setPower(1.0);
    }

    public void off() {
        intake.setPower(0.0);
    }
}
