package Utilities;

import java.awt.geom.Rectangle2D;

import Main.Game;

public class HelpMethods {
    
    public static boolean canMoveHere(float x, float y, float width, float height, int [][] lvlData) {
        
        if (!isSolid(x, y, lvlData))
            if (!isSolid(x + width, y + height, lvlData))
                if (!isSolid(x + width, y, lvlData))
                    if (!isSolid(x, y + height, lvlData))            
                        return true;
        

        return false;
    }

    private static boolean isSolid(float x, float y, int[][] lvlData) {

        if (x < 0 || x >= Game.GAME_WIDTH)      return true;
        if (y < 0 || y >= Game.GAME_HEIGHT)     return true;
        
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        return isTileSolid((int) xIndex, (int) yIndex, lvlData);
    }

    public static boolean isTileSolid(int tileX, int tileY, int[][] lvlData) {

        int value = lvlData[tileY][tileX];

        if (value >= 48 || value < 0 || value != 11)
            return true;

        return false;
    }

    public static boolean isAllTileClear(int xStart, int xEnd, int y, int[][] lvlData) {
        for (int i = 0; i < xEnd - xStart; i++) 
            if (isSolid(xStart + i, y, lvlData))
                return false;
        return true;
    }

    public static boolean isSightClear(int[][] levelData, Rectangle2D.Float firstHitbox, Rectangle2D.Float secondHitbox, int tileY) {

        int firstTileX = (int) (firstHitbox.x / Game.TILES_SIZE);
        int secondTileX = (int) (secondHitbox.x / Game.TILES_SIZE);

        if(firstTileX > secondTileX)    
            return isAllTileClear(secondTileX, firstTileX,  tileY, levelData);
        else                            
            return isAllTileClear(firstTileX,  secondTileX, tileY, levelData);
    }
}
