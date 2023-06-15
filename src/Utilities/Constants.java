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
        public static final int JUMP = 2;        
        public static final int FALLING = 3;
        public static final int ATTACK = 4;    
        public static final int HIT = 5;
        public static final int DEAD = 6;

        public static int getSpriteAmount(int playerAction) { 

            switch (playerAction) {
                case DEAD -> {
                    return 8;
                }
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
                case JUMP -> {
                    return 3;
                }
                case FALLING -> {
                    return 1;
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

        public static final int CRABBY_DRAWOFFSER_X = (int) (26*Game.ENEMY_SCALE);
        public static final int CRABBY_DRAWOFFSER_Y = (int) (9*Game.ENEMY_SCALE);

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
