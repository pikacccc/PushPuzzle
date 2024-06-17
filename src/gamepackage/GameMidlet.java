/*
 * GameMidlet.java
 *
 * Created on 2007年12月15日, 上午12:56
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gamepackage;

import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author bense
 */
public class GameMidlet extends MIDlet implements IExit {
    public static final int MAXLEVEL = 41;
    private final Display display;
    public final GameMain canvas;
    public Menu gameMenu;
    public GameOver gameOver;
    public ContinueCanvas continueCanvas;
    public RecordStores rs;

    public int Level;

    public GameMidlet() {
        RestoreData();
        display = Display.getDisplay(this);
        canvas = new GameMain(this);
        continueCanvas = new ContinueCanvas(this);
        gameMenu = new Menu(this, Level > 0);
        gameOver = new GameOver(this);
    }

    public void startApp() {
        OpenMenu();
    }

    public void pauseApp() {

    }

    public void destroyApp(boolean unconditional) {
        SaveData();
        canvas.Stop();
        display.setCurrent(null);
    }

    public void OpenMenu() {
        gameMenu.SetContinue(Level > 0);
        gameMenu.start();
        display.setCurrent(gameMenu);
    }

    public void StartGame(boolean restart) {
        if (restart) Level = 0;
        canvas.start(Level);
        display.setCurrent(canvas);
    }

    public void CloseGameOver() {
        gameOver.stop();
    }

    public void ShowWin(int index) {
        CloseGame();
        OnFinish();
        if (index >= MAXLEVEL) {
            gameOver.start();
            display.setCurrent(gameOver);
        } else {
            continueCanvas.Start();
            display.setCurrent(continueCanvas);
        }
    }

    public void CloseMenu() {
        gameMenu.stop();
    }

    public void exitMIDlet() {
        destroyApp(true);
        notifyDestroyed();
    }

    public void RestoreData() {
        rs = new RecordStores("PushPuzzle", 1);
        int nr = rs.getNumRecords();
        if (nr > 0) Level = rs.getRecord(1);
        else {
            Level = 0;
        }
    }

    public void SaveData() {
        rs.setRecord(1, Level);
        rs.closeRecords();
    }

    public void GoToNextLevel() {
        StartGame(false);
    }

    public void OnFinish() {
        if (Level <= MAXLEVEL)
            Level++;
    }

    public void CloseGame() {
        canvas.Stop();
    }

    public void Exit() {
        exitMIDlet();
    }
}
