package screensavers;

import java.awt.*;

public interface ScreenSaver {
    boolean is3D();
    void update(float dT, Graphics g);
}
