package org.firstinspires.ftc.teamcode.constants;


import com.bylazar.configurables.annotations.Configurable;

import dev.frozenmilk.sinister.loading.Pinned;

@Configurable
@Pinned
public class Constants {
    public static double MAX_SPEED = 1.0;
    
    public static final class HWNames {
        public static final String FL_MOTOR = "front_left";
        public static final String FR_MOTOR = "front_right";
        public static final String BL_MOTOR = "back_left";
        public static final String BR_MOTOR = "back_right";
        public static final String ODO = "odo";
        public static final String INTAKE = "intake";
        public static final String LAUNCHER = "launcher";
    }

    @Configurable
    public static class PID {
        @Configurable
        public static class Launcher {
            // MotorEx PID works in ticks/second; feedforward outputs a motor power fraction.
            public static double kP = 0.0015;
            public static double kI = 0.0;
            public static double kD = 0.0;
            public static double kS = 0.0;
            public static double kV = 60.0 / (7.0 * 5800.0);
        }
    }
}
