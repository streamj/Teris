package util;

import javax.swing.*;
import java.awt.*;

/**
 * Created by StReaM on 12/19/2015.
 */
public class FrameUtil {
    // 窗口居中
    public static void setCenter(JFrame jf) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        int x = (screen.width - jf.getWidth()) >> 1;
        int y = (screen.height - jf.getHeight()) >> 1;
        jf.setLocation(x,y);
    }
}
