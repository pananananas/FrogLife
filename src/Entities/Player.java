/**
 * @file Player.java
 * @brief This file contains the Player class, which represents the main character in the game.
 */


package Entities;

import Utilities.LoadSave;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import Main.Game;

import static Utilities.Constants.PlayerConstants.*;
import static Utilities.HelpMethods.canMoveHere;


/**
 * @class Player
 * @brief This class represents the player in the game.
 * This class is responsible for player movement, attack, and UI display.
 */
public class Player extends Entity {
    
    private int [][] lvlData;
    private Game game;

    
    // Player sprites and animations

    private BufferedImage[][] animations;
    private int spriteWidth = 64, spriteHeight = 40;

    private int playerAction = IDLE;
    private int animationTick = 0, animationIndex = 0, animationSpeed = 30;

    private float xDrawOffset = 21 * Game.PLAYER_SCALE, yDrawOffset = 5 * Game.PLAYER_SCALE;
    
    
    // Player movement

    private float playerSpeed = 1.3f;
    private boolean left, right, up, down, playerMoving = false, playerAttacking = false;


    // Status bar UI and hp variables
    
    private BufferedImage statusBarImage;

    private int statusBarWidth  = (int) (192 * Game.SCALE);
    private int statusBarHeight = (int) (58  * Game.SCALE);
    private int statusBarX      = (int) (10  * Game.SCALE);
    private int statusBarY      = (int) (10  * Game.SCALE);
    private int healthBarWidth  = (int) (150 * Game.SCALE);
    private int healthBarHeight = (int) ( 4  * Game.SCALE);
    private int healthBarX      = (int) (34  * Game.SCALE);
    private int healthBarY      = (int) (14  * Game.SCALE);

    private int maxHealth = 100;
    private int currentHealth = maxHealth;
    private int healthWidth = healthBarWidth;


    // Attack variables

    private Rectangle2D.Float attackBox;
    private boolean attackChecked = false;

    private int flipX = 0;
    private int flipW = 1;

    
        /**
     * @brief Player constructor.
     * @param x The x-coordinate for the player.
     * @param y The y-coordinate for the player.
     * @param width The width of the player.
     * @param height The height of the player.
     * @param game A reference to the Game object.
     */
    public Player(float x, float y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        loadAnimations();
        initHitbox(x, y, 21 * Game.PLAYER_SCALE, 28 * Game.PLAYER_SCALE);
        initAttackBox();
    }

        /**
     * @brief Updates the state of the player.
     * This includes health, attack box position, animation, position, and checking attack.
     */
    public void update() {
        if (currentHealth <= 0) {
            game.setGameOver(true);
            return;
        }
        updateHealthBar();
        updateAttackBox();
        updateAnimationTick();
        setAnimation();
        updatePosition();
        if (playerAttacking)
            checkAttack();
    }

    private void checkAttack() {
        if (attackChecked || animationIndex != 1)
            return;
        attackChecked = true;
        game.checkEnemyHit(attackBox);
    }

    private void updateAttackBox() {
        if (right) {
            attackBox.x = hitbox.x + hitbox.width + (int) (10 * Game.PLAYER_SCALE);

        } else if (left) {
            attackBox.x = hitbox.x - hitbox.width - (int) (10 * Game.PLAYER_SCALE);
        }
        attackBox.y = hitbox.y + (int) (10 * Game.PLAYER_SCALE);
    }

       /**
     * @brief Renders the player on the screen.
     * @param g Graphics object used to draw the player.
     */
    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animationIndex], 
                    (int) (hitbox.x - xDrawOffset) + flipX, 
                    (int) (hitbox.y - yDrawOffset), 
                    width * flipW, 
                    height, 
                    null);

        // drawHitbox(g);
        drawUI(g);
        // drawAttackBox(g);
    }

    // private void drawAttackBox(Graphics g) {
    //     g.setColor(Color.red);
    //     g.drawRect((int) attackBox.x, (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
    // }

    private void drawUI(Graphics g) {
        g.drawImage(statusBarImage, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
        g.setColor(Color.red);
        g.fillRect(healthBarX + statusBarX, healthBarY + statusBarY, healthWidth, healthBarHeight);
    }

    private void updateHealthBar() {
        healthWidth = (int) (healthBarWidth * ((float) currentHealth / maxHealth));
    }

    private void setAnimation() {
    
        int startAnimation = playerAction;

        if (playerMoving)    playerAction = RUNNING;
        else                 playerAction = IDLE;
        if (playerAttacking) { 
            playerAction = ATTACK;
            if (startAnimation != ATTACK) {
                animationIndex = 1;
                animationTick = 0;
                return;
            }
        }

        if (startAnimation != playerAction) {
            resetAnimationTick();
        }
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= getSpriteAmount(playerAction)) {
                animationIndex = 0;
                playerAttacking = false;
                attackChecked = false;
            }
        }
    }
    
    private void resetAnimationTick() {
        animationTick = 0;
        animationIndex = 0;
    }

    private void updatePosition() {

        if (!left && !right && !up && !down) {
            playerMoving = false;
            return;
        }
        float xSpeed = 0;
        float ySpeed = 0;
    
        if (left && !right) {        
            xSpeed = -playerSpeed; 
            flipX = width;
            flipW = -1;
        }
        else if (right && !left) {    
            xSpeed = playerSpeed;
            flipX = 0;
            flipW = 1;
        }
        if (up && !down)            
            ySpeed = -playerSpeed;
        else if (down && !up)       
            ySpeed = playerSpeed;
        
        if (xSpeed != 0 && ySpeed != 0) {
            xSpeed /= 1.414f;
            ySpeed /= 1.414f;
        }
    
        if (canMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
            playerMoving = true;
        }
        if (canMoveHere(hitbox.x, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData)) {
            hitbox.y += ySpeed;
            playerMoving = true;
        }
    }

       /**
     * @brief Changes the health of the player.
     * @param value The amount to change the health by (can be negative to decrease health).
     */
    public void changeHealth(int value) {

        currentHealth += value;

        if (currentHealth > maxHealth) 
            currentHealth = maxHealth;
        
        if (currentHealth <= 0) {
            currentHealth = 0;
            // gameOver();
        }
    }

    protected void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int) (20 * Game.PLAYER_SCALE), (int) (20 * Game.PLAYER_SCALE));
    }
    
    private void loadAnimations() {

        BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[7][8];

        for ( int j = 0; j < animations.length; j++) 
            for (int i = 0; i < animations[j].length; i++) 
                animations[j][i] = image.getSubimage(i * spriteWidth, j * spriteHeight, spriteWidth, spriteHeight);

        statusBarImage = LoadSave.getSpriteAtlas(LoadSave.STATUS_BAR);
    }

    public void loadLevelData(int[][] lvlData) {
        this.lvlData = lvlData;
    }

    public void setAttack(boolean attacking) {
        this.playerAttacking = attacking;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

}
