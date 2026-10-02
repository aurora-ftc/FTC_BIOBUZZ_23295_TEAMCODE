package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.constants.Constants.HWNames.LAUNCHER;
import static org.firstinspires.ftc.teamcode.constants.Constants.PID.Launcher.*;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Launcher {
    private double power;
    private boolean on;
    MotorEx launcher;
    public void init(HardwareMap hwMap) {
        launcher = new MotorEx(hwMap, LAUNCHER, 28, 5800);

        launcher.setRunMode(MotorEx.RunMode.VelocityControl);
        launcher.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.FLOAT);
        launcher.setInverted(true);
        launcher.stopAndResetEncoder();
        launcher.set(0);

        launcher.setVeloCoefficients(kP, kI, kD);
        launcher.setFeedforwardCoefficients(kS, kV);
    }

    public void on() {
        on = true;
        launcher.set(power);
    }

    public void off() {
        on = false;
        launcher.set(0);
    }

    public void toggle() {
        if (on) off();
        else on();
    }

    public void setPower(double power) {
        this.power = power;
        if (on) launcher.set(this.power);
    }

    public int getRPM() {
        return (int) (launcher.getCorrectedVelocity() * 60.0 / 28.0);
    }

    public double getRate() {
        return launcher.getRate();
    }

    public double[] getGraphData() {
        double target = on ? power * 5800 : 0;
        double current = getRPM();
        double power = launcher.getRawPower();
        return new double[] {target, current, power};
    }
}
