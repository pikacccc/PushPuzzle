package gamepackage;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.GameCanvas;
import java.io.PrintStream;

public class Menu extends GameCanvas implements Runnable {
    private boolean isRunning = false;
    private Graphics g;
    private int selectedOption = 0;

    public GameMidlet midlet;

    private Image Title;
    private Image Play;
    private Image Exit;
    private Image Continue;
    private Image Restart;
    private int width;
    private int height;
    private int bg_x;
    private int bg_y;
    private int title_x;
    private int title_y;
    private int play_x;
    private int play_y;
    private int exit_x;
    private int exit_y;
    private int continue_x;
    private int continue_y;
    private int restart_x;
    private int restart_y;

    private boolean showContinue;

    private String version="V1.5";

    public void SetContinue(boolean showContinue) {
        this.showContinue = showContinue;
    }

    public Menu(GameMidlet midlet, boolean showContinue) {
        super(false);
        this.setFullScreenMode(true);
        SetContinue(showContinue);
        this.midlet = midlet;
        g = getGraphics();
        LoadImages();
        InitCoordinates();
    }

    private void LoadImages() {
        Title = Util.LoadImg("/res/begin.png");
        Play = Util.LoadImg("/res/btn_start.png");
        Exit = Util.LoadImg("/res/btn_quit.png");
        Continue = Util.LoadImg("/res/btn_continue.png");
        Restart = Util.LoadImg("/res/btn_restart.png");
    }

    public void InitCoordinates() {
        width = getWidth();
        height = getHeight();

        int center_x = width / 2;
        int center_y = height / 2;

        bg_x = center_x - Util.bg.getWidth() / 2;
        bg_y = center_y - Util.bg.getHeight() / 2;
        title_x = center_x - Title.getWidth() / 2;
        title_y = center_y - Title.getHeight() / 2 - 70;
        continue_x = center_x - Continue.getWidth() / 2;
        continue_y = center_y - Continue.getHeight() / 2 + 70;
        play_x = center_x - Play.getWidth() / 2;
        play_y = center_y - Play.getHeight() / 2 + 120;
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

    private void tick() {
    }

    public void run() {
        draw();
        while (isRunning) {
//            tick();
//            if (!isRunning) break;
//            draw();
        }
    }

    public void stop() {
        isRunning = false;
    }

    private void draw() {
        g.setColor(0);
        g.fillRect(0, 0, width, height);
        g.drawImage(Util.bg, bg_x, bg_y, 0);
        g.drawImage(Title, title_x, title_y, Graphics.TOP | Graphics.LEFT);
        if (!showContinue) {
            DrawBtnWithNoContinue();
        } else {
            DrawBtnWithContinue();
        }
        g.drawString(version, 0, height-20, Graphics.TOP | Graphics.LEFT);
        flushGraphics();
    }

    private void DrawBtnWithNoContinue() {
        if (selectedOption == 0) {
            g.setColor(0xFADF5F);
        } else {
            g.setColor(0xFFFFCF);
        }
        g.fillRect(play_x - 32, play_y - 8, 140, 32);
        g.drawImage(Play, play_x, play_y, 0);
        if (selectedOption == 1) {
            g.setColor(0xFADF5F);
        } else {
            g.setColor(0xFFFFCF);
        }
        g.fillRect(exit_x - 32, exit_y - 8, 140, 32);
        g.drawImage(Exit, exit_x, exit_y, 0);
    }

    private void DrawBtnWithContinue() {
        if (selectedOption == 0) {
            g.setColor(0xFADF5F);
        } else {
            g.setColor(0xFFFFCF);
        }
        g.fillRect(continue_x - 32, continue_y - 8, 140, 32);
        g.drawImage(Continue, continue_x, continue_y, 0);
        if (selectedOption == 1) {
            g.setColor(0xFADF5F);
        } else {
            g.setColor(0xFFFFCF);
        }
        g.fillRect(restart_x - 32, restart_y - 8, 140, 32);
        g.drawImage(Restart, restart_x, restart_y, 0);
        if (selectedOption == 2) {
            g.setColor(0xFADF5F);
        } else {
            g.setColor(0xFFFFCF);
        }
        g.fillRect(exit_x - 32, exit_y - 8, 140, 32);
        g.drawImage(Exit, exit_x, exit_y, 0);
    }

    protected void keyPressed(int keyCode) {
        int gameAction = getGameAction(keyCode);
        int len = showContinue ? 3 : 2;
        if (gameAction == UP) {
            selectedOption = (selectedOption - 1 + len) % len;
        } else if (gameAction == DOWN) {
            selectedOption = (selectedOption + 1) % len;
        } else if (gameAction == FIRE || gameAction == KEY_NUM5) {
            executeSelectedOption();
        }
        draw();
    }

    private void executeSelectedOption() {
        if (!showContinue) {
            if (selectedOption == 0) {
                midlet.StartGame(true);
                midlet.CloseMenu();
            } else if (selectedOption == 1) {
                midlet.exitMIDlet();
            }
        } else {
            if (selectedOption == 0) {
                midlet.StartGame(false);
                midlet.CloseMenu();
            } else if (selectedOption == 1) {
                midlet.StartGame(true);
                midlet.CloseMenu();
            } else if (selectedOption == 2) {
                midlet.exitMIDlet();
            }
        }
    }

    public void commandAction(Command command, Displayable displayable) {
//        if (command == selectCommand) {
//            executeSelectedOption();
//        }
    }
}
