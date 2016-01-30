package ui;

import java.awt.*;

/**
 * Created by StReaM on 11/26/2015.
 */
public class LayerDataBase extends LayerDataMother {

//    private int slot_width = 0;
    public LayerDataBase(int x, int y, int width, int height) {
        super(x,y,width,height);

 //       slot_width = this.width - 2 * PADDING;
    }

    public void paint(Graphics g) {
        this.createWindow(g);
        this.showData(Img.DB, this.dto.getDbRecord(), g);

    }
}
// 43:03