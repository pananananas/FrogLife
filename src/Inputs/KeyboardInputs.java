/**
 * @file KeyboardInputs.java
 * @brief Handles keyboard inputs for the game.
 */
package Inputs;
import Main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.w3c.dom.events.MouseEvent;

/**
 * @class KeyboardInputs
 * @brief Implements the KeyListener interface to handle keyboard inputs.
 */
public class KeyboardInputs implements KeyListener {


        /**
     * @brief Constructs a KeyboardInputs object with the specified GamePanel.
     * @param gamePanel The GamePanel associated with the keyboard inputs.
     */
    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

       /**
     * @brief Unused method from the KeyListener interface.
     * @param e The KeyEvent object.
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }
    
       /**
     * @brief Handles the key press events.
     * @param e The KeyEvent object.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> gamePanel.getGame().getPlayer().setLeft(true);
            case KeyEvent.VK_W -> gamePanel.getGame().getPlayer().setUp(true);
            case KeyEvent.VK_S -> gamePanel.getGame().getPlayer().setDown(true);
            case KeyEvent.VK_D -> gamePanel.getGame().getPlayer().setRight(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) 
            gamePanel.getGame().getPlayer().setAttack(true);    
    }

        /**
     * @brief Handles the key release events.
     * @param e The KeyEvent object.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> gamePanel.getGame().getPlayer().setLeft(false);
            case KeyEvent.VK_W -> gamePanel.getGame().getPlayer().setUp(false);
            case KeyEvent.VK_S -> gamePanel.getGame().getPlayer().setDown(false);
            case KeyEvent.VK_D -> gamePanel.getGame().getPlayer().setRight(false);
        }
    }
}