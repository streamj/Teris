package service;

import config.GameConfig;
import dto.GameDto;
import dto.Player;
import entity.GameAct;

import java.awt.*;
import java.util.*;

/**
 * Created by StReaM on 11/29/2015.
 */
public class GameService {

    private GameDto dto;

    private Random random = new Random(); // 这个又没初始化

    private static final int MAX_TYPE_CODE = GameConfig.getSystemConfig().getBrick_type().size()-1;

    /**
     * 升级需要的消除的行数
     */
    private static final double LVL_UP_LINES = GameConfig.getSystemConfig().getLvlUpLine();
    private static final Map<Integer, Integer> PLUS_POINT = GameConfig.getSystemConfig().getScore_detail();

    /**
     * 构造函数，传入一个 dto
     *
     * @param dto
     */

    public GameService(GameDto dto) {
        this.dto = dto;

    }

    public void startMainThread() {
        // random generate a next shape
        this.dto.setNext_brick(random.nextInt(MAX_TYPE_CODE));
        // generate  a random brick shape
        this.dto.setGameAct(new GameAct(random.nextInt(MAX_TYPE_CODE)));
        this.dto.setStart(true);
    }

    /**
     * 旋转90度，笛卡尔坐标系，和屏幕坐标系相反，因为一个（0,0）在左上角，一个在左下角
     * 假设 O 中心，A after B before
     * 顺时针
     * B.x = O.x - O.y + A.y
     * B.y = O.x + O.y + A.x
     * 逆时针
     * A.x = O.y + O.x - B.y
     * A.y = O.y - O.x + B.x
     * A (4,2) B(3,-1) O(2,1)
     */

    public void up() {
        this.dto.getGameAct().spin(this.dto.getGameMap());

    }

    public void down() {
        // 需要判断是否下降成功, 能下降，就仅仅做一下下降，然后完事了
        boolean canDown = this.dto.getGameAct().move(0, 1, this.dto.getGameMap());
        if (canDown) {
            return;
        }
        // 如果不能再向下, 就开始堆积
        // 首先从 dto 取得 gameMap
        boolean[][] map = this.dto.getGameMap();

        // 其次从 dto 取得当前方块的坐标
        // 每个数组元素都是 (x,y)
        Point[] act = this.dto.getGameAct().getActPoint();

        // 将这几个坐标都设置为 true 表示堆积
        for (int i = 0; i < act.length; i++) {
            map[act[i].x][act[i].y] = true;
        }

        int exp = this.growExp();
        if(exp > 0) {
            this.growScore(exp);
        }


        // generate another brick shape
        // 从 dto 里获得 gameAct 对象, 然后这个对象重新初始化一个方块
        this.dto.getGameAct().init(this.dto.getNext_brick());


        //System.out.println(this.dto.getNext_brick());
        // 随机生成下方块的 type_code
        this.dto.setNext_brick(random.nextInt(MAX_TYPE_CODE));

        //System.out.println(random.nextInt(MAX_TYPE_CODE));

        // 检查游戏地图是否和新生成的方块有重合，重合即失败
        // 获得当前方块坐标
        Point[] actPoints = this.dto.getGameAct().getActPoint();
        // 获得地图坐标
//        boolean[][] map = this.dto.getGameMap();

        for(int i = 0; i < actPoints.length; i++) {
            if (map[actPoints[i].x][actPoints[i].y]) {
                this.gameOver();
            }
        }

    }


    public void left() {
        this.dto.getGameAct().move(-1, 0, this.dto.getGameMap());

    }

    public void right() {
        this.dto.getGameAct().move(1, 0, this.dto.getGameMap());

    }

    //
    public void testLevelUp() {
        int score = this.dto.getRealtimeScore();
        int rmvLine = this.dto.getRealtimeRemoveLine();
        int lv = this.dto.getLevel();
        score += 10;
        rmvLine += 1;
        if (rmvLine % 20 == 0) {
            lv += 1;
        }

        this.dto.setRealtimeScore(score);
        this.dto.setLevel(lv);
        this.dto.setRealtimeRemoveLine(rmvLine);
    }


    public void gameOver(){
        this.dto.setStart(false);
            // TODO close game main thread
    }

    //
    public void setRecordDataBase(java.util.List<Player> players) {
        this.dto.setDbRecord(players);
    }

    public void setRecordDisk(java.util.List<Player> players) {
        this.dto.setDiskRecord(players);
    }

    /**
     * 判断是否可以消行
     *
     * @param map
     * @param y
     * @return
     */
    private boolean canRemoveLine(boolean[][] map, int y) {
        for (int x = 0; x < 10; x++) {
            if (!map[x][y]) {
                return false;
            }
        }
        return true;
    }


    /**
     * 消行函数
     *
     * @param map
     * @param line
     */
    private void removeLine(boolean[][] map, int line) {
        for (int x = 0; x < 10; x++) {
            for (int y = line; y > 0; y--) {
                map[x][y] = map[x][y - 1];
            }

            map[x][0] = false;
        }
    }

    private int growExp() {
        boolean[][] after_map = this.dto.getGameMap();


        int exp = 0;
        for (int y = 0; y < 18; y++) {
            if (canRemoveLine(after_map, y)) {
                removeLine(after_map, y);
                exp++;
            }
        }
        return exp;
    }

    private  void growScore(int exp) {
        int level = this.dto.getLevel();
        int rmvLine = this.dto.getRealtimeRemoveLine();
        int score = this.dto.getRealtimeScore();
        if (rmvLine % LVL_UP_LINES + exp >= LVL_UP_LINES) {
            this.dto.setLevel(++level);
        }
        this.dto.setRealtimeRemoveLine(rmvLine + exp);
        this.dto.setRealtimeScore(score + PLUS_POINT.get(exp));
    }
}
