package config;

/**
 * Created by StReaM on 11/27/2015.
 */
public class LayerConfig {
    private String className;

    private int x;
    private int y;
    private int layer_width;
    private int layer_height;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLayer_width() {
        return layer_width;
    }

    public int getLayer_height() {
        return layer_height;
    }

    public String getClassName() {
        return className;
    }

    public LayerConfig(String className, int x, int y, int layer_width, int height) {
        this.className = className;
        this.x = x;
        this.y = y;
        this.layer_width = layer_width;
        this.layer_height = height;
    }

}
