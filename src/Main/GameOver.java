package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class GameOver {
    
    private Game game;

    public GameOver(Game game) {
        this.game = game;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        g.setColor(Color.white);
        g.drawString("Game Over",            Game.GAME_WIDTH / 2, Game.GAME_HEIGHT / 2);
        g.drawString("Press ESC to restart", Game.GAME_WIDTH / 2, Game.GAME_HEIGHT / 2 + 50);

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            game.restartAll();
        }
    }
}
