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
        long lastCheck = System.currentTimeMillis();
        int frames = 0;

        while (true) {
            try {
                now = System.nanoTime();
                if (now - lastTime >= timePerFrame) {                   // if frame is done
                    gamePanel.repaint();
                    lastTime = now;
                    frames++;
                }

                if (System.currentTimeMillis() - lastCheck >= 1000) {   // FPS counter
                    lastCheck = System.currentTimeMillis();
                    System.out.println("FPS = " + frames);
                    frames = 0;
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}