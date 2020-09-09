
package Elements;

import Primitives.Coordinate;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
//This class represents Camera by one Point3D and 3 vectors.
public class Camera {

    private Point3D _P0;
    private Vector _vUp;
    private Vector _vTo;
    private Vector _vRight;
    
    //c-tor
    public Camera(Point3D _P0, Vector _vUp, Vector _vTo) {
        this._P0 = _P0;
        this._vUp = _vUp;
        this._vTo = _vTo;
        this._vRight=_vTo.cross_Product(_vUp);
        
    }
    //copy c-tor
    public Camera(Camera c) {
        this._P0 = c.getP0();
        this._vUp = c.getvUp();
        this._vTo = c.getvTo();
        this._vRight=c.getvRight();
        
    }
    
    // **** Getters/Setters **** // 
    public Point3D getP0() {
        return new Point3D(_P0);
    }

    public void setP0(Point3D _P0) {
        this._P0 = _P0;
    }

    public Vector getvUp() {
        return new Vector(_vUp);
    }

    public void setvUp(Vector _vUp) {
        this._vUp = _vUp;
    }

    public Vector getvTo() {
        return new Vector(_vTo);
    }

    public void setvTo(Vector _vTo) {
        this._vTo = _vTo;
    }

    public Vector getvRight() {
        return new Vector(_vRight);
    }

    public void setvRight(Vector _vRight) {
        this._vRight = _vRight;
        
    }
    //This function return a ray through the pixel
    public Ray construct_Ray_Through_Pixel(int Nx, int Ny, double x, double y, double screen_Distance, double screen_Width, double screen_Height) throws Exception
    {
        //normalize the vectors of the camera
        this._vRight.normalize();
        this._vTo.normalize();
        this._vUp.normalize();
        Vector nvup=getvUp();
        Vector nvright=getvRight();
        Vector nvto=getvTo();
        Point3D P0=new Point3D(0,0,0);//reset P0
        
        nvto.scale(screen_Distance);//extends the vector until the view plane
        Point3D Pc=P0.add(nvto);//find Pc
        double Rx=screen_Width/Nx;//find the pixel width at x axis 
        double Ry=screen_Height/Ny;//find the pixel width at y axis
        double scaling_vRight=(((x-(Nx/2.0))*Rx)+(Rx/2.0));//claculate the distance between PC to P at x axis
        nvright.scale(scaling_vRight);
        double scaling_vUp=(((y-(Ny/2.0))*Ry)+(Ry/2.0));//claculate the distance between PC to P at y axis
        nvup.scale(scaling_vUp);
        Point3D p=Pc.add(nvright.subtract(nvup));//find P by adding the recieved vector to Pc.
        Vector v=new Vector(p);
        v.normalize();
        return new Ray(P0,v);
    }
}
 