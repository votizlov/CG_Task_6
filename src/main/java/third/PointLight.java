package third;


import main.math.Vector3;
import models.Anchor;

import java.awt.*;

public class PointLight {
    private double intensity;//how color changes over 1 meter
    private Color color;
    private Vector3 pos;

    public Anchor getA() {
        return a;
    }

    private Anchor a;

    public PointLight(double intensity,Color color,Anchor a) {
        this.intensity = intensity;
        this.color = color;
        this.a = a;
    }

    public Vector3 getPos() {
        return pos;
    }

    public void setPos(Vector3 v){
        pos = v;
    }

    public double getIntensity() {
        return intensity;
    }

    public Color getColor() {
        return color;
    }
}
