package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.constants.Constants.HWNames.WALL_SERVO;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class Wall {
    private ServoEx wall;
    private boolean isActivated;
    public ElapsedTime wallTimer = new ElapsedTime();
    public void init(HardwareMap hwMap) {
        wall = new ServoEx(hwMap, WALL_SERVO);
        wall.setCachingTolerance(0.0002);
        wallTimer.reset();

        deactivate();
    }
    public void activate() {
        isActivated = true;
        wall.set(0.79);
    }
    public void deactivate() {
        isActivated = false;
        wall.set(0.19);
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
