package Main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel{

    private int windowWidth = 1280, windowHeight = 720;

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage image, subImage;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);

        importImage();

        setPanelSize(windowWidth, windowHeight);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXDelta(int value) {
        xDelta += value;
    }

    public void changeYDelta(int value) {
        yDelta += value;
    }

    public void setRectPos(int x, int y) {
        xDelta = x;
        yDelta = y;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);    // paint background, clean frame

        subImage = image.getSubimage(1 * 64, 8 * 40, 64, 40);

        g.drawImage(subImage, (int)xDelta, (int)yDelta, 128, 80, null);
        
    }

    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/Resources/player_sprites.png");
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
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