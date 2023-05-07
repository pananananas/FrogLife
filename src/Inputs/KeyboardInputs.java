package Inputs;
import Main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static Utilities.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {


    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> gamePanel.setDirection(LEFT);
            case KeyEvent.VK_W -> gamePanel.setDirection(UP);
            case KeyEvent.VK_S -> gamePanel.setDirection(DOWN);
            case KeyEvent.VK_D -> gamePanel.setDirection(RIGHT);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> gamePanel.setMoving(false);
            case KeyEvent.VK_W -> gamePanel.setMoving(false);
            case KeyEvent.VK_S -> gamePanel.setMoving(false);
            case KeyEvent.VK_D -> gamePanel.setMoving(false);
        }
    }
}