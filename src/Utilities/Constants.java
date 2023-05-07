package Utilities;

public class Constants {
    
    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int HIT = 2;
        public static final int ATTACK = 3;
        public static final int TONGUE_ATTACK = 4;
        public static final int JUMP_ATTACK = 5;    // nie potrzebne
        public static final int FALLING = 6;        // nie potrzebne
        public static final int JUMPING = 7;        // nie potrzebne
        public static final int GROUND = 8;         // nie potrzebne
        public static final int DEAD = 9;           // nie ma w tutorialu ale chce u nas :>

        public static int getSpriteAmount(int playerAction) { 

            // TODO: Gdy zrobiona będzie grafika zmienić zwracaną wartość na odpowiednią
            switch (playerAction) {
                case IDLE -> {
                    return 5;
                }
                case RUNNING -> {
                    return 6;
                }
                case HIT -> {
                    return 4;
                }
                case ATTACK -> {
                    return 3;
                }
                case TONGUE_ATTACK -> {
                    return 3;
                }

                case JUMP_ATTACK -> {       // nie potrzebne
                    return 3;
                }
                case FALLING -> {           // nie potrzebne
                    return 1;
                }
                case JUMPING -> {           // nie potrzebne
                    return 3;
                }
                case GROUND -> {            // nie potrzebne
                    return 2;
                }
                case DEAD -> {              // nie wiadomo ile tu dać
                    return 3;
                }
                default -> {
                    return 1;
                }
            }
        }
    }

}
