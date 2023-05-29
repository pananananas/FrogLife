package Main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel{

    private int windowWidth = 1280, windowHeight = 720;
    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game) {
        this.game = game;
        mouseInputs = new MouseInputs(this);

        setPanelSize(windowWidth, windowHeight);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    

    public void updateGame() {                  // update game logic

    }

    public void paintComponent(Graphics g) {    // draw graphics
        super.paintComponent(g);    // paint background, clean frame
        game.render(g);
    }

    private void setPanelSize(int width, int heigth) {
        Dimension size = new Dimension(width, heigth);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
    public Game getGame() {
        return game;
    }
}


// Life is messed up, so we need to find the way. Froggy way. Frog life <3