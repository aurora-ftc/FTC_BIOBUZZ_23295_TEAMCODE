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
        public static final String LAUNCHER_POLLEN = "launcher_pollen";
        public static final String LAUNCHER_NECTAR = "launcher_nectar";

    }

    @Configurable
    public static class PID {
        @Configurable
        public static class NectarLauncher {
            public static double kP = 1.3;
            public static double kI = 0.0;
            public static double kD = 0.2;
            public static double kS = 0.08;
            public static double kV = 1.23;
        }
        @Configurable
        public static class PollenLauncher {
            public static double kP = 0.0;
            public static double kI = 0.0;
            public static double kD = 0.0;
            public static double kS = 0.0;
            public static double kV = 20;
        }
    }
}
