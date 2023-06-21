/**
 * @file GameOver.java
 * @brief Manages game over screen and its rendering.
 * Implements the Runnable interface for the game loop.
 */
package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * @class GameOver
 * @brief Manages game over screen and its rendering.
 * Implements the Runnable interface for the game loop.
 */
public class GameOver {
    
    private Game game;

    public GameOver(Game game) {
        this.game = game;
    }

    /**
     * @brief Draws the game over screen.
     * @param g The Graphics object used for rendering.
     * @param gameOver True if the game is over.
     * @param game The Game object associated with the game over screen.
     * @param gameLoopThread The thread used for the game loop.
     * @param gamePanel The GamePanel object associated with the game over screen.
     * @param gameWindow The GameWindow object associated with the game over screen.
     * @param LevelManager The LevelManager object associated with the game over screen.
     * @param EnemyManager The EnemyManager object associated with the game over screen.
     * @param player The Player object associated with the game over screen.
     * @param GameOver The GameOver object associated with the game over screen.
     */
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
