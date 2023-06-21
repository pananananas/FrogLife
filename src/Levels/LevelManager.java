/**
 * @file LevelManager.java
 * @brief Manages game levels and their rendering.
 */
package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;
import Utilities.LoadSave;
import static Main.Game.TILES_SIZE;

/**
 * @class LevelManager
 * @brief Manages game levels and their rendering.
 */
public class LevelManager {
    
    private Game game;
    private BufferedImage levelSprite[];
    private Level levelOne;
    BufferedImage bgImage;

        /**
     * @brief Constructs a LevelManager object with the specified Game.
     * @param game The Game associated with the level manager.
     */
    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites();

        levelOne = new Level(LoadSave.getLevelData());
    }

        /**
     * @brief Imports external sprites for the level.
     */
    private void importOutsideSprites() {

        BufferedImage tmpImage = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        bgImage = LoadSave.getSpriteAtlas(LoadSave.BG_IMAGE);
        levelSprite = new BufferedImage[48];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {

                int index = j * 12 + i;

                levelSprite[index] = tmpImage.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

       /**
     * @brief Draws the level and background image.
     * @param g The Graphics object used for rendering.
     */
    public void draw(Graphics g) {
        g.drawImage(bgImage, 0, 0, Game.GAME_HEIGHT, Game.GAME_WIDTH, null);
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) 
            for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
                int spriteIndex = levelOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[spriteIndex], i * TILES_SIZE, j * TILES_SIZE, TILES_SIZE, TILES_SIZE, null);
            }
    }

        /**
     * @brief Updates the level.
     */
    public void update() {
        
    }

      /**
     * @brief Gets the current level data.
     * @return The current level data.
     */
    public Level getCurrentData() {
        return levelOne;
    }
}
 