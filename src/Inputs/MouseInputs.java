/**
 * @file MouseInputs.java
 * @brief Handles mouse inputs for the game.
 *
 */

package Inputs;
import Main.GamePanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @class MouseInputs
 * @brief Implements the MouseListener and MouseMotionListener interfaces to handle mouse inputs.
 * This class is responsible for handling mouse inputs for the game.
 */
public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;
    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            gamePanel.getGame().getPlayer().setAttack(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
