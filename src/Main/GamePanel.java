/**
 * @file GamePanel.java
 * @brief Represents the game panel where the game is rendered and inputs are handled.
 */

package Main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import javax.swing.*;
import java.awt.*;

import static Main.Game.GAME_HEIGHT;
import static Main.Game.GAME_WIDTH;

/**
 * @class GamePanel
 * @brief Represents the game panel where the game is rendered and inputs are handled.
 * Extends the JPanel class.
 */
public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private Game game;

      /**
     * @brief Constructs a GamePanel object with the specified Game.
     * @param game The Game associated with the game panel.
     */
    public GamePanel(Game game) {
        this.game = game;
        mouseInputs = new MouseInputs(this);

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

       /**
     * @brief Updates the game logic.
     */
    public void updateGame() {                  // update game logic

    }

       /**
     * @brief Draws the graphics on the panel.
     * @param g The Graphics object used for rendering.
     */
    public void paintComponent(Graphics g) {    // draw graphics
        super.paintComponent(g);    // paint background, clean frame
        game.render(g);
    }

       /**
     * @brief Sets the size of the panel.
     */
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        System.out.println("GamePanel size set to: " + size);
    }

      /**
     * @brief Retrieves the game object.
     * @return The game object.
     */
    public Game getGame() {
        return game;
    }
}


// Life is messed up, so we need to find the way. Froggy way. Frog life <3