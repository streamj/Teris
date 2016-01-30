package ui;

import config.FrameConfig;
import config.GameConfig;
import config.LayerConfig;

import control.GameControl;
import control.PlayerControl;
import dto.GameDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by StReaM on 11/25/2015.
 */
public class PanelGame extends JPanel{
    /**
     * 图层列表，一系列 Layer 的派生类
     */
    private List<Layer> layers = null;
    private GameDto dto = null;
    // get method hell...
    private  static final int BTN_SIZE_WIDTH = GameConfig.getFrameConfig().getButtonConfig().getButtonWidth();
    private  static final int BTN_SIZE_Height = GameConfig.getFrameConfig().getButtonConfig().getButtonHeight();

    private JButton startButton;

    private JButton settingButton;

    private GameControl gameControl = null;
    /**
     * 默认构造函数，给他传个dto
     * @param dto
     */
    public PanelGame(GameDto dto) {
        this.dto = dto;
        //设为自由布局
        this.setLayout(null);
        // 初始化组件
        initComponent();
        // 初始化层
        initLayer();
    }

    /**
     * 安装玩家控制器
     * @param playerControl
     */
    public void setPlayerControl(PlayerControl playerControl) {
        this.addKeyListener(playerControl);
    }

    public void setGameControl(GameControl gameControl) {
        this.gameControl = gameControl;
    }

    private void initComponent() {
        this.startButton = new JButton(Img.START_BUTTON);
        startButton.setBounds(
                GameConfig.getFrameConfig().getButtonConfig().getStartX(),
                GameConfig.getFrameConfig().getButtonConfig().getStartY(),
                BTN_SIZE_WIDTH, BTN_SIZE_Height
        );
        // 给开始按钮增加事件监听
        this.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameControl.start();
            }
        });
        this.add(startButton); // 添加按钮到面板
        this.settingButton = new JButton(Img.CONFIG_BUTTON);
        settingButton.setBounds(
                GameConfig.getFrameConfig().getButtonConfig().getSettingX(),
                GameConfig.getFrameConfig().getButtonConfig().getSettingY(),
                BTN_SIZE_WIDTH, BTN_SIZE_Height
        );
        this.add(settingButton); // 同上
    }

    /**
     * 逐个初始化图层
     */
    private void initLayer() {
        try {
            // 获得配置文件
            FrameConfig fCfg = GameConfig.getFrameConfig();

            // 获得 Layer 的配置文件，并放入列表
            List<LayerConfig> layerscfg = fCfg.getLayersConfig();
            this.layers = new ArrayList<Layer>(layerscfg.size());

            for (LayerConfig layercfg : layerscfg) {

                // 利用反射创建对象
                Class<?> cls = Class.forName(layercfg.getClassName());
                Constructor<?> ctr = cls.getConstructor(int.class, int.class, int.class, int.class);
                Layer layer = (Layer)ctr.newInstance(
                        layercfg.getX(),
                        layercfg.getY(),
                        layercfg.getLayer_width(),
                        layercfg.getLayer_height()
                );
                // set game data object
                layer.setDto(this.dto);
                layers.add(layer);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 控制按钮状态
     * @param isClicked
     */
    public void buttonStatus(boolean isClicked) {
        this.startButton.setEnabled(isClicked);
        this.settingButton.setEnabled(isClicked);
    }

    /**
     * 这个方法，在哪里被调用？
     * 推测 JFrame 会调用这个方法
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        // 调用基类方法
        super.paintComponent(g);

        for(int i = 0; i < layers.size(); i++) {
            // 终于被老子找到 paint 的调用了，太他妈隐蔽了
           layers.get(i).paint(g);
        }

        // 返回焦点
        this.requestFocus();

    }
}
