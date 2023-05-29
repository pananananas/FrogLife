package Main;

import java.awt.event.WindowFocusListener;

import javax.swing.*;

public class GameWindow {
    private JFrame jframe;
    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame("Frog life");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setLocationRelativeTo(null);     // center window
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent e) {

            }

            @Override
            public void windowLostFocus(java.awt.event.WindowEvent e) {
                gamePanel.getGame().getPlayer().setLeft(false);
                gamePanel.getGame().getPlayer().setRight(false);
                gamePanel.getGame().getPlayer().setUp(false);
                gamePanel.getGame().getPlayer().setDown(false);
            }
        });
    }
}
