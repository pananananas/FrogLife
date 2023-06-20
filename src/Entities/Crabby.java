/**
 * @file Crabby.java
 * @brief This file contains Crabby class which is a type of enemy in the game.
 */

package Entities;

import static Utilities.Constants.EnemyConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import static Utilities.Constants.Directions.*;

import Main.Game;


/**
 * @class Crabby
 * @brief This class represents a Crabby enemy object. It extends the Enemy class.
 */
public class Crabby extends Enemy {
    
    // Attack variables
    private Rectangle2D.Float attackBox;
    private int attackBoxOffsetX = (int) (30 * Game.ENEMY_SCALE);
    // private int attackBoxOffsetY = (int) (10 * Game.ENEMY_SCALE);


        /**
     * @brief Crabby Constructor. Initializes Crabby object and its hitbox.
     * @param x X-position of Crabby.
     * @param y Y-position of Crabby.
     */
    public Crabby(float x, float y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
        initHitbox(x, y, (int) (22 * Game.ENEMY_SCALE), (int) (19 * Game.ENEMY_SCALE));
        initAttackBox();
    }

        /**
     * @brief Initializes the attack box.
     */
    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(hitbox.x, hitbox.y, (int) (82 * Game.ENEMY_SCALE), (int) (19 * Game.ENEMY_SCALE));
    }

        /**
     * @brief Updates the Crabby object's state and behavior.
     * @param levelData Data about the current level.
     * @param player Player object.
     */
    public void update(int[][] levelData, Player player) {
        super.update(levelData);
        updateBehavior(levelData, player);
        updateAnimationTick();
        updateAttackBox();
    }

        /**
     * @brief Draws the attack box on the screen.
     * @param g Graphics object used to draw the box.
     */
    public void drawAttackBox(Graphics g) {
        g.setColor(Color.red);
        g.drawRect((int) attackBox.x, (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
    }

        /**
     * @brief Updates the position of the attack box.
     */
    private void updateAttackBox() {
        attackBox.x = hitbox.x - attackBoxOffsetX;
        attackBox.y = hitbox.y;
    }

       /**
     * @brief Updates the Crabby object's behavior based on the current enemy state.
     * @param levelData Data about the current level.
     * @param player Player object.
     */
    private void updateBehavior(int[][] levelData, Player player) {

        switch (enemyState) {
            case IDLE:
                newState(RUNNING);
                break;
        
            case RUNNING:
                move(levelData);
                if(canSeePlayer(levelData, player)) {
                    turnTowardsPlayer(player);
                    walkSpeed = (float) (0.5 * Game.ENEMY_SCALE);
                }
                if (isPlayerInAttackRange(player))
                    newState(ATTACK);
            break;

            case ATTACK:
                if (animationIndex == 0)
                    attackChecked = false;

                if (animationIndex == 3 && !attackChecked)
                    checkEnemyHit(attackBox, player);
                
                break;

            case HIT:
                if (animationIndex == 0 && animationTick == 0) {
                    newState(RUNNING);
                }
                break;

            default:
                break;
        }
    }

        /**
     * @brief Calculates horizontal flipping point.
     * @return Horizontal flipping point based on walking direction.
     */
    public int flipX() {
        if (walkDirection == RIGHT) {
            return width;
        } else {
            return 0;
        }
    }

      /**
     * @brief Calculates width for horizontal flipping.
     * @return Width for horizontal flipping based on walking direction.
     */
    public int flipW() {
        if (walkDirection == RIGHT) {
            return -1;
        } else {
            return 1;
        }
    }


       /**
     * @brief Renders the Crabby object.
     * @param g Graphics object used to draw the object.
     */
    public void render(java.awt.Graphics g) {
        super.render(g);
    }
    
}
