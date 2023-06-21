/**
 * @file Game.java
 * @brief Represents the main game class that manages the game loop and game components.
 */

package Main;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import Entities.EnemyManager;
import Entities.Player;
import Levels.LevelManager;


/**
 * @class Game
 * @brief Represents the main game class that manages the game loop and game components.
 * Implements the Runnable interface for the game loop.
 */
public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameLoopThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 240;

    private Player player;
    private LevelManager LevelManager;
    private EnemyManager EnemyManager;
    private GameOver     GameOver;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static float PLAYER_SCALE = 1.5f * SCALE;
    public final static float ENEMY_SCALE = 1.5f * SCALE;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 26;
    public final static int TILES_SIZE  = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH  = TILES_IN_WIDTH * TILES_SIZE;
    public final static int GAME_HEIGHT = TILES_IN_HEIGHT * TILES_SIZE;

    private boolean gameOver = false;
    
     /**
     * @brief Constructs a Game object and initializes game components.
     */
    public Game() {
        initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus(); 

        startGameLoop();
    }

       /**
     * @brief Initializes game components.
     */
    private void initClasses() {
        LevelManager = new LevelManager(this);
        EnemyManager = new EnemyManager(this);
        player = new Player((int) (GAME_WIDTH / 2), 1100, (int) (64 * PLAYER_SCALE), (int) (40 * PLAYER_SCALE), this);
        player.loadLevelData(LevelManager.getCurrentData().getLevelData());
        GameOver = new GameOver(this);
    }

    /**
     * @brief Starts the game loop.
     */
    private void startGameLoop() {
        if (gameLoopThread == null) {
            gameLoopThread = new Thread(this);
            gameLoopThread.start();
        }
    }

        /**
     * @brief Updates the game components.
     */
    private void update() {
        if (gameOver)   
            return;
        player.update();
        EnemyManager.update(LevelManager.getCurrentData().getLevelData(), player);
        LevelManager.update();
    }

      /**
     * @brief Renders the game components.
     * @param g The Graphics object used for rendering.
     */
    public void render(Graphics g) {
        
        LevelManager.draw(g);
        EnemyManager.draw(g);
        player.render(g);
        
        if (gameOver)   
            GameOver.draw(g);
    }

    @Override
    public void run() {

        double timePerFrame  = 1000000000.0 / FPS_SET;  // how long will frame last in ns
        double timePerUpdate = 1000000000.0 / UPS_SET;  // how long will update last in ns

        long previousTime = System.nanoTime();

        long lastCheck = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            try {
                long currentTime = System.nanoTime();

                deltaU += (currentTime - previousTime) / timePerUpdate;
                deltaF += (currentTime - previousTime) / timePerFrame;

                previousTime = currentTime;

                if (deltaU >= 1) {
                    update();
                    updates++;
                    deltaU--;
                }

                if (deltaF >= 1) {
                    gamePanel.repaint();
                    frames++;
                    deltaF--;
                }

                if (System.currentTimeMillis() - lastCheck >= 1000) {   // FPS counter
                    lastCheck = System.currentTimeMillis();
                    System.out.println("FPS = " + frames + " | UPS = " + updates);
                    frames = 0;
                    updates = 0;
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

       /**
     * @brief Retrieves the player object.
     * @return The player object.
     */
    public Player getPlayer() {
        return player;
    }

        /**
     * @brief Restarts the game by resetting player, level, and enemies.
     */
    public void restartAll() {
        // TODO reset player, level and enemies
    }

       /**
     * @brief Checks for enemy hit based on the provided attack box.
     * @param attackBox The attack box rectangle.
     */
    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        EnemyManager.checkEnemyHit(attackBox);
    }

        /**
     * @brief Sets the game over status.
     * @param gameOver The game over status.
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        
    }
}