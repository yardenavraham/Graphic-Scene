package Geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

//This class represents Plane by 3 Point3D.
public class Plane extends Geometry implements FlatGeometry {

    Vector N;
    Point3D _Q;

    //c-tor
    public Plane(Vector N, Point3D _Q, Color _emmission) {
        super(_emmission);
        this.N = N;
        this._Q = _Q;
    }

    public Plane(Plane p) {
        super(p.getEmmission());
        this.N = p.getN();
        this._Q = p.getQ();
    }

    public Plane(Vector N, Point3D _Q) {
        this.N = N;
        this._Q = _Q;
    }

    // **** Getters/Setters **** //  
    public Vector getN() {
        return new Vector(N);
    }

    public void setN(Vector N) {
        this.N = N;
    }

    public Point3D getQ() {
        return new Point3D(_Q);
    }

    public void setQ(Point3D _Q) {
        this._Q = _Q;
    }

    @Override
    //This function find the intersection point with the plane
    public List<Point3D> findIntersections(Ray ray) {
        Point3D P0 = ray.getPOO();
        Vector v = ray.getDirection();
        v.normalize();
        //find the distance between the camera(P0) and the intersection point
        N.normalize();
        List<Point3D> intersections = new ArrayList<Point3D>();
        double t = -(N.dot_product(P0.subtract(_Q)) / N.dot_product(v));
        if (t >= 0) {
            v.scale(t);
            //find the intersection point
            Point3D p = P0.add(v);
            intersections.add(p);
        }

        return intersections;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return getN();
    }
}
