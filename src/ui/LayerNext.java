package ui;

import java.awt.*;

/**
 * Created by StReaM on 11/26/2015.
 */
public class LayerNext extends Layer {


    public LayerNext(int x, int y, int width, int height) {
        super(x,y,width,height);
    }

    public void paint(Graphics g) {
        this.createWindow(g);
        // 如果是开始状态才绘制下一个方块
        if (this.dto.isStart()) {
            this.centerDrawImage(Img.NEXT_BRICK[this.dto.getNext_brick()], g);
        }
    }


}
