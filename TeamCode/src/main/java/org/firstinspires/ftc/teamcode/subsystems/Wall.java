package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.constants.Constants.HWNames.WALL_SERVO;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class Wall {
    private ServoEx wall;
    private MotorEx shooter;
    private boolean isActivated, isSpinning;
    public ElapsedTime wallTimer = new ElapsedTime();
    public ElapsedTime spinTimer = new ElapsedTime();

    public void init(HardwareMap hwMap) {
        shooter = new MotorEx(hwMap, "shooter");
        shooter.setRunMode(Motor.RunMode.RawPower);
        shooter.set(0);

        wall = new ServoEx(hwMap, WALL_SERVO);
        wall.setCachingTolerance(0.0002);

        wallTimer.reset();
        spinTimer.reset();

        isSpinning = false;

        deactivate();
    }

    public void toggleCycle() {
        if (isSpinning)
            shooter.set(0);
        else
            shooter.set(1);
        isSpinning = !isSpinning;
    }

    public void activate() {
        isActivated = true;
        shooter.set(1);
        wall.set(0.29);
    }
    public void deactivate() {
        isActivated = false;
        shooter.set(0);
        wall.set(0.36);
    }
    public void toggle() {
        activate();
        wallTimer.reset();
    }

    public boolean getData() {
        return isActivated;
    }

    public void periodic() {
        if (isActivated && wallTimer.seconds() > 2)
            deactivate();
    }

}
