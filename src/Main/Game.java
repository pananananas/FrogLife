package Main;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameLoopThread;
    private final int FPS_SET = 120;

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

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;   // how long will frame last in ns ( 1s = 1 000 000 000ns )
        long lastTime = System.nanoTime();              // get current time in ns
        long now = System.nanoTime();

        while (true) {
            try {
                now = System.nanoTime();
                if (now - lastTime >= timePerFrame) {
                    gamePanel.repaint();
                    lastTime = now;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}