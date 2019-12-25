/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package third;

import main.draw.Ray;
import main.math.Vector3;

import java.util.List;

/**
 * Описывает в общем виде любую модель
 * @author Alexey
 */
public interface IModel {
    /**
     * Любая модель - это набор полилиний.
     * @return Списко полилиний модели.
     */
    List<PolyLine3D> getLines();
    Material getMat();
    Vector3 checkRayCollision(Ray r);
}
