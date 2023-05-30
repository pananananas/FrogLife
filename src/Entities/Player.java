package Entities;

import Utilities.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;

import static Utilities.Constants.PlayerConstants.*;
import static Utilities.HelpMethods.canMoveHere;

public class Player extends Entity {
    
    private BufferedImage[][] animations;
    private int spriteWidth = 64, spriteHeight = 40;

    private int animationTick = 0, animationIndex = 0, animationSpeed = 30;
    private int playerAction = IDLE;

    private boolean left, right, up, down, playerMoving = false, playerAttacking = false;

    private float playerSpeed = 2.0f;

    private int [][] lvlData;

    private float xDrawOffset = 21 * Game.PLAYER_SCALE, yDrawOffset = 5 * Game.PLAYER_SCALE;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y, 21 * Game.PLAYER_SCALE, 28 * Game.PLAYER_SCALE);
    }

    public void update() {
        
        updateAnimationTick();
        setAnimation();
        updatePosition();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animationIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
        drawHitbox(g);
    }

    private void setAnimation() {
    
        int startAnimation = playerAction;

        if (playerMoving)    playerAction = RUNNING;
        else                 playerAction = IDLE;
        if (playerAttacking) playerAction = ATTACK;

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
            }
        }
    }
    
    private void resetAnimationTick() {
        animationTick = 0;
        animationIndex = 0;
    }

    private void updatePosition() {

        playerMoving = false;

        if (!left && !right && !up && !down)    
            return;
        
        float xSpeed = 0;
        float ySpeed = 0;


        if (left && !right)         xSpeed = -playerSpeed;
        else if (right && !left)    xSpeed = playerSpeed;
        if (up && !down)            ySpeed = -playerSpeed;
        else if (down && !up)       ySpeed = playerSpeed;
        
        // if (canMoveHere(x + xSpeed, y + ySpeed, width, height, lvlData)) {
        //     x += xSpeed;
        //     y += ySpeed;
        //     playerMoving = true;
        // }

        if (canMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
            playerMoving = true;
        }

    }
    
    private void loadAnimations() {

        BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[9][6];

        for ( int j = 0; j < animations.length; j++) 
            for (int i = 0; i < animations[j].length; i++) 
                animations[j][i] = image.getSubimage(i * spriteWidth, j * spriteHeight, spriteWidth, spriteHeight);
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
