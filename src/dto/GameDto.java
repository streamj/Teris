package dto;

import entity.GameAct;
import service.ThreadFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 游戏数据源的类，封装了所有数据
 *
 * Created by StReaM on 11/29/2015.
 */
public class GameDto {

    private boolean pause = false;
    private static final int  MAX_TYPE_CODE = 6;

    private List<Player> dbRecord;
    private List<Player> diskRecord;
    /**
     * 游戏地图，如果 game[x][y]是 true, 表示堆积
     */
    private boolean[][] gameMap;
    /**
     * 可操作的游戏方块
     */
    private GameAct gameAct;
    private int next_brick;
    private int level;
    private int realtimeScore;
    private int realtimeRemoveLine;

    private long sleepTime; // for thread sleep

    private Random random = new Random();

    private boolean startCode = false;

    public GameDto() {
        dtoInit();

//        System.out.println(next_brick);
    }

    public void dtoInit() {
        this.gameMap = new boolean[10][18];
        next_brick = random.nextInt(MAX_TYPE_CODE);
        this.realtimeScore = 0;
        this.level = 0;
        this.realtimeRemoveLine = 0;
        this.pause =false;
        this.sleepTime = ThreadFunction.getSleepTimeByLevel(this.level); // if not initialize will cause bug
    }

    public List<Player> getDbRecord() {
        return dbRecord;
    }

    public List<Player> getDiskRecord() {
        return diskRecord;
    }

    public boolean[][] getGameMap() {
        return gameMap;
    }

    public GameAct getGameAct() {
        return gameAct;
    }

    public int getNext_brick() {
        return next_brick;
    }

    public int getLevel() {
        return level;
    }

    public int getRealtimeScore() {
        return realtimeScore;
    }

    public int getRealtimeRemoveLine() {
        return realtimeRemoveLine;
    }

    public void setDbRecord(List<Player> dbRecord) {

        this.dbRecord = setFullRecord(dbRecord);
    }

    public void setDiskRecord(List<Player> diskRecord) {

        this.diskRecord = setFullRecord(diskRecord);
    }

    public void setGameMap(boolean[][] gameMap) {
        this.gameMap = gameMap;
    }

    public void setGameAct(GameAct gameAct) {
        this.gameAct = gameAct;
    }

    public void setNext_brick(int next_brick) {
        this.next_brick = next_brick;
    }

    public void setLevel(int level) {
        this.level = level;
        this.sleepTime = ThreadFunction.getSleepTimeByLevel(this.level);
    }

    public void setRealtimeScore(int realtimeScore) {
        this.realtimeScore = realtimeScore;
    }

    public void setRealtimeRemoveLine(int realtimeRemoveLine) {
        this.realtimeRemoveLine = realtimeRemoveLine;
    }

    private List<Player> setFullRecord(List<Player> players) {
        /**
         * 记录不满5条，就给他塞满5条
         */
        if (players == null) {
            players = new ArrayList<Player>();
        }
        while (players.size() < 5) {
            players.add(new Player("No data", 0));
        }
        Collections.sort(players);

        return players;
    }

    public boolean isStart() {
        return startCode;
    }

    public void setStart(boolean start) {
        startCode = start;
    }

    public boolean isPause() {
        return pause;
    }

    public void switchPause() {
        this.pause = !this.pause;
    }

    public long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }
}