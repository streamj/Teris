package ui;

import config.GameConfig;
import entity.GameAct;

import java.awt.*;

/**
 * Created by StReaM on 11/26/2015.
 */
public class LayerGame extends Layer {

    private static final int ACT_SIZE = 32;
    // 用于 shadow 的两个常量
    private static final int LEFT_SIDE = 0;
    private static final int RIGHT_SIDE = 9;
    private final int LOSE_IDX = GameConfig.getFrameConfig().getLoseIdx();

    public LayerGame(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void paint(Graphics g) {
        this.createWindow(g);

        GameAct act = this.dto.getGameAct();
        if (act != null) {
            // 获得方块组合的集合
            // dto 继承自 Layer
            Point[] points = this.dto.getGameAct().getActPoint();
            // 绘制活动方块
            this.drawMainAct(g,points);
        }

        // 绘制地图
        this.drawMap(g);
        // pause game
        if(this.dto.isPause()) {
            this.centerDrawImage(Img.PAUSE,g);
        }
    }

    /**
     * 封装了一个打印方块的方法
     *
     * @param x      需要打印方块的 x 坐标
     * @param y      需要打印方块的 y 坐标
     * @param imgIdx 颜色目录，从0开始
     * @param g
     */
    private void drawBrick(int x, int y, int imgIdx, Graphics g) {
        imgIdx = this.dto.isStart() ? imgIdx : LOSE_IDX;

        g.drawImage(Img.ACT,
                // this 代表这个 Layer
                // thjs.x 代表窗口左上角坐标
                // this.y 代表窗口右上角坐标
                this.x + x * ACT_SIZE + 7,
                this.y + y * ACT_SIZE + 7,
                this.x + x * ACT_SIZE + ACT_SIZE + 7,
                this.y + y * ACT_SIZE + ACT_SIZE + 7,
                imgIdx * ACT_SIZE, 0, (imgIdx + 1) * ACT_SIZE, ACT_SIZE, null);

    }

    /**
     * @param b
     * @param points
     */
    private void drawShadow(boolean b, Point[] points, Graphics g) {
        if (!b) {
            return;
        }
        int shadow_left_X = RIGHT_SIDE, shadow_right_X = LEFT_SIDE;
        for (Point p : points) {
            shadow_left_X = p.x < shadow_left_X ? p.x : shadow_left_X;
            shadow_right_X = p.x > shadow_right_X ? p.x : shadow_right_X;
        }
        g.drawImage(Img.SHADOW,
                this.x + WINDOW_SIZE + shadow_left_X * ACT_SIZE,
                this.y + WINDOW_SIZE,
                (shadow_right_X - shadow_left_X + 1) * ACT_SIZE,
                this.height - WINDOW_SIZE * 2, null);

    }

    private void drawMainAct(Graphics g, Point[] points) {
        // 方块类型编号 1-6
        int type_code = this.dto.getGameAct().getType_code();

        // shadow
        this.drawShadow(true, points, g);

        // 打印方块
        for (int i = 0; i < points.length; i++) {
            this.drawBrick(points[i].x, points[i].y, type_code + 1, g);
        }
    }

    private void drawMap(Graphics g) {
        // 打印地图
        boolean[][] map = this.dto.getGameMap();
        // 计算当前堆积的颜色

        int lv = this.dto.getLevel();
        //  根据等级，改编堆积颜色
        int imgIdx = lv == 0 ? 0 : (lv - 1) % 7 + 1;

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y]) { // 如果是 true 就打印一个堆积
                    this.drawBrick(x, y,imgIdx, g);
                }
            }
        }
    }
}
