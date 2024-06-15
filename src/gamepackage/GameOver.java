package gamepackage;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.GameCanvas;

public class GameOver extends GameCanvas implements Runnable {
    private boolean isRunning = false;
    private Graphics g;
    private int selectedOption = 0;

    public GameMidlet midlet;

    private Image GameOver;
    private Image Restart;
    private Image Exit;

    private int width;
    private int height;
    private int bg_x;
    private int bg_y;
    private int gameOver_x;
    private int gameOver_y;
    private int restart_x;
    private int restart_y;
    private int exit_x;
    private int exit_y;

    public GameOver(GameMidlet midlet) {
        super(false);
        setFullScreenMode(true);
        this.midlet = midlet;
        g = getGraphics();
        LoadImages();
        InitCoordinates();
    }

    private void LoadImages() {
        GameOver = Util.LoadImg("/res/win2.png");
        Restart = Util.LoadImg("/res/btn_start.png");
        Exit = Util.LoadImg("/res/btn_quit.png");
    }

    public void InitCoordinates() {
        width = getWidth();
        height = getHeight();

        int center_x = width / 2;
        int center_y = height / 2;

        bg_x = center_x - Util.bg.getWidth() / 2;
        bg_y = center_y - Util.bg.getHeight() / 2;
        gameOver_x = center_x - GameOver.getWidth() / 2;
        gameOver_y = center_y - GameOver.getHeight() / 2 - 70;
        restart_x = center_x - Restart.getWidth() / 2;
        restart_y = center_y - Restart.getHeight() / 2 + 120;
        exit_x = center_x - Exit.getWidth() / 2;
        exit_y = center_y - Exit.getHeight() / 2 + 170;
    }

    public void start() {
        isRunning = true;
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        draw();
        while (isRunning) {
//            tick();
//            if (!isRunning) break;
//            draw();
        }
    }

    private void tick() {
    }

    public void stop() {
        isRunning = false;
    }

    private void draw() {
        g.setColor(0);
        g.fillRect(0, 0, width, height);
        g.drawImage(Util.bg, bg_x, bg_y, 0);
        g.drawImage(GameOver, gameOver_x, gameOver_y, Graphics.TOP | Graphics.LEFT);
        if (selectedOption == 0) {
            g.setColor(0xFADF5F);
        } else {
            g.setColor(0xFFFFCF);
        }
        g.fillRect(restart_x - 32, restart_y - 8, 160, 32);
        g.drawImage(Restart, restart_x, restart_y, 0);
        if (selectedOption == 1) {
            g.setColor(0xFADF5F);
        } else {
            g.setColor(0xFFFFCF);
        }
        g.fillRect(exit_x - 32, exit_y - 8, 160, 32);
        g.drawImage(Exit, exit_x, exit_y, 0);
        flushGraphics();
    }

    protected void keyPressed(int keyCode) {
        int gameAction = getGameAction(keyCode);
        if (gameAction == UP) {
            selectedOption = (selectedOption - 1 + 2) % 2;
        } else if (gameAction == DOWN) {
            selectedOption = (selectedOption + 1) % 2;
        } else if (gameAction == FIRE) {
            executeSelectedOption();
        }
        draw();
    }

    private void executeSelectedOption() {
        if (selectedOption == 0) {
            midlet.StartGame(true);
            midlet.CloseGameOver();
        } else if (selectedOption == 1) {
            midlet.exitMIDlet();
        }
    }
}
