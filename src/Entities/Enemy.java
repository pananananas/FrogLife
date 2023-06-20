/**
 * @file Enemy.java
 * @brief This file contains the Enemy abstract class which forms the basis for enemy entities in the game.
 */


package Entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import static Utilities.Constants.EnemyConstants.*;
import static Utilities.Constants.Directions.*;
import Main.Game;
import static Utilities.HelpMethods.canMoveHere;
import static Utilities.HelpMethods.isSightClear;

/**
 * @class Enemy
 * @brief This abstract class represents a general Enemy object in the game. It extends the Entity class.
 */
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

        /**
     * @brief Enemy Constructor. Initializes the Enemy object and its hitbox.
     * @param x X-position of the Enemy.
     * @param y Y-position of the Enemy.
     * @param width Width of the Enemy.
     * @param height Height of the Enemy.
     * @param enemyType Type of the Enemy.
     */
    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x, y, width, height);
        maxHealth = getMaxHealth(enemyType);
        currentHealth = maxHealth;
    }


        /**
     * @brief Updates the animation tick and index of the Enemy.
     */
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


      /**
     * @brief Updates the Enemy object's state and behavior.
     * @param levelData Data about the current level.
     */
    public void update(int[][] levelData) {
        
        // updateAnimationTick();
        
    }

        /**
     * @brief Makes the Enemy move in the level.
     * @param levelData Data about the current level.
     */
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

        /**
     * @brief Checks if the Enemy has hit the Player.
     * @param attackBox The hitbox used for attacking.
     * @param player The Player object.
     */
    protected void checkEnemyHit(Rectangle2D.Float attackBox, Player player) {
        if (attackBox.intersects(player.hitbox)) 
            player.changeHealth(-getEnemyDamage(enemyType));
        attackChecked = true;
    }

        /**
     * @brief Changes the state of the Enemy.
     * @param enemyState The new state for the Enemy.
     */
    protected void newState(int enemyState) {
        this.enemyState = enemyState;
        animationIndex = 0;
        animationTick = 0;
    }

            /**
     * @brief Inflicts damage to the Enemy.
     * @param damage Amount of damage to inflict.
     */
    public void hurt(int damage) {
        currentHealth -= damage;
        if (currentHealth <= 0) {
            currentHealth = 0;
            newState(DEAD);
        } else {
            newState(HIT);
        }
    }

        /**
     * @brief Makes the Enemy turn towards the Player.
     * @param player The Player object.
     */
    protected void turnTowardsPlayer(Player player) {
        if (player.hitbox.x < hitbox.x) {
            walkDirection = LEFT;
        } else {
            walkDirection = RIGHT;
        }
    }

       /**
     * @brief Checks if the Enemy can see the Player.
     * @param levelData Data about the current level.
     * @param player The Player object.
     * @return True if the Enemy can see the Player, false otherwise.
     */
    protected boolean canSeePlayer(int[][] levelData, Player player) {

        int playerTileX = (int) player.getHitbox().x / Game.TILES_SIZE;
        int playerTileY = (int) player.getHitbox().y / Game.TILES_SIZE;

        int enemyTileX = (int) hitbox.x / Game.TILES_SIZE;
        int enemyTileY = (int) hitbox.y / Game.TILES_SIZE;
        if (playerTileY == enemyTileY || playerTileY - 1 == enemyTileY || playerTileY + 1 == enemyTileY ) {
            if (isPlayerInRange(player)) {
                System.out.println("Player in range");
                // if (isSightClear(levelData, hitbox, player.hitbox, enemyTileY)) {
                    // System.out.println("Sight clear");
                return true;
                // }
            }
        }
        return false;
    }


        /**
     * @brief Checks if the Player is in the Enemy's range.
     * @param player The Player object.
     * @return True if the Player is in range, false otherwise.
     */
    protected boolean isPlayerInRange(Player player) {
        float distanceFromPlayerX = Math.abs(player.hitbox.x + player.hitbox.width/2  - (hitbox.x + hitbox.width/2));
        float distanceFromPlayerY = Math.abs(player.hitbox.y + player.hitbox.height/2 - (hitbox.y + hitbox.height/2));
        float distanceFromPlayer = (float) Math.sqrt(Math.pow(distanceFromPlayerX, 2) + Math.pow(distanceFromPlayerY, 2));
        System.out.println("Distance from player: " + distanceFromPlayer);
        return distanceFromPlayer <= 500;
    }


        /**
     * @brief Checks if the Player is in the Enemy's attack range.
     * @param player The Player object.
     * @return True if the Player is in attack range, false otherwise.
     */
    protected boolean isPlayerInAttackRange(Player player) {
        float distanceFromPlayerX = Math.abs(player.hitbox.x - hitbox.x);
        float distanceFromPlayerY = Math.abs(player.hitbox.y - hitbox.y);
        float distanceFromPlayer = (float) Math.sqrt(Math.pow(distanceFromPlayerX, 2) + Math.pow(distanceFromPlayerY, 2));

        return distanceFromPlayer <= attackDistance;
    }

        /**
     * @brief Changes the walking direction of the Enemy.
     */
    private void changeWalkDir() {
        if(walkDirection == LEFT) {
            walkDirection = RIGHT;
        } else {
            walkDirection = LEFT;
        }
    }

        /**
     * @brief Renders the Enemy object.
     * @param g Graphics object used to draw the object.
     */
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
