package util;

public class Constants {

    public enum posEnum {
        RUNNING, IDLE1, NO, JUMPING, FALLING, DEAD, EATING;
    }

    public static int getAniNums(posEnum c) {
        switch (c) {
            case RUNNING:
                return 4;
            case IDLE1:
                return 6;
            case NO:
                return 6;
            case JUMPING:
                return 8;
            case DEAD:
                return 2;
            case FALLING:
                return 2;
            case EATING:
                return 2;
        }
        return 1;

    }

    public static int getAniLocation(posEnum c) {
        switch (c) {
            case IDLE1:
                return 0;
            case NO:
                return 1;
            case JUMPING:
                return 2;
            case RUNNING:
                return 3;
            case EATING:
                return 4;
            case FALLING:
                return 5;
            case DEAD:
                return 6;
        }
        return 1;
    }

    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
}
