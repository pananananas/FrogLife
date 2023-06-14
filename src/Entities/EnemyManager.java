package Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Main.Game;
import Utilities.LoadSave;
import static Utilities.Constants.EnemyConstants.*;

public class EnemyManager {
    
    private Game game;
    private BufferedImage[][] crabbyArray;
    private ArrayList<Crabby> crabbies = new ArrayList<>();

    public EnemyManager(Game game) {
        this.game = game;
        loadEnemyImages();
        addEnemies();
    }

    private void addEnemies() {
        crabbies = LoadSave.getCrabs();
        System.out.println("Crabbs: "+crabbies.size());
    }

    public void update(int[][] levelData) {
        for (Crabby c : crabbies) {
            c.update(levelData);
        }
    }

    public void draw(Graphics g) {
        drawCrabbies(g);
    }
    

    private void drawCrabbies(Graphics g) {
        for (Crabby c : crabbies) {
            g.drawImage(crabbyArray[c.getEnemyState()][c.getAnimationIndex()], (int)c.getHitbox().x, (int)c.getHitbox().y, CRABBY_WIDTH, CRABBY_HEIGHT, null);
        }
    }

    private void loadEnemyImages() {
        crabbyArray = new BufferedImage[5][9];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.CRAB_ATLAS);
        for (int i = 0; i < crabbyArray.length; i++) {
            for (int j = 0; j < crabbyArray[0].length; j++) {
                crabbyArray[i][j] = temp.getSubimage(j * CRABBY_WIDTH_DEFAULT, i * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
            }
        }
    }
    
}
