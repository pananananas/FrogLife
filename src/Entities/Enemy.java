package Entities;

import java.awt.Graphics;

import static Utilities.Constants.EnemyConstants.*;
import static Utilities.Constants.Directions.*;
import Main.Game;
import static Utilities.HelpMethods.canMoveHere;
public abstract class Enemy extends Entity {

    protected int enemyState, enemyType;
    protected int animationIndex, animationTick, animationSpeed = 25;

    protected float walkSpeed = .5f * Game.ENEMY_SCALE;
    protected int walkDirection = LEFT;

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x, y, width, height);
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= getSpriteAmount(enemyType, enemyState)) {
                animationIndex = 0;
            }
        }
    }

    public void update(int[][] levelData) {
        
        updateAnimationTick();
        
    }

    protected void move(int[][] levelData) {
        float xSpeed = 0;
                
        if(walkDirection == LEFT) {
            xSpeed -= walkSpeed;
            if (canMoveHere(hitbox.x - xSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
                hitbox.x += xSpeed;
                return;
            } 
        } else {
            xSpeed += walkSpeed;
            if (canMoveHere(hitbox.x + width + xSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
                hitbox.x += xSpeed;
                return;
            } 
        }
        changeWalkDir();
    }

    protected void changeState(int enemyState) {
        this.enemyState = enemyState;
        animationIndex = 0;
        animationTick = 0;
    }



    private void changeWalkDir() {
        if(walkDirection == LEFT) {
            walkDirection = RIGHT;
        } else {
            walkDirection = LEFT;
        }
    }

    public void render(Graphics g) {

    }
    
    public int getAnimationIndex() {
        return animationIndex;
    }
    
    public int getEnemyType() {
        return enemyType;
    }
    
    public int getEnemyState() {
        return enemyState;
    }
}
