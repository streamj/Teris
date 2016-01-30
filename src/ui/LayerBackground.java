package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by StReaM on 11/26/2015.
 */
public class LayerBackground extends Layer {

    public LayerBackground(int x, int y, int width, int height) {
        super(x,y,width,height);
    }
    @Override
    public void paint(Graphics g) {
       // g.drawImage(BG_IMG, 0,0,null);
        int bgIdx = this.dto.getLevel() % Img.BG_LIST.size();
        g.drawImage(Img.BG_LIST.get(bgIdx),0,0,1162,676,null);
    }
}
