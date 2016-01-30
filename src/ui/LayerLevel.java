package ui;

import java.awt.*;

/**
 * Created by StReaM on 11/26/2015.
 */
public class LayerLevel extends Layer {

    public LayerLevel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void paint(Graphics g) {
        this.createWindow(g);
        // 居中点
        int center = (this.width - Img.LV.getWidth(null)) / 2;
        g.drawImage(Img.LV, this.x + center, this.y + PADDING, null);
        this.drawNumber(center, 60, this.dto.getLevel(), 2, g);
    }

}
