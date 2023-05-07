package Main;

import javax.swing.*;

public class GameWindow {
    private JFrame jframe;
    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame("Frog life");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(1280, 720);
        jframe.setLocationRelativeTo(null);     // center window
        jframe.add(gamePanel);
        jframe.setVisible(true);
    }
}
