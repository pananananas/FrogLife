package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;
import Utilities.LoadSave;
import static Main.Game.TILES_SIZE;

public class LevelManager {
    
    private Game game;
    private BufferedImage levelSprite[];
    private Level levelOne;

    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites();

        levelOne = new Level(LoadSave.getLevelData());
    }

    private void importOutsideSprites() {

        BufferedImage tmpImage = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {

                int index = j * 12 + i;

                levelSprite[index] = tmpImage.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics g) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) 
            for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
                int spriteIndex = levelOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[spriteIndex], i * TILES_SIZE, j * TILES_SIZE, TILES_SIZE, TILES_SIZE, null);
            }
    }

    public void update() {
        
    }
}
 