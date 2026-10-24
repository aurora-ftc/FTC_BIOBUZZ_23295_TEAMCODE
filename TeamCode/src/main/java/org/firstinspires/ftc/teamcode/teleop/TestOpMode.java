package org.firstinspires.ftc.teamcode.teleop;

import static org.firstinspires.ftc.teamcode.constants.Constants.HWNames.LAUNCHER_POLLEN;
import static org.firstinspires.ftc.teamcode.constants.Constants.HWNames.LAUNCHER_NECTAR;

import com.bylazar.configurables.PanelsConfigurables;
import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.subsystems.Wall;


@TeleOp(name="TestOpMode", group="MainOps")
public class TestOpMode extends OpMode {
    private Drive drive = new Drive();
    private Intake intake = new Intake();
    private Wall wall = new Wall();
    private Launcher nectarLauncher = new Launcher(LAUNCHER_NECTAR, 28, 5000, true);
    private Launcher pollenLauncher = new Launcher(LAUNCHER_POLLEN, 28, 5000, false);
    private boolean intakeOn;
    private double power;
    private TelemetryManager panelsTelemetry;
    private ElapsedTime matchTimer = new ElapsedTime();
    @Override
    public void init() {
        intakeOn = false;
        power = 0.72;

        drive.init(hardwareMap);
        intake.init(hardwareMap);
        wall.init(hardwareMap);
        nectarLauncher.init(hardwareMap);
        pollenLauncher.init(hardwareMap);

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
        matchTimer.reset();
    }

    @Override
    public void loop() {
        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;

        drive.driveFieldCentric(forward, strafe, rotate);

        if (gamepad1.squareWasPressed()) intakeOn = !intakeOn;

        if (gamepad1.triangleWasPressed()) nectarLauncher.toggle();
        if (gamepad1.circleWasPressed()) pollenLauncher.toggle();

        if (gamepad1.touchpadWasPressed()) drive.resetOdo();

        if (gamepad1.left_bumper) intake.down();
        else if (gamepad1.right_bumper) intake.up();
        else intake.stop();

        if (gamepad1.crossWasPressed()) wall.toggle();
        wall.periodic();

        if (gamepad1.rightTriggerWasPressed()) intake.blip();

        if (intake.blipTimer.seconds() <= 0.1) intake.reverse();
        else if (intakeOn || wall.wallTimer.seconds() < 2) intake.on();
        else intake.off();

        if (gamepad1.dpadDownWasPressed() && power >= 0.01) power -= 0.01;
        else if (gamepad1.dpadUpWasPressed() && power <= 1.00) power += 0.01;
        nectarLauncher.setPower(power);
        pollenLauncher.setPower(power);

        tele();
    }

    @Override
    public void stop() {
        super.stop();
    }

    private void tele() {
        double[] dataNectar = nectarLauncher.getGraphData();
        double[] dataPollen = pollenLauncher.getGraphData();
        double[] dataPos = drive.getData();

        telemetry.addData("General Power", power);
        telemetry.addData("Wall Pos", wall.getData()? "IN" : "OUT");

        telemetry.addLine("------NECTAR_LAUNCHER------");
        telemetry.addData("Target_N", dataNectar[0]);
        telemetry.addData("Current_N", dataNectar[1]);
        telemetry.addData("Power_N", dataNectar[2]);

        telemetry.addLine("------POLLEN_LAUNCHER------");
        telemetry.addData("Target_P", dataPollen[0]);
        telemetry.addData("Current_P", dataPollen[1]);
        telemetry.addData("Power_P", dataPollen[2]);

        telemetry.addLine("------POSITION------");
        telemetry.addData("Y (in)", dataPos[0]);
        telemetry.addData("X (in)", dataPos[1]);
        telemetry.addData("Heading (deg)", dataPos[2]);


//        panelsTelemetry.debug("Target", dataNectar[0]);
//        panelsTelemetry.debug("Current", dataNectar[1]);
//        panelsTelemetry.debug("Power", dataNectar[2]);
//
//        panelsTelemetry.addData("Target", dataNectar[0]);
//        panelsTelemetry.addData("Current", dataNectar[1]);
//        panelsTelemetry.addData("Power", dataNectar[2]);

        panelsTelemetry.update();
        telemetry.update();
    }

}
