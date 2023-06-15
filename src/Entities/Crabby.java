package Entities;

import static Utilities.Constants.EnemyConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import static Utilities.Constants.Directions.*;

import Main.Game;

public class Crabby extends Enemy {
    
    // Attack variables
    private Rectangle2D.Float attackBox;
    private int attackBoxOffsetX = (int) (30 * Game.ENEMY_SCALE);
    // private int attackBoxOffsetY = (int) (10 * Game.ENEMY_SCALE);


    public Crabby(float x, float y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
        initHitbox(x, y, (int) (22 * Game.ENEMY_SCALE), (int) (19 * Game.ENEMY_SCALE));
        initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(hitbox.x, hitbox.y, (int) (82 * Game.ENEMY_SCALE), (int) (19 * Game.ENEMY_SCALE));
    }

    public void update(int[][] levelData, Player player) {
        super.update(levelData);
        updateBehavior(levelData, player);
        updateAnimationTick();
        updateAttackBox();
    }

    public void drawAttackBox(Graphics g) {
        g.setColor(Color.red);
        g.drawRect((int) attackBox.x, (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
    }

    private void updateAttackBox() {
        attackBox.x = hitbox.x - attackBoxOffsetX;
        attackBox.y = hitbox.y;
    }

    private void updateBehavior(int[][] levelData, Player player) {

        switch (enemyState) {
            case IDLE:
                newState(RUNNING);
                break;
        
            case RUNNING:
                move(levelData);
                if(canSeePlayer(levelData, player))
                    turnTowardsPlayer(player);
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

    public int flipX() {
        if (walkDirection == RIGHT) {
            return width;
        } else {
            return 0;
        }
    }

    public int flipW() {
        if (walkDirection == RIGHT) {
            return -1;
        } else {
            return 1;
        }
    }

    public void render(java.awt.Graphics g) {
        super.render(g);
    }
    
}
