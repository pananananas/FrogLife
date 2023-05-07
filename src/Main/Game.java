package Main;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameLoopThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 240;


    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop() {
        if (gameLoopThread == null) {
            gameLoopThread = new Thread(this);
            gameLoopThread.start();
        }
    }

    private void update() {

        gamePanel.updateGame();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;   // how long will frame last in ns ( 1s = 1 000 000 000ns )
        double timePerUpdate = 1000000000.0 / UPS_SET;  // how long will update last in ns ( 1s = 1 000 000 000ns )

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
}