package Main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Utilities.Constants.PlayerConstants.*;
import static Utilities.Constants.Directions.*;

public class GamePanel extends JPanel{

    private int windowWidth = 1280, windowHeight = 720;
    private int spriteWidth = 64, spriteHeight = 40;

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage image;
    private BufferedImage[][] animations;
    private int animationTick = 0, animationIndex = 0, animationSpeed = 15;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean playerMoving = false;



    public GamePanel() {
        mouseInputs = new MouseInputs(this);

        importImage();
        loadAnimations();

        setPanelSize(windowWidth, windowHeight);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void setDirection(int direction) {
        this.playerDirection = direction;
        playerMoving = true;
    }
    
    public void setMoving(boolean moving) {
        this.playerMoving = moving;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);    // paint background, clean frame
        updateAnimationTick();

        setAnimation();
        updatePosition();

        g.drawImage(animations[playerAction][animationIndex], (int)xDelta, (int)yDelta, 3 * spriteWidth, 3 * spriteHeight, null);
    }

    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/Resources/player_sprites.png");
        try {
            image = ImageIO.read(is);
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

    private void loadAnimations() {
        animations = new BufferedImage[9][6];

        for ( int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = image.getSubimage(i * spriteWidth, j * spriteHeight, spriteWidth, spriteHeight);
            }
        }
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
        if (playerMoving)
            switch (playerDirection) {
                case LEFT -> xDelta -= 3;
                case UP -> yDelta -= 3;
                case DOWN -> yDelta += 3;
                case RIGHT -> xDelta += 3;
            }
    }

    private void setPanelSize(int wid, int hei) {
        Dimension size = new Dimension(wid, hei);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
}


// Life is messed up, so we need to find the way. Froggy way. Frog life <3