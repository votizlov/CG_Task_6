package draw;


import main.math.Vector3;


public class Ray {
    public Vector3 getPoint() {
        return point;
    }

    public Vector3 getDirection() {
        return direction;
    }

    private Vector3 point,direction;

    public Ray(Vector3 point, Vector3 direction) {
        this.point = point;
        this.direction = direction;
    }

    //point + dir * t = p соединяем с вершинами и углы равны 2pi
    public void trace(float d){
        point = point.add(direction.mul(d));
    }
}
