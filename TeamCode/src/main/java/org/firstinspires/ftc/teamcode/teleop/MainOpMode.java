package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drive;

@TeleOp(name="MainOpMode")
public class MainOpMode extends OpMode {
    Drive drive;

    @Override
    public void init() {
        drive = new Drive();
        drive.init(hardwareMap);
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
        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;

        drive.drive(forward, strafe, rotate);

        telemetry.addData("Forward", forward);
        telemetry.addData("Strafe", strafe);
        telemetry.addData("Rotate", rotate);
    }

}
