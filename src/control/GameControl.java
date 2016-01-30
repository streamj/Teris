package control;

import config.DataInterfaceConfig;
import config.GameConfig;
import dao.Data;

import service.GameService;
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

    /**
     * 数据访问接口 A B
     */
    private Data dataA;
    private Data dataB;


    public GameControl(PanelGame panelGame, GameService gameService) {
        this.panelGame = panelGame;
        this.gameService = gameService;

        action = new HashMap<Integer,Method>();
        try {
            action.put(37,this.gameService.getClass().getMethod("left"));
            action.put(38,this.gameService.getClass().getMethod("up"));
            action.put(39,this.gameService.getClass().getMethod("right"));
            action.put(40,this.gameService.getClass().getMethod("down"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 从数据库接口获得数据库记录
        DataInterfaceConfig data_cfg_a = GameConfig.getDataConfig().getDataA();
        DataInterfaceConfig data_cfg_b = GameConfig.getDataConfig().getDataA();

        this.dataA = createDataObject(data_cfg_a);
        // 从磁盘接口获得磁盘记录
        this.dataB = createDataObject(data_cfg_b); // should be disk
        this.gameService.setRecordDataBase(dataA.loadData());
        this.gameService.setRecordDisk(dataB.loadData());
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

    /**
     * 每动一步，panelGame 都会 repaint
     */
//    public void up() {
//        this.gameService.up();
//        this.panelGame.repaint();
//    }
//
//    public void down() {
//        this.gameService.down();
//        this.panelGame.repaint();
//    }
//
//    public void left() {
//        this.gameService.left();
//        this.panelGame.repaint();
//    }
//
//    public void right() {
//        this.gameService.right();
//        this.panelGame.repaint();
//    }

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
}