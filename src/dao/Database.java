package dao;

import dto.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by StReaM on 12/5/2015.
 */
public class Database implements Data {

//    // jdbc
//    private static String DRIVER; // 驱动包
//    private static String DB_URL; // 数据库地址
//    private static String DB_USER;
//    private static String DB_PWD;
//    private static String LOAD_SQL; // 要运行的SQL语句
//
//    static {
//        try {
//            Class.forName(DRIVER);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public Database(HashMap<String, String> param) {

    }
    /**
     * 获得数据
     *
     * @return
     */
    @Override
    public List<Player> loadData() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Nao", 100));
        players.add(new Player("Nao", 200));
        players.add(new Player("Nao", 300));
        players.add(new Player("Nao", 400));
        players.add(new Player("Nao", 500));

//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        try {
//            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
//            stmt = conn.prepareStatement(LOAD_SQL);
//            stmt.executeQuery();
//            while(rs.next()) {
//                players.add(new Player(rs.getString(1), rs.getInt(2)));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//                if (stmt != null) {
//                    stmt.close();
//                }
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        return players;
    }

    /**
     * 获得数据
     *
     * @param player
     */
    @Override
    public void saveData(Player player) { // 接口改成 Player player
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        try {
//            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
//            stmt = conn.prepareStatement(LOAD_SQL);
//            stmt.setObject(1, player.getName());
//            stmt.executeQuery();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//                if (stmt != null) {
//                    stmt.close();
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

}
