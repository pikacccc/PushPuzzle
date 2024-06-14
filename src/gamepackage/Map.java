/*
 * Map.java
 *
 * Created on 2007年11月30日, 下午8:52
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package gamepackage;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.*;

/**
 * @author bense
 */
public class Map {
    private Image gameBg;

    private Sprite sp1 = new Sprite(ImageAction.getImage("/res/1.png"));
    private Sprite sp2 = new Sprite(ImageAction.getImage("/res/2.png"));
    private Sprite sp3 = new Sprite(ImageAction.getImage("/res/3.png"));
    private Sprite sp4 = new Sprite(ImageAction.getImage("/res/4.png"));
    private Sprite sp5 = new Sprite(ImageAction.getImage("/res/5.png"));
    private int length = ImageAction.getImage("/res/1.png").getHeight();
    private boolean finish;
    private Graphics g;
    private GameMain game = null;
    private int[][] box;
    private int[][] container;
    private int[][] map;
    private int x, y;
    private int w, h;

    private int width;
    private int height;
    private int bg_x;
    private int bg_y;
    private int gameBg_x;
    private int gameBg_y;

    /**
     * Creates a new instance of Map
     */
    public Map(GameMain game, Graphics g, int index) {
        this.game = game;
        this.g = g;
        gameBg = ImageAction.getImage("/res/backgroud2.png");
        initXY(game);
        initMap(index);
    }

    private void initXY(GameMain game) {
        width = game.getWidth();
        height = game.getHeight();
        int center_x = width / 2;
        int center_y = height / 2;
        bg_x = center_x - Util.bg.getWidth() / 2;
        bg_y = center_y - Util.bg.getHeight() / 2;
        gameBg_x = center_x - gameBg.getWidth() / 2;
        gameBg_y = center_y - gameBg.getHeight() / 2;
    }

    public void initMap(int index) {
        AllMap all = new AllMap();
        map = all.getMap(index);
        box = new int[all.getBoxLength(index, 3)][2];
        container = new int[all.getBoxLength(index, 2)][2];
        finish = false;
        init(2);
        init(3);
        init(4);
        setXY();
    }

    private void setXY() {
        x = (game.getWidth() - 12 * length) / 2;
        y = (game.getHeight() - 12 * length + 10) / 2 - 5;
    }

    private void setBox() {
        int findex = 0;
        for (int i = 0; i < container.length; i++) {
            for (int j = 0; j < box.length; j++) {
                if (container[i][0] == box[j][0] && container[i][1] == box[j][1]) {
                    Sp(container[i][1], container[i][0], 5);
                    findex++;
                }
            }
        }
        finish = (findex == container.length);
    }

    public boolean isFinish() {
        return finish;
    }

    public void Sp(int w, int h, int index) {
        switch (index) {
            case 1:
                sp1.setPosition(x + w * length, y + h * length);
                sp1.paint(g);
                break;
            case 2:
                sp2.setPosition(x + w * length, y + h * length);
                sp2.paint(g);
                break;
            case 3:
                sp3.setPosition(x + w * length, y + h * length);
                sp3.paint(g);
                break;
            case 4:
                sp4.setPosition(x + w * length, y + h * length);
                sp4.paint(g);
                break;
            case 5:
                sp5.setPosition(x + w * length, y + h * length);
                sp5.paint(g);
                break;
        }

    }

    public void paint() {
        DrawBg();
        setContainer();
        setMap();
        setMan();
        setBox();
    }

    private void DrawBg() {
        g.drawImage(Util.bg, bg_x, bg_y, 0);
        g.drawImage(gameBg, gameBg_x, gameBg_y, 0);
    }

    private void setContainer() {
        for (int i = 0; i < container.length; i++) {
            Sp(container[i][1], container[i][0], 2);
        }
    }

    private void setMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                Sp(j, i, map[i][j]);
            }
        }
    }

    private void setMan() {
        Sp(w, h, 4);
    }

    private void set(int x1, int y1, int x2, int y2) {
        map[y1][x1] = 0;
        map[y2][x2] = 3;
        for (int i = 0; i < box.length; i++) {
            if (box[i][0] == y1 && box[i][1] == x1) {
                box[i][0] = y2;
                box[i][1] = x2;
            }
        }
    }

    private boolean isEmpty(int w, int h) {
        return map[h][w] == 0;
    }

    public void getKey(int keyCode) {
        switch (keyCode) {
            case 1:
                if (isEmpty(w, h - 1)) {
                    h -= 1;
                } else {
                    if (h > 1 && map[h - 1][w] != 1 && isEmpty(w, h - 2)) {
                        set(w, h - 1, w, h - 2);
                        h -= 1;
                    }
                }
                break;
            case 3:
                if (isEmpty(w, h + 1)) {
                    h += 1;
                } else {
                    if (h < map.length - 2 && map[h + 1][w] != 1 && isEmpty(w, h + 2)) {
                        set(w, h + 1, w, h + 2);
                        h += 1;
                    }
                }
                break;
            case 2:
                if (isEmpty(w - 1, h)) {
                    w -= 1;
                } else {
                    if (w > 1 && map[h][w - 1] != 1 && isEmpty(w - 2, h)) {
                        set(w - 1, h, w - 2, h);
                        w -= 1;
                    }
                }
                break;
            case 4:
                if (isEmpty(w + 1, h)) {
                    w += 1;
                } else {
                    if (w < map[0].length - 2 && map[h][w + 1] != 1 && isEmpty(w + 2, h)) {
                        set(w + 1, h, w + 2, h);
                        w += 1;
                    }
                }
                break;
        }
    }

    public void init(int index) {
        int in = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == index) {
                    switch (index) {
                        case 3:
                            box[in][0] = i;
                            box[in][1] = j;
                            in++;
                            break;
                        case 2:
                            container[in][0] = i;
                            container[in][1] = j;
                            in++;
                            map[i][j] = 0;
                            break;
                        case 4:
                            w = j;
                            h = i;
                            map[i][j] = 0;
                            break;
                        case 5:
                            container[in][0] = i;
                            container[in][1] = j;
                            box[in][0] = i;
                            box[in][1] = j;
                            map[i][j] = 3;
                            in++;
                            break;
                    }
                }
            }
        }
    }
}
