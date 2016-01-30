package ui;

import config.GameConfig;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by StReaM on 12/2/2015.
 */
public class Img {
    private Img () {
    }

    public static Image SHADOW = new ImageIcon("graphic/game/shadow.png").getImage();
    public static Image WINDOW = new ImageIcon("graphic/window/Window.png").getImage();

    public static  Image NB = new ImageIcon("graphic/string/num.png").getImage();

    public static Image DB = new ImageIcon("graphic/string/db.png").getImage();

    public static Image DISK = new ImageIcon("graphic/string/disk.png").getImage();

    public static ImageIcon START_BUTTON = new ImageIcon("graphic/string/start.png");

    public static ImageIcon CONFIG_BUTTON = new ImageIcon("graphic/string/config.png");


    public static  Image[] NEXT_BRICK;

    public static List<Image> BG_LIST;

    static {
        NEXT_BRICK = new Image[GameConfig.getSystemConfig().getBrick_type().size()];
        for (int i = 0; i< NEXT_BRICK.length; i++) {
            NEXT_BRICK[i] = new ImageIcon("graphic/game/" + i + ".png").getImage();
        }

        File dir = new File("graphic/background");
        File[] files = dir.listFiles();
        BG_LIST = new ArrayList<Image>();
        for (File file: files) {
            if (file.isDirectory()) {
                continue;
            }
            BG_LIST.add(new ImageIcon(file.getPath()).getImage());
        }
    }
    /**
     * 签名
     */
    public static Image RECT = new ImageIcon("graphic/window/rect.png").getImage();
    public static Image SIGN = new ImageIcon("graphic/string/sign.png").getImage();
    /**
     * 分数
     */
    public static Image SC = new ImageIcon("graphic/string/point.png").getImage();
    /**
     * 消除行
     */
    public static Image RM= new ImageIcon("graphic/string/rmline.png").getImage();

    public static Image ACT = new ImageIcon("graphic/game/rect.png").getImage();

    //public static Image BG = new ImageIcon("graphic/background/bg7.jpg").getImage();

    public static  Image LV = new ImageIcon("graphic/string/level.png").getImage();
}
