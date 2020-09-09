package Primitives;
//This class represents Ray by Point3D to the point of sending the ray 
//and vector to represent the direction .
public class Ray implements Comparable<Ray>{

    private Point3D _POO;
    private Vector _direction;
    
    //c-tor
    public Ray(Point3D POO, Vector direction) {
        this._POO = POO;
        Vector v=new Vector(direction);
        v.normalize();
        this._direction = v;
    }
    public Ray(Ray ray)
    {
        _POO=ray.getPOO();
        _direction=ray.getDirection();
    }

    // **** Getters/Setters **** //
    public Point3D getPOO() {
        return new Point3D(_POO);
    }

    public void setPOO(Point3D _POO) {
        this._POO = _POO;
    }

    public Vector getDirection() {
        Vector v=new Vector(_direction);
        v.normalize();
        return v;
    }

    public void setDirection(Vector direction) {
        direction.normalize();
        this._direction = direction;
    }

     @Override
    public int compareTo(Ray o) {//return 0 if the rays are equals.
        if(this._POO.compareTo(o._POO)==0&&this._direction.compareTo(o.getDirection())==0)
            return 0;
        return -1;
    }

}
