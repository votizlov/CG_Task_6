import screensavers.ScreenSaver;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    private ScreenSaver currTemp;

    @Override
    public void paint(Graphics g){
        if(currTemp.is3D()){

        }
    }

    public void setTemplate(ScreenSaver screenSaver) {
        this.currTemp = screenSaver;
        repaint();
    }
}
