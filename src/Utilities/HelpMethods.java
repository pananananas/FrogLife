/**
 * @file HelpMethods.java
 * @brief Provides utility methods for game logic.
 */

package Utilities;

import java.awt.geom.Rectangle2D;

import Main.Game;

/**
 * @class HelpMethods
 * @brief Provides utility methods for game logic.
 */
public class HelpMethods {
    
       /**
     * @brief Checks if a game object can move to the specified position.
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     * @param width The width of the game object.
     * @param height The height of the game object.
     * @param lvlData The level data array.
     * @return True if the game object can move to the position, false otherwise.
     */
    public static boolean canMoveHere(float x, float y, float width, float height, int [][] lvlData) {
        
        if (!isSolid(x, y, lvlData))
            if (!isSolid(x + width, y + height, lvlData))
                if (!isSolid(x + width, y, lvlData))
                    if (!isSolid(x, y + height, lvlData))            
                        return true;
        

        return false;
    }

      /**
     * @brief Checks if a specific position is solid (collision) in the level.
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     * @param lvlData The level data array.
     * @return True if the position is solid, false otherwise.
     */
    private static boolean isSolid(float x, float y, int[][] lvlData) {

        if (x < 0 || x >= Game.GAME_WIDTH)      return true;
        if (y < 0 || y >= Game.GAME_HEIGHT)     return true;
        
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        return isTileSolid((int) xIndex, (int) yIndex, lvlData);
    }

        /**
     * @brief Checks if a specific tile is solid (collision) in the level.
     * @param tileX The x-coordinate of the tile.
     * @param tileY The y-coordinate of the tile.
     * @param lvlData The level data array.
     * @return True if the tile is solid, false otherwise.
     */
    public static boolean isTileSolid(int tileX, int tileY, int[][] lvlData) {

        int value = lvlData[tileY][tileX];

        if (value >= 48 || value < 0 || value != 11)
            return true;

        return false;
    }

        /**
     * @brief Checks if all tiles within a specified range are clear (not solid) in the level.
     * @param xStart The starting x-coordinate of the range.
     * @param xEnd The ending x-coordinate of the range.
     * @param y The y-coordinate of the tiles.
     * @param lvlData The level data array.
     * @return True if all tiles within the range are clear, false otherwise.
     */
    public static boolean isAllTileClear(int xStart, int xEnd, int y, int[][] lvlData) {
        for (int i = 0; i < xEnd - xStart; i++) 
            if (isSolid(xStart + i, y, lvlData))
                return false;
        return true;
    }

        /**
     * @brief Checks if there is a clear sight between two hitboxes on a specific tile row in the level.
     * @param levelData The level data array.
     * @param firstHitbox The first hitbox.
     * @param secondHitbox The second hitbox.
     * @param tileY The y-coordinate of the tile row.
     * @return True if there is a clear sight between the hitboxes, false otherwise.
     */
    public static boolean isSightClear(int[][] levelData, Rectangle2D.Float firstHitbox, Rectangle2D.Float secondHitbox, int tileY) {

        int firstTileX = (int) (firstHitbox.x / Game.TILES_SIZE);
        int secondTileX = (int) (secondHitbox.x / Game.TILES_SIZE);

        if(firstTileX > secondTileX)    
            return isAllTileClear(secondTileX, firstTileX,  tileY, levelData);
        else                            
            return isAllTileClear(firstTileX,  secondTileX, tileY, levelData);
    }
}
