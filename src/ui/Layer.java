package ui;

import config.FrameConfig;
import config.GameConfig;
import dto.GameDto;

import java.awt.*;

/**
 * Created by StReaM on 11/26/2015.
 * 绘制窗口
 */
abstract public class Layer {

    protected static final int PADDING;
    protected static final int WINDOW_SIZE;

    protected static final Font DEFONT = new Font("Arial", Font.PLAIN, 20);

    static {
        // 获得配置
        FrameConfig fCfg = GameConfig.getFrameConfig();
        PADDING = fCfg.getPadding();
        WINDOW_SIZE = fCfg.getWindowSize();
    }

    /**
     * 窗口图片高度和宽度
     */
    private static int IMGW = Img.WINDOW.getWidth(null);
    private static int IMGH = Img.WINDOW.getHeight(null);

    /**
     * 数字图片高度和宽度
     */
    protected static final int NB_W = Img.NB.getWidth(null) / 10;
    protected static final int NB_H = Img.NB.getHeight(null);

    /**
     * slot
     */
    protected static final int RECT_HEIGHT = Img.RECT.getHeight(null);
    protected static final int RECT_WIDTH = Img.RECT.getWidth(null);
    /**
     * 窗口左上角x坐标
     */
    protected int x;
    /**
     * 窗口左上角y坐标
     */
    protected int y;
    /**
     * 窗口宽度
     */
    protected int width;
    /**
     * 窗口高度
     */
    protected int height;

    /**
     * data transfer object 数据传输对象，来源于设计模式
     */
    protected GameDto dto = null;

    protected Layer(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * 绘制窗口方法
     */
    protected void createWindow(Graphics g) {
        // 左上
        g.drawImage(Img.WINDOW, x, y, x + WINDOW_SIZE, y + WINDOW_SIZE, 0, 0, WINDOW_SIZE, WINDOW_SIZE, null);
        // 前面是 panel 坐标，后面是图片坐标
        // 中上
        g.drawImage(Img.WINDOW, x + WINDOW_SIZE, y, x + width - WINDOW_SIZE, y + WINDOW_SIZE, WINDOW_SIZE, 0, IMGW - WINDOW_SIZE, WINDOW_SIZE, null);
        // 右上
        g.drawImage(Img.WINDOW, x + width - WINDOW_SIZE, y, x + width, y + WINDOW_SIZE, IMGW - WINDOW_SIZE, 0, IMGW, WINDOW_SIZE, null);
        // 左中
        g.drawImage(Img.WINDOW, x, y + WINDOW_SIZE, x + WINDOW_SIZE, y + height - WINDOW_SIZE, 0, WINDOW_SIZE, WINDOW_SIZE, IMGH - WINDOW_SIZE, null);
        // 右中
        g.drawImage(Img.WINDOW, x + width - WINDOW_SIZE, y + WINDOW_SIZE, x + width, y + height - WINDOW_SIZE, IMGW - WINDOW_SIZE, WINDOW_SIZE, IMGW, IMGH - WINDOW_SIZE, null);
        // 左下
        g.drawImage(Img.WINDOW, x, y + height - WINDOW_SIZE, x + WINDOW_SIZE, y + height, 0, IMGH - WINDOW_SIZE, WINDOW_SIZE, IMGH, null);
        // 下中
        g.drawImage(Img.WINDOW, x + WINDOW_SIZE, y + height - WINDOW_SIZE, x + width - WINDOW_SIZE, y + height, WINDOW_SIZE, IMGH - WINDOW_SIZE, IMGW - WINDOW_SIZE, IMGH, null);
        // 右下
        g.drawImage(Img.WINDOW, x + width - WINDOW_SIZE, y + height - WINDOW_SIZE, x + width, y + height, IMGW - WINDOW_SIZE, IMGH - WINDOW_SIZE, IMGW, IMGH, null);
    }

    public void setDto(GameDto dto) {
        this.dto = dto;
    }

    /**
     * 刷新游戏具体内容
     * 抽象方法，让子类自己去实现
     * @param g 画笔
     */
    abstract public void paint(Graphics g);

    /**
     *
     * @param x      左上角的x坐标
     * @param y      左上角的y坐标
     * @param num    传入的分数的具体数字
     * @param length 数字位数
     * @param g
     */
    protected void drawNumber(int x, int y, int num, int length, Graphics g) {
        String strNum = Integer.toString(num);
        for (int i = 0; i < length; i++) {
            if (length - i <= strNum.length()) {
                int idx = i - length + strNum.length();
                int singleNum = strNum.charAt(idx) - '0';
                g.drawImage(Img.NB,
                        // 多乘一个数字宽度，不然打出来的数字都会叠在一起
                        this.x + x + NB_W * i, this.y + y,
                        this.x + x + NB_W * (i + 1), this.y + y + NB_H,
                        singleNum * NB_W, 0,
                        (singleNum + 1) * NB_W, NB_H, null);
            }
        }
    }

    /**
     * 居中绘图
     * @param img
     * @param g
     */
    protected void centerDrawImage(Image img, Graphics g) {
        int imgW = img.getWidth(null);
        int imgH = img.getHeight(null);

        g.drawImage(img,this.x + (this.width - imgW >> 1),this.y +(this.height - imgH >> 1),null);
    }
    /**
     *
     * @param sy slot's y
     * @param sw slot's width
     * @param title
     * @param number
     * @param value
     * @param maxValue
     * @param g
     */

    protected void drawRect(int sy, int sw,String title, String number, double value, double maxValue, Graphics g) {

        int rect_x = this.x + PADDING;
        int rect_y = this.y + sy;
        // 绘制背景
        g.setColor(Color.black);
        g.fillRect(rect_x, rect_y, sw, RECT_HEIGHT + 4);
        g.setColor(Color.white);
        g.fillRect(rect_x + 1, rect_y + 1, sw - 2, RECT_HEIGHT + 2);
        g.setColor(Color.black);
        g.fillRect(rect_x + 2, rect_y + 2, sw - 4, RECT_HEIGHT);

        // 绘制经验槽
        double percent = value / maxValue;
        int dynamicWidth = (int) (percent * (sw - 4));

        // color
        int subIdx = (int) (percent * RECT_WIDTH) -1;

        g.drawImage(Img.RECT,
                rect_x + 2, rect_y + 2,
                rect_x + 2 +dynamicWidth, rect_y + 2 + RECT_HEIGHT,
                subIdx, 0, subIdx + 1, RECT_HEIGHT,
                null);
        g.setColor(Color.white);
        g.setFont(DEFONT);
        g.drawString(title, rect_x + 10, rect_y + 20); // string 是从窗口左下角开始算，真他妈傻逼

        if (number != null) {
            g.drawString(number, rect_x + 232, rect_y + 22);
        }

    }
}
