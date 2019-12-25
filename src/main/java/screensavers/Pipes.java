package screensavers;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Pipes implements ScreenSaver {
    @Override
    public boolean is3D() {
        return true;
    }

    @Override
    public void update(float dT, Graphics g) {

    }

    @Override
    public void draw(Graphics g) {
        sc.setScreenSize(getWidth(), getHeight());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) bi.getGraphics();
        IDrawer dr = new SimpleEdgePolygonDrawer(sc, graphics);
        if (!isRendererActive)
            scene.drawScene(dr, cam, new Renderer(sc, cam, scene),bi);
        else
            scene.drawScene(dr, cam, null,null);
        g.drawImage(bi, 0, 0, null);
        graphics.dispose();
        /*for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(cam.getTranslate().getAt(i, j) + " ");
            }
            System.out.println();
        }*/
    }
}
