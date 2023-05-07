package Main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private float xDir = 2.0f, yDir = 2.0f;
    private int frames = 0;
    private long lastCheck = System.currentTimeMillis();

    private Color color = new Color(69,102,23);
    private Random random;
    public GamePanel() {
        random = new Random();
        mouseInputs = new MouseInputs(this);
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

        updateRectangle();

        g.setColor(color);
        g.fillRect((int)xDelta, (int)yDelta, 100, 100);

        frames++;
        if (System.currentTimeMillis() - lastCheck >= 1000) {
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS = " + frames);
            frames = 0;
        }

    }
    private void updateRectangle() {
        xDelta += xDir;
        if (xDelta >= 1280 - 100 || xDelta <= 0) {
            xDir *= -1;
            color = getRandomColor();
        }

        yDelta += yDir;
        if (yDelta >= 720 - 100 || yDelta <= 0) {
            yDir *= -1;
            color = getRandomColor();
        }
    }
    private Color getRandomColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        return new Color(r, g, b);
    }
}


// Life is messed up, so we need to find the way. Froggy way. Frog life <3