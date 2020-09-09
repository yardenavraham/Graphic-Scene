package Geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import Geometries.Plane;
import Primitives.Material;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

//This class represents Triangle by 3 Point3D.
public class Triangle extends Geometry implements FlatGeometry{

    private Point3D _p1;
    private Point3D _p2;
    private Point3D _p3;

    //c-tor
    public Triangle(Point3D _p1, Point3D _p2, Point3D _p3, Color _emmission) {
        super(_emmission);
        this._p1 = _p1;
        this._p2 = _p2;
        this._p3 = _p3;
    }

    public Triangle(Point3D _p1, Point3D _p2, Point3D _p3) {
        this._p1 = _p1;
        this._p2 = _p2;
        this._p3 = _p3;
    }
    
    //copy c-tor
    public Triangle(Triangle triangle) {
        this._p1 = triangle.getP1();
        this._p2 = triangle.getP2();
        this._p3 = triangle.getP3();
    }

    // **** Getters/Setters **** //  
    public Point3D getP1() {
        return new Point3D(_p1);
    }

    public void setP1(Point3D _p1) {
        this._p1 = _p1;
    }

    public Point3D getP2() {
        return new Point3D(_p2);
    }

    public void setP2(Point3D _p2) {
        this._p2 = _p2;
    }

    public Point3D getP3() {
        return new Point3D(_p3);
    }

    public void setP3(Point3D _p3) {
        this._p3 = _p3;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        //find the intersection point with the plane
        Vector P1P2 = getP2().subtract(getP1());
        Vector P1P3 = getP3().subtract(getP1());
        Vector Ntriangle = P1P2.cross_Product(P1P3);
        Plane Ptriangle = new Plane(Ntriangle, getP1());
        List<Point3D> intersection = Ptriangle.findIntersections(ray);
        if(intersection.isEmpty())
            return intersection;
        Point3D iPoint = intersection.get(0);

        //checks if the point inside the triangle
        Point3D P0 = ray.getPOO();
        Vector v1 = getP1().subtract(P0);//the vector between p1 and p0
        Vector v2 = getP2().subtract(P0);//the vector between p2 and p0
        Vector v3 = getP3().subtract(P0);//the vector between p2 and p0

        //side 1        
        Vector N1 = v1.cross_Product(v2);
        N1.normalize();

        //side 2
        Vector N2 = v2.cross_Product(v3);
        N2.normalize();

        //side 3
        Vector N3 = v3.cross_Product(v1);
        N3.normalize();

        //checks if the point inside the triangle 
        //by checking if all the dot-products of the normals with the vector between the point and p0 gives the same sign
        Vector vec = iPoint.subtract(P0);
        if (vec.dot_product(N1) >= 0 && vec.dot_product(N2) >= 0 && vec.dot_product(N3) >= 0)//all of them positive
        {
            return intersection;
        }
        if (vec.dot_product(N1) < 0 && vec.dot_product(N2) < 0 && vec.dot_product(N3) < 0)//all of them negetive
        {
            return intersection;
        }
        return new ArrayList<Point3D>();//no intersection points
        
    }

    //reutrn the normal of the vector
    @Override
    public Vector getNormal(Point3D p) {
        //The vectors of the triangle
        Vector v1 = getP2().subtract(getP1());
        Vector v2 = getP3().subtract(getP1());
        //calculate the normal
        Vector n = v1.cross_Product(v2);
        n.normalize();
        return new Vector(n);
    }
}
