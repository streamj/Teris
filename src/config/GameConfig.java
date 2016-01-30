package config;


import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * Created by StReaM on 11/27/2015.
 */
public class GameConfig {

//    private int width;
//    private int height;
//    private int windowSize;
//    private int padding;
//    //private int windowUp;
//    private String title;
    private static FrameConfig FRAME_CONFIG = null;

    private static DataConfig DATA_CONFIG = null;

    private static SystemConfig SYSTEM_CONFIG = null;

    static {

        try {
            // 创建XML读取器
            SAXReader read = new SAXReader();
            // 读取XML
            Document doc = read.read("data/cfg.xml");
            // 获得XML 根节点?
            Element game = doc.getRootElement();

            FRAME_CONFIG = new FrameConfig(game.element("frame"));
            DATA_CONFIG = new DataConfig(game.element("data"));
            SYSTEM_CONFIG = new SystemConfig(game.element("system"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private GameConfig() {

    }

    public static FrameConfig getFrameConfig() {
        return FRAME_CONFIG;
    }

    public static DataConfig getDataConfig() {
        return DATA_CONFIG;
    }

    public static SystemConfig getSystemConfig() {
        return SYSTEM_CONFIG;
    }
}
