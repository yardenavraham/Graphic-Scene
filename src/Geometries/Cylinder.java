
package Geometries;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import java.awt.Color;
import java.util.List;

//This class represents Cylinder by Point3D to represent the axis point
//and Vector to represent the axis direction and number for radius

public class Cylinder extends RadialGeometry{

    private Point3D _axisPoint;
    private Vector _axisDirection;

// ***************** Getters/Setters ********************** //    
    public Point3D getAxisPoint() {
        return _axisPoint;
    }

    public void setAxisPoint(Point3D _axisPoint) {
        this._axisPoint = _axisPoint;
    }

    public Vector getAxisDirection() {
        return _axisDirection;
    }

    public void setAxisDirection(Vector _axisDirection) {
        this._axisDirection = _axisDirection;
    }

    //c-tor
    public Cylinder(Point3D _axisPoint, Vector _axisDirection, double _radius, Color _emmission) {
        super(_radius, _emmission);
        this._axisPoint = _axisPoint;
        this._axisDirection = _axisDirection;
    }
    
    
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector getNormal(Point3D p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
