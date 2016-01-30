package ui;

import java.awt.*;

/**
 * Created by StReaM on 11/26/2015.
 */
public class LayerButton extends Layer {
    public LayerButton(int x, int y, int width, int height) {
        super(x,y,width,height);
    }

    public void paint(Graphics g) {
        this.createWindow(g);
    }
}
