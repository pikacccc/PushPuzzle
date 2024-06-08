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
 *
 * @author bense
 */
public class GameMain extends GameCanvas implements Runnable{
    
    private boolean runningFlag=true;
    private boolean cancel=false;
    private int index;
    private GameChoose choice=null;
    private Map map;
    private Graphics g;
    private Continue con;
    
    public GameMain(GameChoose choice){
        this(choice,0);
    }
    public GameMain(GameChoose choice,int index) {
        super(false);
        g=getGraphics();
        map=new Map(this,g,index);
        this.choice=choice;
        this.index=index;
        con=new Continue();
    }
    public void stop(){
        runningFlag=false;
    }
    public void commandAction(Command cmd,Displayable displayable){
        
    }
    public void start(){
        Thread thread=new Thread(this);
        thread.start();
    }
    public void keyPressed(int keyCode){
        int key=getGameAction(keyCode);
        if(map.isFinish()){
            switch(key){
                case GameCanvas.LEFT:
                case GameCanvas.RIGHT:
                    if(con.ok==true){
                        con.ok=false;
                    }else
                    {
                        con.ok=true;
                    }
                    break;
                case GameCanvas.FIRE:
                    if(con.ok==true){
                        index++;
                        if(index==42){
                            index=0;
                        }
                    }
                    con.ok=true;
                    map.initMap(index);
                    break;
                default:
                    if(keyCode==-7){
                        stop();
                    }
            }
        }else{
            if(cancel==false){
                switch(key){
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
                        switch(keyCode){
                            case 49:
                                index--;
                                if(index==-1){
                                    index=41;
                                }
                                map.initMap(index);
                                break;
                            case 51:
                                index++;
                                if(index==42){
                                    index=0;
                                }
                                map.initMap(index);
                                break;
                            case -7:
                                cancel=true;
                                break;
                        }
                }
            }else{
                switch(key){
                    case GameCanvas.LEFT:
                    case GameCanvas.RIGHT:
                        if(con.ok2==true){
                            con.ok2=false;
                        }else{
                            con.ok2=true;
                        }
                        break;
                    case GameCanvas.FIRE:
                        if(con.ok2==true){
                            stop();
                        }else{
                            cancel=false;
                        }
                        con.ok2=true;
                        break;
                }
            }
        }
        
    }
    private void setBackground(){
        g.setColor(0x6A4055);
        g.fillRect(0,0, getWidth(),getHeight());
        g.setColor(0xF89433);
        g.drawLine(0,20,getWidth(),20); 
        g.drawString("你正在攻克第"+(index+1)+"关",getWidth()/2,18, Graphics.HCENTER|Graphics.BOTTOM);
        g.drawRect(0, 0, getWidth()-2, getHeight()-3);
        g.drawRect(1,1, getWidth()-3, getHeight()-4);
    }
    public void run(){  
        while(runningFlag){
            if(map.isFinish()){
                con.paint(g);
            }else{
                if(cancel==false){
                    setBackground();
                    map.paint();
                }else{
                    con.paint1(g);
                }
                
            }
            flushGraphics();
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        choice.init();
        choice.show();
    }
}
