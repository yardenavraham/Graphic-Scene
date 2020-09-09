package Elements;
import Primitives.Point3D;
import Primitives.Vector;
import java.awt.Color;

//This class represents point light by point 3D and 3 parameters.
public class PointLight extends Light implements LightSource{

    private Point3D _position;
    private double _Kc;
    private double _Kl;
    private double _Kq;
    
    // **** c-tor **** //
    public PointLight(Point3D _position, double _Kc, double _Kl, double _Kq, Color _color) {
        super(_color);
        this._position = _position;
        this._Kc = _Kc;
        this._Kl = _Kl;
        this._Kq = _Kq;
    }
    
    //copy c-tor
    public PointLight(PointLight pl) {
        super(pl.getColor());
        this._position = pl.getPosition();
        this._Kc =  pl.getKc();
        this._Kl = pl.getKl();
        this._Kq = pl.getKq();
    }

    // **** Getters/Setters **** //  
    public Point3D getPosition() {
        return _position;
    }

    public void setPosition(Point3D _position) {
        this._position = _position;
    }

    public double getKc() {
        return _Kc;
    }

    public void setKc(double _Kc) {
        this._Kc = _Kc;
    }

    public double getKl() {
        return _Kl;
    }

    public void setKl(double _Kl) {
        this._Kl = _Kl;
    }

    public double getKq() {
        return _Kq;
    }

    public void setKq(double _Kq) {
        this._Kq = _Kq;
    }

    //return the intensity of the color
    @Override
    public Color getIntensity(Point3D point) {
        double d= getPosition().distance(point);//distance between the position of the light and the point
        double divideFactor=getKc()+getKl()*d+getKq()*Math.pow(d, 2);//calculate the intensity
        if(divideFactor<=1)//מניעת הגדלת הצבע מעבר למקסימום בצורה פרופורציונלית
            divideFactor=1;
        int red=(int)(getColor().getRed()/divideFactor);
        int green=(int)(getColor().getGreen()/divideFactor);
        int blue=(int)(getColor().getBlue()/divideFactor);
        return new Color(red,green,blue);
    }

    @Override
    public Vector getL(Point3D point) {//vector between the light and the point
        return new Vector(point.subtract(getPosition()));
    }
    
   
}
