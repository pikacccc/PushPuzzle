/*
 * Continue.java
 *
 * Created on 2007��12��2��, ����6:33
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
    
public class Continue {
    
    /** Creates a new instance of Continue */
    public boolean ok=true;
    public boolean ok2=true;
    public Continue() {
    }
    public void paint(Graphics g){
        g.setColor(0xffffff);
        g.fillRect(25,40, 130, 100);
        g.setColor(0x046DFB);
        g.fillRect(27, 118,126, 20);          
        g.setColor(0xFB1504);
        g.drawRect(25,40,130,100);
        g.drawRect(27,42,126,96);
        g.setColor(0x000000);
        g.fillTriangle(30,128, 36, 125, 36, 131);
        g.fillTriangle(150, 128, 146, 125, 146, 131);
        g.setColor(0x000000);   
        g.drawString("��ϲ����,ѡ��",90, 80 ,Graphics.HCENTER|Graphics.BOTTOM);
        g.drawString("����������", 90, 100,Graphics.HCENTER|Graphics.BOTTOM);
        if(ok){
            g.setColor(0x0452BC);
            g.fillRect(40, 118, 50, 20);
            g.setColor(0xffffff);
            g.drawString("��һ��", 65, 136, Graphics.HCENTER|Graphics.BOTTOM);
            g.setColor(0x000000);
            g.drawString("����", 115, 136, Graphics.HCENTER|Graphics.BOTTOM);
        }else{
            g.setColor(0x0452BC);
            g.fillRect(90, 118, 50, 20);
            g.setColor(0x000000);
            g.drawString("��һ��", 65, 136, Graphics.HCENTER|Graphics.BOTTOM);
            g.setColor(0xffffff);
            g.drawString("����", 115, 136, Graphics.HCENTER|Graphics.BOTTOM);
        }
    }
    public void paint1(Graphics g){
        g.setColor(0xffffff);
        g.fillRect(25,40, 130, 100);
        g.setColor(0x046DFB);
        g.fillRect(27, 118,126, 20);          
        g.setColor(0xFB1504);
        g.drawRect(25,40,130,100);
        g.drawRect(27,42,126,96);
        g.setColor(0x000000);
        g.fillTriangle(30,128, 36, 125, 36, 131);
        g.fillTriangle(150, 128, 146, 125, 146, 131);
        g.setColor(0x000000);   
        g.drawString("ȷ��Ҫ�˳�",90, 80 ,Graphics.HCENTER|Graphics.BOTTOM);
        g.drawString("������?", 90, 100,Graphics.HCENTER|Graphics.BOTTOM);
        if(ok2){
            g.setColor(0x0452BC);
            g.fillRect(40, 118, 50, 20);
            g.setColor(0xffffff);
            g.drawString("ȷ��", 65, 136, Graphics.HCENTER|Graphics.BOTTOM);
            g.setColor(0x000000);
            g.drawString("ȡ��", 115, 136, Graphics.HCENTER|Graphics.BOTTOM);
        }else{
            g.setColor(0x0452BC);
            g.fillRect(90, 118, 50, 20);
            g.setColor(0x000000);
            g.drawString("ȷ��", 65, 136, Graphics.HCENTER|Graphics.BOTTOM);
            g.setColor(0xffffff);
            g.drawString("ȡ��", 115, 136, Graphics.HCENTER|Graphics.BOTTOM);
        }
    }
    
}
