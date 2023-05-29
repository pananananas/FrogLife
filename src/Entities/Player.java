package Entities;

import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static Utilities.Constants.PlayerConstants.*;
import static Utilities.Constants.Directions.*;

public class Player extends Entity {
    
    private BufferedImage[][] animations;
    private int spriteWidth = 64, spriteHeight = 40;

    private int animationTick = 0, animationIndex = 0, animationSpeed = 30;
    private int playerAction = IDLE;

    private boolean left, right, up, down, playerMoving = false;

    private float playerSpeed = 2.0f;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update() {
        
        updateAnimationTick();
        setAnimation();
        updatePosition();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animationIndex], (int)x, (int)y, 3 * spriteWidth, 3 * spriteHeight, null);
    }

    private void setAnimation() {
        if (playerMoving)   playerAction = RUNNING;
        else                playerAction = IDLE;
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= getSpriteAmount(playerAction)) {
                animationIndex = 0;
            }
        }
    }

    private void updatePosition() {

        playerMoving = false;

        if (left && !right) {
            x -= playerSpeed;
            playerMoving = true;
        } else if (right && !left) {
            x += playerSpeed;
            playerMoving = true;
        } 

        if (up && !down) {
            y -= playerSpeed;
            playerMoving = true;
        } else if (down && !up){ 
            y += playerSpeed;
            playerMoving = true;
        }
    }
    
    private void loadAnimations() {

        InputStream is = getClass().getResourceAsStream("/Resources/player_sprites.png");
        try {
            BufferedImage image = ImageIO.read(is);

            animations = new BufferedImage[9][6];

            for ( int j = 0; j < animations.length; j++) 
                for (int i = 0; i < animations[j].length; i++) 
                    animations[j][i] = image.getSubimage(i * spriteWidth, j * spriteHeight, spriteWidth, spriteHeight);
                
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
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
