package Utilities;
import Main.Game;
public class Constants {
    
    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int HIT = 2;
        public static final int ATTACK = 7;
        public static final int TONGUE_ATTACK = 4;
        public static final int JUMP_ATTACK = 5;    // nie potrzebne
        public static final int FALLING = 6;        // nie potrzebne
        public static final int JUMPING = 3;        // nie potrzebne
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

    public static class EnemyConstants {

        public static final int CRABBY = 0;

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;
        public static final int HIT = 3;
        public static final int DEAD = 4;
        

        public static final int CRABBY_WIDTH_DEFAULT = 72;
        public static final int CRABBY_HEIGHT_DEFAULT = 32;

        public static final int CRABBY_WIDTH = (int)(CRABBY_WIDTH_DEFAULT * Game.ENEMY_SCALE);
        public static final int CRABBY_HEIGHT = (int)(CRABBY_HEIGHT_DEFAULT * Game.ENEMY_SCALE);

        public static int getSpriteAmount(int enemyType, int enemyState) {
            switch (enemyType) {
                case CRABBY -> {
                    switch (enemyState) {
                        case IDLE -> {
                            return 9;
                        }
                        case RUNNING -> {
                            return 6;
                        }
                        case ATTACK -> {
                            return 7;
                        }
                        case HIT -> {
                            return 4;
                        }
                        case DEAD -> {
                            return 5;
                        }
                        default -> {
                            return 0;
                        }
                    }
                }
                default -> {
                    return 0;
                }
            }

        }

    }

}
