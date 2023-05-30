package Main;

import java.awt.Graphics;

import Entities.Player;
import Levels.LevelManager;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameLoopThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 240;

    private Player player;
    private LevelManager LevelManager;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE  = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH  = TILES_IN_WIDTH * TILES_SIZE;
    public final static int GAME_HEIGHT = TILES_IN_HEIGHT * TILES_SIZE;

    public Game() {
        initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus(); 

        startGameLoop();
    }

    private void initClasses() {
        player = new Player(100, 100);
        LevelManager = new LevelManager(this);

    }

    private void startGameLoop() {
        if (gameLoopThread == null) {
            gameLoopThread = new Thread(this);
            gameLoopThread.start();
        }
    }

    private void update() {
        player.update();
        LevelManager.update();
    }

    public void render(Graphics g) {
        player.render(g);
        LevelManager.draw(g);
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

    public Player getPlayer() {
        return player;
    }
}