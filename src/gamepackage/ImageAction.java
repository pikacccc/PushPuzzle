/*
 * ImageAction.java
 *
 * Created on 2007年11月30日, 下午9:07
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package gamepackage;

import javax.microedition.lcdui.*;
/**
 *
 * @author bense
 */
public class ImageAction {
    
    /** Creates a new instance of ImageAction */
    public ImageAction() {
    }
    public static Image getImage(String url){
        Image img=null;
        try{
            img=Image.createImage(url);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return img;
    }
    
}
