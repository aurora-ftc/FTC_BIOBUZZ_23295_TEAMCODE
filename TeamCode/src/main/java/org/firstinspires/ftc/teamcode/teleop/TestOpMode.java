package org.firstinspires.ftc.teamcode.teleop;

import com.bylazar.configurables.PanelsConfigurables;
import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Launcher;


@TeleOp(name="TestOpMode", group="MainOps")
public class TestOpMode extends OpMode {
    private Drive drive;
    private Intake intake;
    private Launcher launcher;
    private boolean intakeOn;
    private double power;
    TelemetryManager panelsTelemetry;

    @Override
    public void init() {
        drive = new Drive();
        intake = new Intake();
        launcher = new Launcher();

        intakeOn = false;

        power = 0.5;

        drive.init(hardwareMap);
        intake.init(hardwareMap);
        launcher.init(hardwareMap);

        PanelsConfigurables.INSTANCE.refreshClass(TestOpMode.class);
        telemetry = new JoinedTelemetry(telemetry, PanelsTelemetry.INSTANCE.getFtcTelemetry());

        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
    }

    @Override
    public void init_loop() {
        super.init_loop();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {
        double forward = gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;

        if (gamepad1.crossWasPressed()) intakeOn = !intakeOn;
        if (gamepad1.xWasPressed()) launcher.toggle();

        drive.drive(forward, strafe, rotate);

        if (intakeOn) intake.on();
        else intake.off();

        if (gamepad1.dpadDownWasPressed() && power > 0.01) power -= 0.01;
        else if (gamepad1.dpadUpWasPressed() && power < 0.99) power += 0.01;
        launcher.setPower(power);

        telemetry.addData("Power", power);
        telemetry.addData("RPM", launcher.getRPM());
        telemetry.addData("Rate", launcher.getRate());

        double[] dataPID = launcher.getGraphData();
        panelsTelemetry.addData("target", dataPID[0]);
        panelsTelemetry.addData("current", dataPID[1]);
        panelsTelemetry.addData("power", dataPID[2]);

        panelsTelemetry.update();
        telemetry.update();
    }

    @Override
    public void stop() {
        super.stop();
    }

}
