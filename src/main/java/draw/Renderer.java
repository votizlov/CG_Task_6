package draw;

import main.math.Matrix4;
import main.math.Matrix4Factories;
import main.math.Vector3;
import main.math.Vector4;
import main.screen.ScreenConverter;
import main.third.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import static java.lang.Math.cos;

public class Renderer {
    private static final int MAX_RAY_DEPTH = 3;
    private static final float PRECISION = 0.0001f;
    private ScreenConverter sc;
    private ICamera cam;
    private Matrix4 rayPointerX;
    private Matrix4 rayPointerY;
    private Scene scene;
    IModel primRayHit = null;
    Vector3 primRayHitPoint = null;
    Vector3 normalHit = null;
    double r;
    float E = 1;//fov = 0.273589248 halffov = 0.136794624 rad

    public Renderer(ScreenConverter sc, ICamera cam, Scene scene) {
        this.sc = sc;
        this.cam = cam;
        this.scene = scene;
        rayPointerX = Matrix4Factories.rorationAroundVector(new Vector3(1,0,0), (float) cos(0.136794624/300));
        rayPointerY = Matrix4Factories.rorationAroundVector(new Vector3(0,1,0), (float) cos(0.136794624/300));
    }

    public Image renderImage(BufferedImage bi,LinkedList<PolyLine3D> lines) {

        Vector3 t = cam.getCameraDir();
        //начальный луч в левом верхнем углу
        for (int i = 0; i < 300; i++) {
            t = rayPointerX.mul(new Vector4(t,0)).asVector3();
        }
        for (int i = 0; i < 300; i++) {
            t = rayPointerY.mul(new Vector4(t,0)).asVector3();
        }
int c = 0;
        for (int j = 0; j < 600; ++j) {
            for (int i = 0; i < 600; ++i) {

                // compute primary ray direction
                Ray primRay = new Ray(cam.getCameraPos(), t);
                while (new Vector3(cam.getCameraPos(), primRay.getPoint()).length() < 5) {
                    findNearestFace(primRay.getPoint(),lines);
                    if (primRayHitPoint != null) {
                        break;
                    }else{
                        primRay.trace((float) r);
                    }

                    //todo normal for reflection
                }

                boolean isInShadow = false;/*
                if (primRayHit != null) {
                    // compute illumination
                    for (PointLight p : scene.getLights()
                    ) {
                        Ray shadowRay = new Ray(primRayHitPoint, new Vector3(primRayHitPoint, p.getPos()), new Vector3(primRayHitPoint, p.getPos()).length());
                        for (IModel m : scene.getModelsList()) {
                            for (PolyLine3D p1 : m.getLines()
                            ) {
                                Vector3 t = primRay.isHitting(p1);
                                if (t != null) {
                                    isInShadow = true;
                                    break;
                                }
                            }
                            if (primRayHit != null)
                                break;
                        }
                    }
                }*/
                if (primRayHitPoint==null) {
                    bi.setRGB(i, j, Color.WHITE.getRGB());
                } else {
                    bi.setRGB(i,j,Color.CYAN.getRGB());
                }
                System.out.println(c++);

                primRayHit = null;
                primRayHitPoint = null;
                t = rayPointerX.mul(new Vector4(t,0)).asVector3();

            }
            t = rayPointerY.mul(new Vector4(t,0)).asVector3();
        }


        return bi;
    }

    private void findNearestFace(Vector3 p,LinkedList<PolyLine3D> lines){
    float min = new Vector3(p,scene.getModelsList().get(0).getLines().get(0).getPoints().get(0)).length();
    PolyLine3D nearest = null;
    IModel mod = null;
       // for (IModel m:scene.getModelsList()
         //    ) {
            for (PolyLine3D l:lines
                 ) {
                if(l.isClosed())
                for (Vector3 v:l.getPoints()
                     ) {
                    Vector3 t = new Vector3(p,v);
                    if(t.length()<min) {
                        min = t.length();
                        nearest=l;
                        //mod = m;
                    }
                }
            }
        //}
        r = min;
        if(min<E) {
            primRayHitPoint = p;
            //primRayHit = mod;
        }

    }
    /*
    #define MAX_RAY_DEPTH 3

color Trace(const Ray &ray, int depth)
{
    Object *object = NULL;
    float minDist = INFINITY;
    Point pHit;
    Normal nHit;
    for (int k = 0; k < objects.size(); ++k) {
        if (Intersect(objects[k], ray, &pHit, &nHit)) {
            // ray origin = eye position of it's the prim ray
            float distance = Distance(ray.origin, pHit);
            if (distance < minDistance) {
                object = objects[i];
                minDistance = distance;
            }
        }
    }
    if (object == NULL)
        return 0;
    // if the object material is glass, split the ray into a reflection
    // and a refraction ray.
    if (object->isGlass && depth < MAX_RAY_DEPTH) {
        // compute reflection
        Ray reflectionRay;
        reflectionRay = computeReflectionRay(ray.direction, nHit);
        // recurse
        color reflectionColor = Trace(reflectionRay, depth + 1);
        Ray refractioRay;
        refractionRay = computeRefractionRay(
            object->indexOfRefraction,
            ray.direction,
            nHit);
        // recurse
        color refractionColor = Trace(refractionRay, depth + 1);
        float Kr, Kt;
        fresnel(
            object->indexOfRefraction,
            nHit,
            ray.direction,
            &Kr,
            &Kt);
        return reflectionColor * Kr + refractionColor * (1-Kr);
    }
    // object is a diffuse opaque object
    // compute illumination
    Ray shadowRay;
    shadowRay.direction = lightPosition - pHit;
    bool isShadow = false;
    for (int k = 0; k < objects.size(); ++k) {
        if (Intersect(objects[k], shadowRay)) {
            // hit point is in shadow so just return
            return 0;
        }
    }
    // point is illuminated
    return object->color * light.brightness;
}

// for each pixel of the image
for (int j = 0; j < imageHeight; ++j) {
    for (int i = 0; i < imageWidth; ++i) {
        // compute primary ray direction
        Ray primRay;
        computePrimRay(i, j, &primRay);
        pixels[i][j] = Trace(primRay, 0);
    }
}
     */
}
