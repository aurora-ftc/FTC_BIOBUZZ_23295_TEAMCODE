package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

import dev.frozenmilk.dairy.core.util.supplier.numeric.EnhancedDoubleSupplier;
import dev.frozenmilk.dairy.pasteurized.Pasteurized;
import dev.frozenmilk.dairy.pasteurized.SDKGamepad;

import dev.frozenmilk.dairy.core.util.supplier.logical.EnhancedBooleanSupplier;

@TeleOp(name="TestOpMode", group="MainOps")
public class TestOpMode extends OpMode {
    private Drive drive;
    private Intake intake;
    private EnhancedBooleanSupplier intakeOn;
    private EnhancedDoubleSupplier strafe, forward, rotate;

    @Override
    public void init() {
        drive = new Drive();
        intake = new Intake();

        drive.init(hardwareMap);
        intake.init(hardwareMap);

        Pasteurized.gamepad1(new SDKGamepad(gamepad1));
        Pasteurized.gamepad2(new SDKGamepad(gamepad2));

        intakeOn = Pasteurized.gamepad1().cross();

        forward = Pasteurized.gamepad1().rightStickY();
        strafe = Pasteurized.gamepad1().rightStickX();
        rotate = Pasteurized.gamepad1().leftStickX();
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
        intakeOn.toggleTrue();

        drive.drive(forward.state(), strafe.state(), rotate.state());

        if (intakeOn.state())
            intake.on();
        else
            intake.off();

        telemetry.addData("Intake", intakeOn.state());
        telemetry.update();
    }

}
