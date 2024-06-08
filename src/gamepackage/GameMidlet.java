/*
 * GameMidlet.java
 *
 * Created on 2007��12��15��, ����12:56
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gamepackage;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
/**
 *
 * @author bense
 */
public class GameMidlet extends MIDlet{
    
    private Display display=null;
    private GameChoose choice=null;
    
    public GameMidlet() {
        choice=new GameChoose(this);
        display=Display.getDisplay(this);
    }
    public void startApp(){
        display.setCurrent(choice);
    }
    public void pauseApp(){
        
    }
    public void play(Displayable displayable){
        display.setCurrent(displayable);
    }
    public void destroyApp(boolean unconditional){
        
    }
    
}
