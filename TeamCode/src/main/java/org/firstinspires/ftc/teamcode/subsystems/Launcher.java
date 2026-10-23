package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.constants.Constants.HWNames.LAUNCHER_NECTAR;
import static org.firstinspires.ftc.teamcode.constants.Constants.PID.NectarLauncher.*;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Launcher {
    private static final double TICKS_PER_REV = 28.0;
    private double power;
    private int ticksPerRev, rpm;
    private boolean reverse, on;
    private String motorName;
    private MotorEx launcher;

    public Launcher(String motorName, int ticksPerRev, int rpm, boolean reverse) {
        this.motorName = motorName;
        this.ticksPerRev = ticksPerRev;
        this.rpm = rpm;
    }

    public void init(HardwareMap hwMap) {
        launcher = new MotorEx(hwMap, motorName, ticksPerRev, rpm);

        launcher.setRunMode(MotorEx.RunMode.VelocityControl);
        launcher.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.FLOAT);
        launcher.setInverted(reverse);
        launcher.stopAndResetEncoder();
        launcher.set(0);

        launcher.setVeloCoefficients(kP, kI, kD);
        launcher.setFeedforwardCoefficients(kS, kV);
    }

    public void on() {
        on = true;
        launcher.setRunMode(MotorEx.RunMode.VelocityControl);
        launcher.set(power);
    }

    public void off() {
        on = false;
        launcher.setRunMode(MotorEx.RunMode.RawPower);
        launcher.set(0);
    }

    public void toggle() {
        if (on) off();
        else on();
    }

    public void setPower(double power) {
        launcher.setVeloCoefficients(kP, kI, kD);
        launcher.setFeedforwardCoefficients(kS, kV);

        this.power = power;
        if (motorName.equals(LAUNCHER_NECTAR))
            this.power = -power;
        if (on) launcher.set(this.power);
    }

    public int getRPM() {
        return (int) (launcher.getCorrectedVelocity() * 60.0 / TICKS_PER_REV);
    }

    public double getRate() {
        return launcher.getRate();
    }

    public double[] getGraphData() {
        double target = on ? power * 5000 : 0;
        double current = getRPM();
        double power = launcher.getRawPower();
        return new double[] {target, current, power};
    }
}
