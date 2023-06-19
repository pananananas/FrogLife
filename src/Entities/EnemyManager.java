/**
 * @file EnemyManager.java
 * @brief This file contains the EnemyManager class which manages all enemy objects in the game.
 */

package Entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Main.Game;
import Utilities.LoadSave;
import static Utilities.Constants.EnemyConstants.*;


/**
 * @class EnemyManager
 * @brief This class manages all Enemy objects in the game.
 */
public class EnemyManager {
    
    private Game game;
    private BufferedImage[][] crabbyArray;
    private ArrayList<Crabby> crabbies = new ArrayList<>();


        /**
     * @brief EnemyManager Constructor. Initializes the EnemyManager object.
     * @param game Instance of the Game class.
     */
    public EnemyManager(Game game) {
        this.game = game;
        loadEnemyImages();
        addEnemies();
    }


    
    /**
     * @brief Adds enemies to the game.
     */
    private void addEnemies() {
        crabbies = LoadSave.getCrabs();
        System.out.println("Crabbs: "+crabbies.size());
    }


       /**
     * @brief Updates all enemies in the game.
     * @param levelData Data about the current level.
     * @param player The Player object.
     */
    public void update(int[][] levelData, Player player) {
        for (Crabby c : crabbies) {
            if (c.isActive())
                c.update(levelData, player);
        }
    }


        /**
     * @brief Draws all enemies in the game.
     * @param g Graphics object used to draw the object.
     */
    public void draw(Graphics g) {
        drawCrabbies(g);
        for (Crabby c : crabbies) 
            if (c.isActive()) {
                // c.drawAttackBox(g);
                // c.drawHitbox(g);
            }
    }
    

        /**
     * @brief Draws all Crabby enemies in the game.
     * @param g Graphics object used to draw the object.
     */
    private void drawCrabbies(Graphics g) {
        for (Crabby c : crabbies) {
            if (c.isActive())
                g.drawImage(crabbyArray[c.getEnemyState()][c.getAnimationIndex()], 
                            (int)c.getHitbox().x - CRABBY_DRAWOFFSET_X + c.flipX(), 
                            (int)c.getHitbox().y - CRABBY_DRAWOFFSET_Y, 
                            CRABBY_WIDTH * c.flipW(), 
                            CRABBY_HEIGHT, 
                            null);
        }
    }

      /**
     * @brief Checks if any enemy has been hit.
     * @param attackBox The hitbox used for attacking.
     */

    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        for (Crabby c : crabbies) {
            if (c.isActive())
                if (attackBox.intersects(c.getHitbox())) {
                    c.hurt(10);
                    return;
                }
        }
    }


      /**
     * @brief Loads all enemy images.
     */
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
