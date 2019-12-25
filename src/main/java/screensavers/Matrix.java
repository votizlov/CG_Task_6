package screensavers;

import math.Vector2;

import java.awt.*;
import java.util.LinkedList;

public class Matrix implements ScreenSaver {
    private LinkedList<Number> numbers = new LinkedList<>();
    @Override
    public boolean is3D() {
        return false;
    }

    @Override
    public void update(float dT, Graphics g) {

        for (Number p :numbers//calc existing
        ) {
            Vector2 np = p.getPos()
                    .add(p.getV().mul(dT))
                    .add(p.getA().mul(dT * dT * 0.5));
            Vector2 nv = p.getV()
                    .add(p.getA().mul(dT));

            double vx = nv.getX(), vy = nv.getY();
            boolean reset = false;
            nv = new Vector2(vx, vy);
            if (nv.length() < 1e-10)
                nv = new Vector2(0, 0);
            if (reset)
                np = p.getPos();
            p.setV(nv);
            p.setPos(np);
        }
    }

    @Override
    public void draw(Graphics g) {
        for (Number n:numbers
             ) {
            g.drawString(n.n,(int)n.position.getX(),n.position.getY());
        }
    }

    class Number{
        Vector2 position;
        Vector2 a;
        Vector2 v;
        int n;

        public Vector2 getA() {
            return a;
        }

        public void setA(Vector2 a) {
            this.a = a;
        }

        public Vector2 getV() {
            return v;
        }

        public void setV(Vector2 v) {
            this.v = v;
        }

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }

        public Vector2 getPos() {
            return position;
        }

        public void setPos(Vector2 pos) {
            this.position = pos;
        }

        public Number(Vector2 pos, Vector2 a, Vector2 v, int n) {
            this.position = pos;
            this.a = a;
            this.v = v;
            this.n = n;
        }
    }
}
