package main;


import control.GameControl;
import control.PlayerControl;
import dto.GameDto;

import service.GameService;
import ui.FrameGame;
import ui.PanelGame;

/**
 * Created by StReaM  11/25/2015.
 */
public class main {
    public static void main(String[] args) {
        /**
         * 生成一个数据源，这个数据源将会不断往下传输
         */
        GameDto dto = new GameDto();
        /**
         * 创建游戏服务
         */
        GameService gameService = new GameService(dto);

        /**
         * 创建游戏面板
         * 如果 dto 产生变化，他就会根据 dto 重绘
         */
        PanelGame panelGame = new PanelGame(dto);

        /**
         * 创建游戏控制器(连接游戏面板与游戏服务)
         * 有几个接口用于连接本地磁盘数据和数据库数据
         * 他的方法，也只是分别调用了 gameService 和 panelGame 的方法
         * 本身并不做什么，需要等待 playerControl 的消息
         * 一旦 playerControl 来消息了，他就可以采取行动，主要就是调用 gameService 和 panelGame 重绘图
         */
        GameControl gameControl = new GameControl(panelGame, gameService);

        /**
         * 创建玩家控制器(连接游戏控制器)
         * 这个很简单，就是一个监听键盘事件, 而且只接受上下左右
         * 监听到了之后，他什么也不做，只是调用 gameControl
         */
        PlayerControl playerControl = new PlayerControl(gameControl);

        /**
         * 安装玩家控制器
         * 这个时候，又绕回到了 panelGame, 他要一个玩家控制器
         */
        panelGame.setGameControl(playerControl);

        /**
         * 生成一个 frameGame 也就是游戏主窗口，稍微读一下配置，设置一下参数
         * 然后把 panelGame 传给 frameGame 就开始画图 生成游戏并且监听键盘行为
         * 之后除了退出之外的大部分玩家行为都会传给 playerControl
         */
        FrameGame frameGame = new FrameGame(panelGame);
    }
}
