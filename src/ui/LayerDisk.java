package ui;

import java.awt.*;

/**
 * Created by StReaM on 11/26/2015.
 */
public class LayerDisk extends LayerDataMother {

    public LayerDisk(int x, int y, int width, int height) {
        super(x,y,width,height);
    }

    public void paint(Graphics g) {
        this.createWindow(g);
        this.showData(Img.DISK, this.dto.getDiskRecord(), g);
    }

}
