package ui;

import config.GameConfig;

import java.awt.*;

/**
 * Created by StReaM on 11/26/2015.
 */
public class LayerScore extends Layer {

    // RM_IMG.getHeight(null);

    private static final int MAX_SCORE_BIT = 5;

    /**
     * 升级需要的消除的行数
     */
    private static final double LVL_UP_LINES = GameConfig.getSystemConfig().getLvlUpLine();


    /**
     * the will all use this score_x
     */
    private int score_x = 0;
    /**
     * exp slot's y and width, using score_x as x
     */
    private int slot_y = 0;
    private int slot_width = 0;

    public LayerScore(int x, int y, int width, int height) {
        super(x, y, width, height);
        // initialize show score x
        score_x = this.width - NB_W * MAX_SCORE_BIT - PADDING;
        slot_y = Img.SC.getHeight(null) + 3 * PADDING + Img.RM.getHeight(null);
        slot_width = this.width - 2 * PADDING;
    }

    public void paint(Graphics g) {
        this.createWindow(g);

        g.drawImage(Img.SC, this.x + PADDING, this.y + PADDING, null);
        // draw score
        this.drawNumber(score_x, PADDING, this.dto.getRealtimeScore(), MAX_SCORE_BIT, g);

        g.drawImage(Img.RM, this.x + PADDING, this.y + PADDING * 2 + Img.RM.getHeight(null), null);
        // draw removed line
        this.drawNumber(score_x, Img.RM.getHeight(null) + PADDING * 2, this.dto.getRealtimeRemoveLine(), MAX_SCORE_BIT, g);
        // draw exp value slot

//        int h = 32;
//        g.setColor(Color.black);
//        g.fillRect(this.x + PADDING, this.y + slot_y, slot_width,h);
//        g.setColor(Color.white);
//        g.fillRect(this.x + PADDING + 1, this.y + slot_y + 1, slot_width-2, h-2);
//        g.setColor(Color.red);
//        g.fillRect(this.x + PADDING + 2, this.y + slot_y + 2, slot_width -4, h - 4);
//        g.setColor(Color.green);
//
        /**
         * 已经消除的行数
         */
        int rmv_line = this.dto.getRealtimeRemoveLine();
//        double green_width = (rmv_line % LVL_UP_LINES) / LVL_UP_LINES * (slot_width - 4);
//        g.setColor(this.getCurColor(rmv_line % LVL_UP_LINES, LVL_UP_LINES));
//        g.fillRect(this.x + PADDING + 2, this.y + slot_y + 2,(int)green_width, h - 4);
        double lacked_lines = rmv_line % LVL_UP_LINES;
        this.drawRect(slot_y, slot_width,"next level", null, lacked_lines, LVL_UP_LINES, g);
    }
    // Img.RECT


    @Deprecated
    private Color getCurColor(double hp, double maxhp) {
        int colorR = 0;
        int colorG = 255;
        int colorB = 0;
        double hpHalf = maxhp / 2;
        if (hp > hpHalf) {
            colorR = 255 - (int) ((hp - hpHalf) / (maxhp / 2) * 255);
            colorG = 255;
        } else {
            colorR = 255;
            colorG = (int) (hp / (maxhp) / 2 * 255);
        }

        return new Color(colorR, colorG, colorB);
    }
}
