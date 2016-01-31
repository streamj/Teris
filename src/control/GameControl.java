package control;

import config.DataInterfaceConfig;
import config.GameConfig;
import dao.Data;

import dto.GameDto;
import service.GameService;
import ui.FrameGame;
import ui.PanelGame;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by StReaM on 11/29/2015.
 */
public class GameControl {
    /**
     * 游戏界面层
     * 主要就是为了调用 repaint 刷新游戏
     */
    private PanelGame panelGame;
    /**
     * 游戏逻辑层
     */
    private GameService gameService;

    private Map<Integer, Method> action;

    private Thread gameThread = null;

    private GameDto dto = null;



    /**
     * 数据访问接口 A B
     */
    private Data dataA;
    private Data dataB;


    public GameControl() {

        /**
         * 生成一个数据源，这个数据源将会不断往下传输
         */
        this.dto = new GameDto();

        /**
         * 创建游戏服务
         */
        this.gameService = new GameService(dto);

        // 从数据库接口获得数据库记录
        DataInterfaceConfig data_cfg_a = GameConfig.getDataConfig().getDataA();
        DataInterfaceConfig data_cfg_b = GameConfig.getDataConfig().getDataA();

        this.dataA = createDataObject(data_cfg_a);

        // 从磁盘接口获得磁盘记录
        this.dataB = createDataObject(data_cfg_b); // should be disk
        this.dto.setDbRecord(dataA.loadData());
        this.dto.setDiskRecord(dataB.loadData());

        /**
         * 创建游戏面板
         * 如果 dto 产生变化，他就会根据 dto 重绘
         */
        this.panelGame = new PanelGame(dto, this);

        action = new HashMap<Integer,Method>();
        try {
            action.put(27,this.gameService.getClass().getMethod("esc"));
            action.put(37,this.gameService.getClass().getMethod("left"));
            action.put(38,this.gameService.getClass().getMethod("up"));
            action.put(39,this.gameService.getClass().getMethod("right"));
            action.put(40,this.gameService.getClass().getMethod("down"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 生成一个 frameGame 也就是游戏主窗口，稍微读一下配置，设置一下参数
         * 然后把 panelGame 传给 frameGame 就开始画图 生成游戏并且监听键盘行为
         * 之后除了退出之外的大部分玩家行为都会传给 playerControl
         */
        FrameGame frameGame = new FrameGame(panelGame);

    }

    private Data createDataObject(DataInterfaceConfig data_cfg) {
        try {
            Class<?> cls = Class.forName(data_cfg.getClassName());

            Constructor<?> ctr = cls.getConstructor(HashMap.class);
            return (Data)ctr.newInstance(data_cfg.getParam());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void actionByKeyCode(int keyCode) {

        try {
            if (!this.action.containsKey(keyCode)) {
                return;
            }
            this.action.get(keyCode).invoke(this.gameService);

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.panelGame.repaint();
    }

    /**
     * 开始按钮的一系列方法
     */
    public void start() {
        this.panelGame.buttonStatus(false);
        this.gameService.startGame();

        // create thread
        this.gameThread = new MainThread();
        this.gameThread.start();
        this.panelGame.repaint();
    }

    public void afterGameOver(){

    }

    private class MainThread extends Thread {
        // 内部类
        @Override
        public void run() {
            while (true) {
                if (!dto.isStart()) {
                    break;
                }

                try {
                    Thread.sleep(500); // sleep first

                    if (dto.isPause()) {
                        continue;
                    }
                    gameService.mainAction();
                    panelGame.repaint();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}