package config;

import org.dom4j.Element;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by StReaM on 12/14/2015.
 */
public class SystemConfig {

    private final int min_x ;
    private final int max_x ;
    private final int min_y ;
    private final int max_y ;
    private final int lvl_up_line;

    private final List<Point[]> brick_type;

    private final Map<Integer, Integer> score_detail;

    public SystemConfig(Element system) {
        this.min_x = Integer.parseInt(system.attributeValue("min_x"));
        this.max_x = Integer.parseInt(system.attributeValue("max_x"));
        this.min_y = Integer.parseInt(system.attributeValue("min_y"));
        this.max_y = Integer.parseInt(system.attributeValue("max_y"));
        this.lvl_up_line = Integer.parseInt(system.attributeValue("lvl_up_line"));

        List<Element> rects = system.elements("rect");
        this.brick_type = new ArrayList<Point[]>(rects.size());
        for (Element e: rects) {
            List<Element> pointConfig = e.elements("point");
            Point[] points = new Point[pointConfig.size()];

            for (int i = 0; i< points.length; i++) {
                int x = Integer.parseInt(pointConfig.get(i).attributeValue("x"));
                int y = Integer.parseInt(pointConfig.get(i).attributeValue("y"));
                points[i] = new Point(x,y);
            }

            brick_type.add(points);
        }
        this.score_detail = new HashMap<>();
        List<Element> ppcfg = system.elements("plusPoint");
        for(Element p : ppcfg) {
            int rm = Integer.parseInt(p.attributeValue("rm"));
            int Score_point = Integer.parseInt(p.attributeValue("point"));
            this.score_detail.put(rm, Score_point);
        }
    }

    public int getMin_x() {
        return min_x;
    }

    public int getMax_x() {
        return max_x;
    }

    public int getMin_y() {
        return min_y;
    }

    public int getMax_y() {
        return max_y;
    }

    public int getLvlUpLine() {
        return lvl_up_line;
    }

    public List<Point[]> getBrick_type() {
        return brick_type;
    }

    public Map<Integer, Integer> getScore_detail() {
        return score_detail;
    }
}
