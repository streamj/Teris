package ui;

import java.awt.*;

/**
 * Created by StReaM on 11/26/2015.
 */
public class LayerAbout extends Layer {

    public LayerAbout(int x, int y, int width, int height) {
        super(x,y,width,height);
    }

    public void paint(Graphics g) {
        this.createWindow(g);
        this.centerDrawImage(Img.SIGN, g);
    }
}
