package Main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import javax.swing.*;
import java.awt.*;

import static Main.Game.GAME_HEIGHT;
import static Main.Game.GAME_WIDTH;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game) {
        this.game = game;
        mouseInputs = new MouseInputs(this);

        setPanelSize();
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

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        System.out.println("GamePanel size set to: " + size);
    }
    public Game getGame() {
        return game;
    }
}


// Life is messed up, so we need to find the way. Froggy way. Frog life <3