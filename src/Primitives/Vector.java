package Primitives;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
//This Class represents Vector starts at(0,0,0)

public class Vector implements Comparable<Vector> {

    private Point3D _head;

    public Point3D getHead() {
        return new Point3D(_head);
    }

    public void setHead(Point3D head) {
        this._head = new Point3D(head);
    }

    //c-tor
    public Vector(Point3D head) {
        this._head = new Point3D(head);
    }

    public Vector(Vector vec) {
        this._head = vec.getHead();
    }

    //c-tor
    public Vector(double x, double y, double z) {
        this._head = new Point3D(x, y, z);
    }

    public Vector add(Vector v)//add vector to vector
    {
        return new Vector(getHead().add(v));
    }

    public Vector subtract(Vector v)//subtract vector from vector
    {
        return getHead().subtract(v.getHead());
    }

    public Vector cross_Product(Vector v)//calculates cross product
    {
        Point3D p = v.getHead();
        Point3D newH = new Point3D(
                new Coordinate(getHead().getY().getCoordinate() * p.getZ().getCoordinate())
                        .subtract(new Coordinate(getHead().getZ().getCoordinate() * p.getY().getCoordinate())),
                new Coordinate(getHead().getZ().getCoordinate() * p.getX().getCoordinate())
                        .subtract(new Coordinate(getHead().getX().getCoordinate() * p.getZ().getCoordinate())),
                new Coordinate(getHead().getX().getCoordinate() * p.getY().getCoordinate())
                        .subtract(new Coordinate(getHead().getY().getCoordinate() * p.getX().getCoordinate()))
        );

        return new Vector(newH);
    }

    public double length()//calculates length of vector
    {
        return sqrt(pow(getHead().getX().getCoordinate(), 2)
                + pow(getHead().getY().getCoordinate(), 2)
                + pow(getHead().getZ().getCoordinate(), 2));
    }

    public void normalize() throws ArithmeticException //normalize vector 
    {
        if (length() == 0) {
            throw new ArithmeticException();
        }
        double length = length();
        setHead(new Point3D(getHead().getX().getCoordinate() / length,
                getHead().getY().getCoordinate() / length,
                getHead().getZ().getCoordinate() / length));
    }

    public double dot_product(Vector v)//calculate dot product
    {
        return getHead().getX().getCoordinate() * v.getHead().getX().getCoordinate()
                + getHead().getY().getCoordinate() * v.getHead().getY().getCoordinate()
                + getHead().getZ().getCoordinate() * v.getHead().getZ().getCoordinate();
    }

    public void scale(double scalingFacor)//multing vector in scalar
    {
        setHead(new Point3D(getHead().getX().getCoordinate() * scalingFacor,
                getHead().getY().getCoordinate() * scalingFacor,
                getHead().getZ().getCoordinate() * scalingFacor));
    }

    @Override
    public int compareTo(Vector o) {//return 0 is the vectors are equals.
        if (this.getHead().compareTo(o.getHead()) == 0) {
            return 0;
        }
        return -1;
    }
}
