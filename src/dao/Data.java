package dao; // data access object

import dto.Player;

import java.util.List;

/**
 * 实现了一个 Data 接口
 * Created by StReaM on 12/5/2015.
 */
public interface Data {

    /**
     * 获得数据
     * @return
     *
     */
    List<Player> loadData();

    /**
     * 获得数据
     * @param player
     */

    void saveData(Player player);

}
