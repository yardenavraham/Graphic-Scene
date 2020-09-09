package Elements;

import Primitives.Point3D;
import Primitives.Vector;
import java.awt.Color;

//This class represents spot light by vector
public class SpotLight extends PointLight {

    private Vector direction;

    //c-tor
    public SpotLight(Vector direction, Point3D _position, double _Kc, double _Kl, double _Kq, Color _color) {
        super(_position, _Kc, _Kl, _Kq, _color);
        direction.normalize();
        this.direction = direction;
    }

    public SpotLight(Point3D _position, double _Kc, double _Kl, double _Kq, Color _color) {
        super(_position, _Kc, _Kl, _Kq, _color);
    }

    //copy c-tor
    public SpotLight(SpotLight sl) {
        super(sl.getPosition(), sl.getKc(), sl.getKl(), sl.getKq(), sl.getColor());
        this.direction = sl.getDirection();
    }

    // **** Getters/Setters **** //  
    public Vector getDirection() {
        return new Vector(direction);
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    //This function calculates the intensity of the color
    @Override
    public Color getIntensity(Point3D point) {
        Vector L = getL(point);//The vector between the light and the point
        L.normalize();

        double d = getPosition().distance(point);//distance between the position of the light and the point
        double divideFactor = getKc() + getKl() * d + getKq() * Math.pow(d, 2);//calculate the intensity of the attenuation factors
        if (divideFactor <= 1) {//מניעת הגדלת הצבע מעבר למקסימום בצורה פרופורציונלית
            divideFactor = 1;
        }
        double multFactor = Math.abs(L.dot_product(getDirection()));//calculate the intensity of the color by multing the direction with the vector between the light and the point
        if (multFactor > 1) {//מניעת הגדלת הצבע מעבר למקסימום בצורה פרופורציונלית
            multFactor = 1;
        }        
        int red = (int) Math.min(255,getColor().getRed() * multFactor / divideFactor);//takes the minimum value
        int green = (int) Math.min(255,getColor().getGreen() * multFactor / divideFactor);
        int blue = (int) Math.min(255,getColor().getBlue() * multFactor / divideFactor);
        return new Color(red,green,blue);
    }

}
