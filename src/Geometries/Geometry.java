package Geometries;
import Primitives.Material;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import java.awt.Color;
import java.util.List;

//This abstract class represents geometry by material and emmission
public abstract class Geometry {

    private Material _material;
    private Color _emmission;

    //c-tor
    public Geometry() {
        _material=new Material();
        _emmission=new Color(0, 0, 0);
    }

    public Geometry(Color _emmission) {
        this._material = new Material();
        this._emmission = _emmission;
    }

    public Geometry(Material _material, Color _emmission) {
        this._material = _material;
        this._emmission = _emmission;
    }
    
    //copy c-tor
    public Geometry(Geometry g) {
        this._material = g.getMaterial();
        this._emmission = g.getEmmission();
    }
    
    // **** Getters/Setters **** //  
    public Material getMaterial() {
        return new Material(_material);
    }

    public void setMaterial(Material _material) {
        this._material = new Material(_material);
    }

    public Color getEmmission() {
        return _emmission;
    }

    public void setEmmission(Color _emmission) {
        this._emmission = _emmission;
    }
    
    public abstract Vector getNormal(Point3D p);
    public abstract List<Point3D> findIntersections(Ray ray);
}
