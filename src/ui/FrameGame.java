package ui;

import config.FrameConfig;
import config.GameConfig;

import javax.swing.JFrame;

/**
 * Created by StReaM on 11/25/2015.
 */
public class FrameGame extends JFrame{
    /**
     * 只有一个构造函数...
     * @param panelGame
     */
    public FrameGame(PanelGame panelGame) {
        // 获得游戏配置
        FrameConfig fCfg = GameConfig.getFrameConfig();


        this.setTitle(fCfg.getTitle());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(fCfg.getWidth(),fCfg.getHeight()); // 要算上标题栏的像素
        this.setResizable(false);

        // 获得显示器大小属性,然后设置居中
        FrameUtil.setCenter(this);

        this.setContentPane(panelGame);

        this.setVisible(true);
    }
}
