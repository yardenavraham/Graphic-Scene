
package Primitives;
//This class represents Point3D by 3 Coordinates.
public class Point3D implements Comparable<Point3D>{

    private Coordinate _x;
    private Coordinate _y;
    private Coordinate _z;

    //c-tor
    public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
        this._x = new Coordinate(_x);
        this._y = new Coordinate(_y);
        this._z = new Coordinate(_z);
    }
    public Point3D(Point3D p) {
        this._x = p.getX();
        this._y = p.getY();
        this._z = p.getZ();
    }
    //c-tor
    public Point3D(double _x, double _y, double _z) {
        this._x = new Coordinate(_x);
        this._y = new Coordinate(_y);
        this._z = new Coordinate(_z);
    }

   // **** Getters/Setters **** //
    public Coordinate getX() {
        return new Coordinate(_x);
    }

    public void setX(Coordinate _x) {
        this._x = _x;
    }

    public Coordinate getY() {
        return new Coordinate(_y);
    }

    public void setY(Coordinate y) {
        this._y = new Coordinate(y);
    }

    public Coordinate getZ() {
        return new Coordinate(_z);
    }

    public void setZ(Coordinate z) {
        this._z = new Coordinate(z);
    }
    
    
     public Point3D add(Vector v)//add vector to point
    {
        Point3D p=v.getHead();
        return new Point3D(getX().add(p.getX()), getY().add(p.getY()), getZ().add(p.getZ()));
    }
    
//    public Point3D subtract(Point3D p)//subtract point from point 
//    {
//        
//        return new Point3D(_x.subtract(p.getX()), _y.subtract(p.getY()), _z.subtract(p.getZ()));
//        
//    }
    public Vector subtract(Point3D p)//subtract point from point 
    {
        
        return new Vector(new Point3D(getX().subtract(p.getX()), getY().subtract(p.getY()), getZ().subtract(p.getZ())));
        
    }

    @Override
    public int compareTo(Point3D o) {//return 0 if the points equals
        if (this.getX().compareTo(o.getX())==0&&getY().compareTo(o.getY())==0&&getZ().compareTo(o.getZ())==0)
            return 0;
        return -1;
    }

    //return the distance
    public double distance(Point3D point) {
        return this.subtract(point).length();
    }
    
}
