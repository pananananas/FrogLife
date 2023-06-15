package Entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import static Utilities.Constants.EnemyConstants.*;
import static Utilities.Constants.Directions.*;
import Main.Game;
import static Utilities.HelpMethods.canMoveHere;
import static Utilities.HelpMethods.isSightClear;
public abstract class Enemy extends Entity {

    protected int enemyState, enemyType;
    protected int animationIndex, animationTick, animationSpeed = 25;

    protected float walkSpeed = .3f * Game.ENEMY_SCALE;
    protected int walkDirection = LEFT;
    protected float attackDistance = Game.TILES_SIZE;
    protected float sightDistance = Game.TILES_SIZE * 5;

    protected int maxHealth;
    protected int currentHealth;
    protected boolean acitve = true;
    protected boolean attackChecked = false;

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x, y, width, height);
        maxHealth = getMaxHealth(enemyType);
        currentHealth = maxHealth;
    }

    protected void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= getSpriteAmount(enemyType, enemyState)) {
                animationIndex = 0;

                switch (enemyState) {
                    case ATTACK, HIT -> newState(IDLE);
                    case DEAD        -> acitve = false;
                }
            }
        }
    }

    public void update(int[][] levelData) {
        
        // updateAnimationTick();
        
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

    protected void checkEnemyHit(Rectangle2D.Float attackBox, Player player) {
        if (attackBox.intersects(player.hitbox)) 
            player.changeHealth(-getEnemyDamage(enemyType));
        attackChecked = true;
    }

    protected void newState(int enemyState) {
        this.enemyState = enemyState;
        animationIndex = 0;
        animationTick = 0;
    }

    public void hurt(int damage) {
        currentHealth -= damage;
        if (currentHealth <= 0) {
            currentHealth = 0;
            newState(DEAD);
        } else {
            newState(HIT);
        }
    }

    protected void turnTowardsPlayer(Player player) {
        if (player.hitbox.x < hitbox.x) {
            walkDirection = LEFT;
        } else {
            walkDirection = RIGHT;
        }
    }

    protected boolean canSeePlayer(int[][] levelData, Player player) {

        int playerTileX = (int) player.getHitbox().x / Game.TILES_SIZE;
        int playerTileY = (int) player.getHitbox().y / Game.TILES_SIZE;

        int enemyTileX = (int) hitbox.x / Game.TILES_SIZE;
        int enemyTileY = (int) hitbox.y / Game.TILES_SIZE;
        if (playerTileY == enemyTileY) {
            if (isPlayerInRange(player)) {
                System.out.println("Player in range");
                if (isSightClear(levelData, hitbox, player.hitbox, enemyTileY)) {
                    System.out.println("Sight clear");
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean isPlayerInRange(Player player) {
        float distanceFromPlayerX = Math.abs(player.hitbox.x + player.hitbox.width/2  - (hitbox.x + hitbox.width/2));
        float distanceFromPlayerY = Math.abs(player.hitbox.y + player.hitbox.height/2 - (hitbox.y + hitbox.height/2));
        float distanceFromPlayer = (float) Math.sqrt(Math.pow(distanceFromPlayerX, 2) + Math.pow(distanceFromPlayerY, 2));

        return distanceFromPlayer <= sightDistance;
    }

    protected boolean isPlayerInAttackRange(Player player) {
        float distanceFromPlayerX = Math.abs(player.hitbox.x - hitbox.x);
        float distanceFromPlayerY = Math.abs(player.hitbox.y - hitbox.y);
        float distanceFromPlayer = (float) Math.sqrt(Math.pow(distanceFromPlayerX, 2) + Math.pow(distanceFromPlayerY, 2));

        return distanceFromPlayer <= attackDistance;
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

    public boolean isActive() {
        return acitve;
    }
}
