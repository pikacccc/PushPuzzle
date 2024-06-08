/*
 * GameChoose.java
 *
 * Created on 2007年12月15日, 上午1:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gamepackage;

import javax.microedition.lcdui.*;
/**
 *
 * @author bense
 */
public class GameChoose extends Canvas{
    
    private GameMidlet midlet=null;
    private boolean ok;
    private boolean select;
    private boolean ok2;
    private int  select2;
    private int  index;
    private int w,h;
    private GameMain game=null;
    
    public GameChoose(GameMidlet midlet) {
        this.midlet=midlet;
        init();
    }
    public void init(){
        reset();
    }
    public void reset(){
        ok=true;
        select=false;
        ok2=false;
        select2=0;
        index=-1;
    }
    public void show(){
        midlet.play(this);
    }
    public void paint(Graphics g){        
        h=getHeight();
        w=getWidth();
        g.setColor(0xffffff);
        g.fillRect(0,0, w,h);
        g.setFont(Font.getFont(Font.FACE_SYSTEM,Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        g.setColor(0xFCA204);
        g.fillRect(0, 0, w,18);
        g.setColor(0x000000);
        g.drawLine(0,18,w,18); 
        g.drawString("推箱子",w/2,16, Graphics.HCENTER|Graphics.BOTTOM);
        g.drawString("8↑ 2↓ 4← 6→", w/2, 50, Graphics.HCENTER|Graphics.BOTTOM);
        g.drawString("右上按钮 退出", w/2, 70, Graphics.HCENTER|Graphics.BOTTOM);
        g.drawString("1 上一关节   3 下一关", w/2, 90, Graphics.HCENTER|Graphics.BOTTOM);
        g.setColor(0x046DFB);
        g.fillRect(0, h-22, w,22);
        g.setColor(0x000000);
        g.drawLine(0, h-22, w,h-22); 
        g.fillTriangle(3,h-11,11,h-15,11,h-7);
        g.fillTriangle(w-3,h-11,w-11,h-15,w-11,h-7);
        if(ok){
            g.setColor(0x0452BC);
            g.fillRect(14, h-22, w/2-14, 22);
            g.setColor(0xffffff);
            g.drawString("新游戏",w/4+7,h-3, Graphics.HCENTER|Graphics.BOTTOM);
            g.setColor(0x000000);
            g.drawString("选关",3*w/4-7,h-3,Graphics.HCENTER|Graphics.BOTTOM);
        }else{
            g.setColor(0x0452BC);
            g.fillRect(w/2, h-22, w/2-14, 22);
            g.setColor(0x000000);
            g.drawString("新游戏",w/4+7,h-3, Graphics.HCENTER|Graphics.BOTTOM);
            g.setColor(0xffffff);
            g.drawString("选关",3*w/4-7,h-3,Graphics.HCENTER|Graphics.BOTTOM);
        }
        if(select==true){
            g.setColor(0xffffff);
            g.fillRect(25,40, 130, 100);
            g.setColor(0x046DFB);
            g.fillRect(27, 118,126, 20);          
            g.setColor(0xFFA800);
            g.drawRect(25,40,130,100);
            g.setColor(0xFFA800);
            g.drawRect(27,42,126,96);
            g.setColor(0x000000);
            g.fillTriangle(30,128, 36, 125, 36, 131);
            g.fillTriangle(150, 128, 146, 125, 146, 131);
            g.setColor(0x000000);   
            g.drawString("选关",60, 80 ,Graphics.HCENTER|Graphics.BOTTOM);
            g.drawString("(1~40)", 60, 100,Graphics.HCENTER|Graphics.BOTTOM);
            g.setFont(Font.getFont(Font.FACE_SYSTEM,Font.STYLE_PLAIN|Font.STYLE_BOLD,Font.SIZE_LARGE));
            g.drawString("[   ]", 115, 90,Graphics.HCENTER|Graphics.BOTTOM );
            g.setFont(Font.getFont(Font.FACE_SYSTEM,Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
            if(index==-1&&select2==0){
                g.drawString("(", 115, 87,Graphics.HCENTER|Graphics.BOTTOM );
            }else if(index!=-1){
                g.drawString(""+index, 115, 87,Graphics.HCENTER|Graphics.BOTTOM );
            }
            if(select2==1){
                g.setColor(0x0452BC);
                g.fillRect(40, 118, 50, 20);
                g.setColor(0xffffff);
                g.drawString("确定", 65, 136, Graphics.HCENTER|Graphics.BOTTOM);
                g.setColor(0x000000);
                g.drawString("取消", 115, 136, Graphics.HCENTER|Graphics.BOTTOM);
            } 
            else if(select2==2){
                g.setColor(0x0452BC);
                g.fillRect(90, 118, 50, 20);
                g.setColor(0x000000);
                g.drawString("确定", 65, 136, Graphics.HCENTER|Graphics.BOTTOM);
                g.setColor(0xffffff);
                g.drawString("取消", 115, 136, Graphics.HCENTER|Graphics.BOTTOM);
            }else if(select2==0){
                g.setColor(0x000000);
                g.drawString("确定", 65, 136, Graphics.HCENTER|Graphics.BOTTOM);              
                g.drawString("取消", 115, 136, Graphics.HCENTER|Graphics.BOTTOM);
            }
        }
    }
    public void keyPressed(int keyCode){
        if(select==false){
            switch(getGameAction(keyCode)){
                case Canvas.LEFT:
                case Canvas.RIGHT:
                     if(ok==true){
                        ok=false;
                    }else{
                        ok=true;
                    }
                    break;
                case Canvas.FIRE:
                    if(ok==true){
                        game=new GameMain(this);
                        game.start();
                        midlet.play(game);
                    }
                    else{
                        select=true;
                        ok2=true;
                    }
                    break;
            }
        }else{ 
            if(select2==0){
                if(keyCode<58&&keyCode>47){
                    keyCode-=48;
                    if(index==-1){
                        index=keyCode;
                    }else if(index<10){
                        index=index*10+keyCode;
                    }
                }else{
                    switch(getGameAction(keyCode)){
                        case Canvas.UP:
                            select2=2;
                            break;
                        case Canvas.DOWN:
                            select2++;
                            break; 
                        default:
                            if(keyCode==-7){
                                if(index>9){
                                    index/=10;
                                }else{
                                    index=-1;
                                }
                            }
                            
                    }
                }
            }else{
                switch(getGameAction(keyCode)){
                    case Canvas.UP:
                        select2--;
                        break;
                    case Canvas.DOWN:
                        select2++;
                        if(select2==3){
                            select2=0;
                        }
                        break;
                    case Canvas.FIRE:
                        if(select2==1){
                            if(index>0&&index<=42){
                                game=new GameMain(this,index-1);
                                game.start();
                                midlet.play(game);
                            }
                            
                        }else{
                            ok=true;
                            select2=0;
                            select=false;
                        }
                        break;
              
                }
            }
        }
        repaint();
    }
}
