package Entities;

import java.awt.Graphics;

import static Utilities.Constants.EnemyConstants.*;

public abstract class Enemy extends Entity {

    private int enemyState, enemyType;
    private int animationIndex, animationTick, animationSpeed = 25;

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

    public void update() {
        updateAnimationTick();
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
