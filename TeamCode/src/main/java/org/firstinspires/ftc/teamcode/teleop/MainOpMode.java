package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="MainOpMode")
public class MainOpMode extends OpMode {
    DcMotor motor;

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotor.class, "motor");
        // runs once
    }

    @Override
    public void init_loop() {
        super.init_loop();
        // run in loop when init
    }

    @Override
    public void start() {
        super.start();
        // runs once when started
    }

    @Override
    public void loop() {
        // runs in a loop
        if (gamepad1.a) {
            motor.setPower(0.5);
        } else {
            motor.setPower(0);
        }
        telemetry.addData("Motor Power", motor.getPower());
    }
}
