/**
 * @file Level.java
 * @brief This file contains the Level class, which represents a level in the game.
 * 
 */

package Levels;

/**
 * @class Level
 * @brief This class represents a level in the game.
 * This class is responsible for storing the level data.
 */
public class Level {


    private int [][] levelData;

    public Level(int [][] levelData) {
        this.levelData = levelData;
    }

    public int getSpriteIndex(int x, int y) {
        return levelData[y][x];
    }
    public int [][] getLevelData() {
        return levelData;
    }
}
