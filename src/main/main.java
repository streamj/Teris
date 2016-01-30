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
         * 把刚才的 dto 传给他
         * gameService 有一系列方法
         * 初始化的时候，用随机数生成器生成一个数字,根据这个数字, 用 gameAct 类生成一个方块的信息
         * 把这个方块信息，记录到 dto
         * 到这里为止，貌似只产生了信息，并没有绘制
         *
         * ... 还要等待 gameControl 初始化
         *
         * 等到 gameControl 初始化完毕之后，gameControl 就可以调用 gameService 的方法了
         * 但是，他可能没想到，gameService 自己也是个二道贩子，他大部分也是在通过 dto 来调用 gameAct 的方法
         *
         * ... 还要等待 playerControl 初始化
         *
         * playerControl->gameControl->gameService 了，他就可以对 gameAct 采取一些行动
         */
        GameService gameService = new GameService(dto);

        /**
         * 创建游戏面板
         * 把 dto 再传给他，这个时候，dto 的信息略有改变，起码有一个新方块的信息了
         * 反正这帮孙子共享了这个 dto
         * panelGame 这孙子从 xml 文件里开始读取配置, 并且利用反射创建 Layer对象并加入一个列表
         * 还重写了一个方法用于绘制 Layer
         * 还偷偷给这些 Layer 都共享了这个 dto
         *
         * ...
         *
         * 如果 dto 产生变化，他就会根据 dto 重绘
         */
        PanelGame panelGame = new PanelGame(dto);


        /**
         * 创建游戏控制器(连接游戏面板与游戏服务)
         * 这货有几个接口用于连接本地磁盘数据和数据库数据
         * 他的方法，也只是分别调用了 gameService 和 panelGame 的方法
         *
         * ...他本身不能干嘛，还得等 playerControl
         *
         * playerControl 来消息了，他就可以采取行动，主要就是调用 gameService 和 panelGame 重绘图
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
         * 都弄好之后，把 panelGame 传给 frameGame 就开始画图 生成游戏并且监听键盘行为
         * 之后除了退出之外的大部分玩家行为都会传给 playerControl
         */
        FrameGame frameGame = new FrameGame(panelGame);
    }
}
