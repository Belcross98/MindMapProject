package dsw.gerumap.app.serializer;

import java.awt.*;

public class SerializableColor {
    int r, g, b, a;

    public SerializableColor(Color col)
    {
        r = col.getRed();
        g = col.getGreen();
        b = col.getBlue();
        a = col.getAlpha();
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public int getA() {
        return a;
    }

    public Color ToAwtColor()
    {
        return new Color(r, g, b, a);
    }
}
