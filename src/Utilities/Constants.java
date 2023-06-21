/**
 * @file Constants.java
 * @brief Contains constant values used in the game.
 */

package Utilities;
import Main.Game;
/**
 * @class Constants
 * @brief Contains constant values used in the game.
 */
public class Constants {
    
        /**
     * @class Directions
     * @brief Contains constant values for directions.
     */
    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

       /**
     * @class PlayerConstants
     * @brief Contains constant values for player actions.
     */
    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;        
        public static final int FALLING = 3;
        public static final int ATTACK = 4;    
        public static final int HIT = 5;
        public static final int DEAD = 6;

             /**
         * @brief Retrieves the number of sprites for the given player action.
         * @param playerAction The player action constant.
         * @return The number of sprites for the player action.
         */
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

      /**
     * @class EnemyConstants
     * @brief Contains constant values for enemy actions and properties.
     */
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

        public static final int CRABBY_DRAWOFFSET_X = (int) (26*Game.ENEMY_SCALE);
        public static final int CRABBY_DRAWOFFSET_Y = (int) (9*Game.ENEMY_SCALE);

           /**
         * @brief Retrieves the number of sprites for the given enemy type and state.
         * @param enemyType The enemy type constant.
         * @param enemyState The enemy state constant.
         * @return The number of sprites for the enemy type and state.
         */
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
        
                /**
         * @brief Retrieves the maximum health for a given enemy type.
         * @param enemyType The enemy type constant.
         * @return The maximum health for the enemy type.
         */
        public static int getMaxHealth(int enemyType) {
            switch (enemyType) {
                case CRABBY -> {
                    return 10;
                }
                default -> {
                    return 1;
                }
            }
        }

              /**
         * @brief Retrieves the enemy damage for a given enemy type.
         * @param enemyType The enemy type constant.
         * @return The enemy damage for the enemy type.
         */
        public static int getEnemyDamage(int enemyType) {
            switch (enemyType) {
                case CRABBY -> {
                    return 15;
                }
                default -> {
                    return 0;
                }
            }
        }

    }

}
