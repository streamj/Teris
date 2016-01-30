package ui;

import config.GameConfig;
import dto.Player;

import java.awt.*;
import java.util.List;

/**
 * Created by StReaM on 12/6/2015.
 */
public abstract class  LayerDataMother extends Layer {

    protected static final int MAX_ROW = GameConfig.getDataConfig().getMaxRow();

    /**
     * 起始坐标Y
     */
    protected static  int START_Y = 0;
    /**
     * 间距
     */
    protected static int DISTANCE = 0;
    private int slot_width = 0;


    public LayerDataMother(int x, int y, int width, int height) {
        super(x, y, width, height);
        DISTANCE = (this.height - (RECT_HEIGHT + 4) * 5 - PADDING * 2 - Img.DB.getHeight(null))/MAX_ROW;
        START_Y =  PADDING + Img.DB.getHeight(null) + DISTANCE;

        slot_width = this.width - 2 * PADDING;
    }

    @Override
    abstract public void paint(Graphics g);
    /**
     *
     * @param imgTitle
     * @param players
     * @param g
     */

    public void showData(Image imgTitle, List<Player> players, Graphics g) {
        g.drawImage(imgTitle,this.x + PADDING, this.y + PADDING,null);

        int realtime_score = this.dto.getRealtimeScore();
        for(int i = 0; i < MAX_ROW; i++) {
            Player pla = players.get(i);
            int recordScore = pla.getScore();
            double percent = (double)realtime_score/pla.getScore();
            percent = percent > 1 ? 1.0 : percent;
            String score = recordScore == 0 ? null : Integer.toString(recordScore);
            this.drawRect(START_Y + i * (RECT_HEIGHT + DISTANCE), slot_width, pla.getName(),score,
                    realtime_score, pla.getScore(), g);
        }
    }
}
