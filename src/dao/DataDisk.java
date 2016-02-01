package dao;

import dto.Player;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * Created by StReaM on 12/6/2015.
 */
public class DataDisk implements Data {
    /**
     * 获得数据
     * @return
     */

    private  final String file_path;

    public DataDisk(HashMap<String, String> param) {
        this.file_path = param.get("path");
    }
    @Override
    public List<Player> loadData() {
        ObjectInputStream ois = null;
        List<Player> players = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file_path));
            players = (List<Player>)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return players;
    }

    /**
     * 获得数据
     * @param player
     */
    @Override
    public void saveData(Player player) {
        List<Player> players = this.loadData();
        // TODO 判断是否超过5条，去掉最低的一条
        players.add(player);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file_path));
            oos.writeObject(players);
        } catch (Exception e) {
                e.printStackTrace();
            } finally {

            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
