package entity;

import config.GameConfig;

import java.awt.*;
import java.util.List;
/**
 * 方块类
 * Created by StReaM on 11/29/2015.
 */
public class GameAct {

    /**
     * 成员函数
     * 使用 Point 类来构建游戏主界面的坐标系
     * 一个 Point 类型就是一组(x,y) 坐标
     */
    private Point[] actPoints = null;

    /**
     * 成员函数
     *  the serial number of brick
     */
    private int type_code;

    private static int MIN_X = GameConfig.getSystemConfig().getMin_x();
    private static int MAX_X = GameConfig.getSystemConfig().getMax_x();
    private static int MIN_Y = GameConfig.getSystemConfig().getMin_y();
    private static int MAX_Y = GameConfig.getSystemConfig().getMax_y();

    private final static List<Point[]> BRICK_TYPE = GameConfig.getSystemConfig().getBrick_type();

    public GameAct(int type_code) {
        this.init(type_code);
    }

    public void init(int type_code) {
        this.type_code = type_code;
        // 先得到那个方块的形状
        Point[] points = BRICK_TYPE.get(type_code);

        // new 一个新方块对象，如果引用静态对象出问题
        this.actPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            actPoints[i] = new Point(points[i].x, points[i].y);
        }
    }

    public Point[] getActPoint() {
        return actPoints;
    }

    /**
     *
     * @param moveX
     * @param moveY
     * @param gameMap 这个参数，要传给 isDead 方法用来判断边界条件
     * @return
     */
    public boolean move(int moveX, int moveY, boolean[][] gameMap) {
        // 第一次判断是假动作，仅仅是判断是否会出界
        for (int i = 0; i < actPoints.length; i++) {
            int newX = actPoints[i].x + moveX;
            int newY = actPoints[i].y + moveY;
            if (this.isDeadEnd(newX, newY, gameMap)) {
                return false;
            }
        }
        // 如果不会出界，就进行移动
        for (int i = 0; i < actPoints.length; i++) {
            actPoints[i].x += moveX ;
            actPoints[i].y += moveY ;
        }

        return true;
    }

    /**
     * 选择顺时针
     *          A.x = O.y + O.x - B.y
     *          A.y = O.y - O.x + B.x
     */

    public void spin(boolean[][] gameMap) {

        /**
         * square doesn't need spin
         */
        if (this.type_code == 4) {
            return;
        }
        for (int i = 1; i < actPoints.length; i++) {
            int newX = actPoints[0].y + actPoints[0].x - actPoints[i].y;
            int newY = actPoints[0].y - actPoints[0].x + actPoints[i].x;
            if (this.isDeadEnd(newX, newY, gameMap)) {
                return;
            }
        }

        for (int i = 1; i < actPoints.length; i++) {
            int newX = actPoints[0].y + actPoints[0].x - actPoints[i].y;
            int newY = actPoints[0].y - actPoints[0].x + actPoints[i].x;
            actPoints[i].x = newX;
            actPoints[i].y = newY;
        }

    }

    private boolean isDeadEnd(int x, int y, boolean[][] gameMap) {
        /**
         * 判断是否在 Point 坐标系内
         * 多判断一下，是否碰到堆积的对象
         * 如果 gameMap[x][y] 是 true 就表示碰到堆积的对象
         */
        return x < MIN_X || x > MAX_X || y < MIN_Y || y > MAX_Y || gameMap[x][y];
    }

    public int getType_code() {
        return type_code;
    }
}
