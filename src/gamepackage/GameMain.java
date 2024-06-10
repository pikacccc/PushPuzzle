/*
 * GameMain.java
 *
 * Created on 2007年12月15日, 上午1:03
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
public class GameMain extends GameCanvas implements Runnable, CommandListener {

    private boolean runningFlag = true;
    private int index;
    private Map map;
    private Graphics g;
    private GameMidlet midlet;

    Command cmdBack = new Command("", Command.EXIT, 1);
    Command cmdReset = new Command("", Command.OK, 1);

    public GameMain(GameMidlet midlet) {
        super(false);
        this.midlet = midlet;
        g = getGraphics();

        addCommand(cmdBack);
        addCommand(cmdReset);
        setCommandListener(this);
    }

    public void Stop() {
        runningFlag = false;
    }

    public void commandAction(Command cmd, Displayable displayable) {
        if (!map.isFinish()) {
            if (cmd == cmdBack) {
                midlet.OpenMenu();
                midlet.CloseGame();
            } else if (cmd == cmdReset) {
                Reset();
            }
        }
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
            default:
                break;
        }
    }

    public void run() {
        while (runningFlag) {
            if (map.isFinish()) {
                midlet.ShowWin(index);
            } else {
                map.paint();
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
}
