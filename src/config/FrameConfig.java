package config;

import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by StReaM on 12/14/2015.
 */
public class FrameConfig {

    private final int width;
    private final int height;
    private final int windowSize;
    private final int padding;
    private final String title;
    private final int loseIdx;
    private final List<LayerConfig> layersConfig;

    private final ButtonConfig buttonConfig;

    public FrameConfig(Element frame) {

        this.width = Integer.parseInt(frame.attributeValue("width"));
        this.height = Integer.parseInt(frame.attributeValue("height"));
        this.windowSize = Integer.parseInt(frame.attributeValue("windowSize"));
        this.padding = Integer.parseInt(frame.attributeValue("padding"));
        this.title = frame.attributeValue("title");
        //this.windowUp = Integer.parseInt(frame.attributeValue("windowUp"));
        this.loseIdx = Integer.parseInt(frame.attributeValue("loseIdx"));


        List<Element> layers = frame.elements("layer");
        layersConfig = new ArrayList<LayerConfig>();
        for (Element layer : layers) {
            LayerConfig lc = new LayerConfig(
                    layer.attributeValue("className"),
                    Integer.parseInt(layer.attributeValue("x")),
                    Integer.parseInt(layer.attributeValue("y")),
                    Integer.parseInt(layer.attributeValue("layer_width")),
                    Integer.parseInt(layer.attributeValue("layer_height"))
            );

            layersConfig.add(lc);
        }

        buttonConfig = new ButtonConfig(frame.element("button"));
    }


    public int getWidth() {
        return width;
    }

    public List<LayerConfig> getLayersConfig() {
        return layersConfig;
    }

    public String getTitle() {
        return title;
    }

    public int getPadding() {
        return padding;
    }

    public int getWindowSize() {
        return windowSize;
    }

    public int getHeight() {
        return height;
    }

    public int getLoseIdx() {
        return loseIdx;
    }

    public ButtonConfig getButtonConfig() {
        return buttonConfig;
    }
}
