package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;
import Utilities.LoadSave;

public class LevelManager {
    
    private Game game;
    private BufferedImage levelSprite[];

    public LevelManager(Game game) {
        this.game = game;

        importOutsideSprites();
    }

    private void importOutsideSprites() {

        BufferedImage tmpImage = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {

                int index = j * 12 + i;

                levelSprite[index] = tmpImage.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics g) {
        g.drawImage(levelSprite[2], 0, 0, null);
    }
    public void update() {
        
    }
}
