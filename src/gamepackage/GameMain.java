/*
 * GameMain.java
 *
 * Created on 2007��12��15��, ����1:03
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gamepackage;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;

/**
 * @author bense
 */
public class GameMain extends GameCanvas implements Runnable, IRestartGame {

    private boolean runningFlag = true;
    private int index;
    private Map map;
    private Graphics g;
    private GameMidlet midlet;

    public boolean pause = false;
    private PausePannel pp;

    public GameMain(GameMidlet midlet) {
        super(false);
        setFullScreenMode(true);
        this.midlet = midlet;
        g = getGraphics();
        pp = new PausePannel(midlet, this, this.getWidth(), this.getHeight());
    }

    public void Stop() {
        runningFlag = false;
    }

    public void start(int index) {
        InitData(index);
        Thread thread = new Thread(this);
        thread.start();
    }

    private void InitData(int index) {
        map = new Map(this, g, index);
        this.index = index;
        runningFlag = true;
    }

    public void keyPressed(int keyCode) {
        int key = getGameAction(keyCode);
        if (keyCode == 8 || keyCode == 96 || keyCode == -6 || keyCode == 48 || keyCode == -31 || keyCode == -8 || keyCode == -9 || keyCode == -5) {
            if (key != FIRE && key != UP && key != LEFT && key != RIGHT && key != DOWN) {
                pause = true;
                return;
            }
        }

        if (!pause) {
            switch (key) {
                case GameCanvas.DOWN:
                    map.getKey(3);
                    break;
                case GameCanvas.UP:
                    map.getKey(1);
                    break;
                case GameCanvas.LEFT:
                    map.getKey(2);
                    break;
                case GameCanvas.RIGHT:
                    map.getKey(4);
                    break;
                case GameCanvas.FIRE:
                    Reset();
                    break;
                default:
                    break;
            }
        } else {
            pp.keyPressed(key);
        }
    }

    protected void hideNotify() {
        super.hideNotify();
        pause = true;
        System.out.println("Out");
    }

    public void run() {
        while (runningFlag) {
            if (map.isFinish()) {
                midlet.ShowWin(index);
            } else {
                map.paint();
                if (pause) pp.Draw(g);
            }
            flushGraphics();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void Reset() {
        InitData(index);
    }

    public void RestartGame() {
        pause = false;
    }

    private void drawString(Graphics g, String str, int x, int y, int anchor) {
        g.setColor(0, 0, 0);
        g.drawString(str, x - 2, y, anchor);
        g.drawString(str, x + 2, y, anchor);
        g.drawString(str, x, y - 2, anchor);
        g.drawString(str, x, y + 2, anchor);
        g.setColor(0, 0, 129);
        g.drawString(str, x - 1, y, anchor);
        g.drawString(str, x + 1, y, anchor);
        g.drawString(str, x, y - 1, anchor);
        g.drawString(str, x, y + 1, anchor);
        g.setColor(199, 218, 243);
        g.drawString(str, x, y, anchor);
    }
}
