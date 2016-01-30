package ui;

import java.awt.*;

/**
 * Created by StReaM on 11/26/2015.
 */
public class LayerNext extends Layer {

//    private static  Image[] NEXT_BRICK;
//
//    static {
//        NEXT_BRICK = new Image[7];
//        for (int i = 0; i< NEXT_BRICK.length; i++) {
//            NEXT_BRICK[i] = new ImageIcon("graphic/game/" + i + ".png").getImage();
//        }
//    }
    public LayerNext(int x, int y, int width, int height) {
        super(x,y,width,height);
    }

    public void paint(Graphics g) {
        this.createWindow(g);
        this.centerDrawImage(Img.NEXT_BRICK[this.dto.getNext_brick()], g);

    }


}
