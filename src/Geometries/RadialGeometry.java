package Geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import java.awt.Color;
import java.util.List;

//This class represents RadialGeometry by radius.
public abstract class RadialGeometry extends Geometry{

    private double _radius;

    //c-tor
    public RadialGeometry(double _radius, Color _emmission) {
        super( _emmission);
        this._radius = _radius;
    }
    public RadialGeometry(RadialGeometry rg) {
        super( rg.getEmmission());
        this._radius = rg.getRadius();
    }

    public RadialGeometry(double _radius) {
        this._radius = _radius;
    }
    
    // **** Getters/Setters **** //  
    public double getRadius() {
        return _radius;
    }

    public void setRadius(double _radius) {
        this._radius = _radius;
    }

    @Override
    public abstract List<Point3D> findIntersections(Ray ray) ;

    @Override
    public abstract Vector getNormal(Point3D p) ;
}
