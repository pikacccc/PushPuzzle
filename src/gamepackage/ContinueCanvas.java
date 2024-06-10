package gamepackage;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;

public class ContinueCanvas extends GameCanvas implements Runnable {
    private Graphics g;
    private Image Title;

    private int bg_x;
    private int bg_y;
    private int title_x;
    private int title_y;

    private boolean isRunning = false;

    private GameMidlet midlet;

    protected ContinueCanvas(GameMidlet midlet) {
        super(false);
        this.setFullScreenMode(true);
        this.midlet=midlet;
        g = getGraphics();
        Title = Util.LoadImg("/res/win1.png");
        InitXY();
    }

    private void InitXY() {
        int width = getWidth();
        int height = getHeight();

        int center_x = width / 2;
        int center_y = height / 2;

        bg_x = center_x - Util.bg.getWidth() / 2;
        bg_y = center_y - Util.bg.getHeight() / 2;
        title_x = center_x - Title.getWidth() / 2;
        title_y = center_y - Title.getHeight() / 2 - 100;
    }

    private void Draw() {
        g.drawImage(Util.bg, bg_x, bg_y, 0);
        g.drawImage(Title, title_x, title_y, Graphics.TOP | Graphics.LEFT);
        flushGraphics();
    }

    public void Start() {
        isRunning = true;
        Draw();
        Thread t = new Thread(this);
        t.start();
    }

    public void Stop() {
        isRunning = false;
    }

    public void run() {
        while (isRunning) {
            Sleep(2000);
            Stop();
        }
        midlet.GoToNextLevel();
    }

    public void Sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            Stop();
        }
    }
}
