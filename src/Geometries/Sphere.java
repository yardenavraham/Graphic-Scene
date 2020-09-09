package Geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import java.awt.Color;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.List;

//This class represents Sphere by Point3D to represents the center and radius.
public class Sphere extends RadialGeometry {

    private Point3D _center;

    //c-tor
    public Sphere(Point3D _center, double _radius, Color _emmission) {
        super(_radius, _emmission);
        this._center = _center;
    }
    
    public Sphere(Point3D _center, double _radius) {
        super(_radius);
        this._center = _center;
    }
    
    //copy c-tor
    public Sphere(Sphere s)
    {
        super(s.getRadius(),s.getEmmission());
        _center=s.getCenter();
    }    

    // **** Getters/Setters **** //
    public Point3D getCenter() {
        return new Point3D(_center);
    }

    public void setCenter(Point3D _center) {
        this._center = _center;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        double r = this.getRadius();
        Point3D P0 = ray.getPOO();
        Vector V = ray.getDirection();
        V.normalize();
        Vector L = _center.subtract(P0);//the vector between the camera and the center of the sphere.
        double tm = L.dot_product(V);//the projection of L on V
        double d = sqrt(pow(L.length(), 2) - pow(tm, 2));//the distance between the center and the ray
        if (d > r) {
            return new ArrayList<Point3D>();//no intersection points
        }
        if (d == r) {//the ray touches a sphere
            V.normalize();
            V.scale(tm);
            Point3D p = P0.add(V);
            List<Point3D> intersections = new ArrayList<Point3D>();
            intersections.add(p);
            return intersections;
        }
        double th = sqrt(pow(r, 2) - pow(d, 2));//the distance between the first point on the sphere and the middle of the chord(that on v)
        double t1 = tm - th;//the distance between P0 to the first intersection point
        double t2 = tm + th;//the distance between P0 to the second intersection point
        if (t1 < 0 && t2 < 0) {
            return new ArrayList<Point3D>();//no intersection points
        }
        List<Point3D> intersections = new ArrayList<Point3D>();
        if(t1>0)// take only if t > 0
        {
            V.normalize();
            V.scale(t1);
            Point3D p1 = P0.add(V);//find the first intersection point
            intersections.add(p1);
        }
        if(t2>0)// take only if t > 0
        {
            V.normalize();
            V.scale(t2);
            Point3D p2 = P0.add(V);//find the second intersection point
            intersections.add(p2);
        }
        return intersections;
    }
    
    //return the normal of the sphere
    @Override
    public Vector getNormal(Point3D p) {
        Vector N=p.subtract(_center);//the radious
        N.normalize();
        return N;
    }
}
