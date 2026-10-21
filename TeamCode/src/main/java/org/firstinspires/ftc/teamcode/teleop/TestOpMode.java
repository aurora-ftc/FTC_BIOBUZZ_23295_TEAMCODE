package org.firstinspires.ftc.teamcode.teleop;

import com.bylazar.configurables.PanelsConfigurables;
//import com.bylazar.graph.GraphManager;
//import com.bylazar.graph.PanelsGraph;
import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.constants.Constants;


@TeleOp(name="TestOpMode", group="MainOps")
public class TestOpMode extends OpMode {
    private Drive drive;
    private Intake intake;
    private Launcher launcher;
    private boolean intakeOn;
    private double power;
//    private GraphManager graphManager;
    private TelemetryManager panelsTelemetry;
    private ElapsedTime matchTimer = new ElapsedTime();
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
//        graphManager = PanelsGraph.INSTANCE.getManager();
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
        double forward = gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;

        if (gamepad1.crossWasPressed()) intakeOn = !intakeOn;
        if (gamepad1.triangleWasPressed()) launcher.toggle();

        drive.drive(forward, strafe, rotate);

        if (intakeOn) intake.on();
        else intake.off();

        if (gamepad1.dpadDownWasPressed() && power >= 0.01) power -= 0.01;
        else if (gamepad1.dpadUpWasPressed() && power <= 0.99) power += 0.01;
        launcher.setPower(power);

        double[] dataPID = launcher.getGraphData();
        telemetry.addData("Power", power);
        telemetry.addData("RPM", launcher.getRPM());//telemetry.addData("Last Update", graphManager.getTimeSinceLastUpdate());

        panelsTelemetry.debug("Target", dataPID[0]);
        panelsTelemetry.debug("Current", dataPID[1]);
        panelsTelemetry.debug("Power", dataPID[2]);

        panelsTelemetry.addData("Target", dataPID[0]);
        panelsTelemetry.addData("Current", dataPID[1]);
        panelsTelemetry.addData("Power", dataPID[2]);

//        graphManager.addData("Target", dataPID[0]);
//        graphManager.addData("Current", dataPID[1]);
//        graphManager.addData("Power", dataPID[2]);

        panelsTelemetry.addLine("extra1:" + matchTimer.seconds());

        panelsTelemetry.update();
//        graphManager.update();
        telemetry.update();
    }

    @Override
    public void stop() {
        super.stop();
    }

}
