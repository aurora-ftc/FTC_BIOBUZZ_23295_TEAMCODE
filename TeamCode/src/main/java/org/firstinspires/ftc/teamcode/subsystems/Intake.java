package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.constants.Constants.HWNames.INTAKE;
import static org.firstinspires.ftc.teamcode.constants.Constants.HWNames.LIFT_SERVO;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.hardware.motors.CRServo;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class Intake {
    private DcMotorEx intake;
    private CRServo lift;
    private ElapsedTime liftTimer = new ElapsedTime();
    public ElapsedTime blipTimer = new ElapsedTime();
    private boolean liftUp;
    public void init(HardwareMap hwMap) {
        intake = hwMap.get(DcMotorEx.class, INTAKE);
        lift = new CRServo(hwMap, LIFT_SERVO);

        lift.setInverted(false);
        lift.set(0);

        intake.setPower(0.0);
        intake.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        intake.setDirection(DcMotorEx.Direction.REVERSE);
        intake.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        blipTimer.reset();

        liftTimer.reset();
    }

    public void on() {
        intake.setPower(1.0);
    }

    public void off() {
        intake.setPower(0.0);
    }

    public void reverse() {
        intake.setPower(-1);
    }
    public void blip() {
        blipTimer.reset();
    }

    public void up() {
        liftUp = false;
        lift.set(1);
    }

    public void down() {
        liftUp = true;
        lift.set(-1);
    }

    public void stop() {
        lift.set(0);
    }

    public void periodic() {
        if (blipTimer.seconds() <= 0.1)
            reverse();
    }
}
