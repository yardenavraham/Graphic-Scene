package Elements;

import Primitives.Point3D;
import Primitives.Vector;
import java.awt.Color;

//This class represents directional light by vector and color
public class DirectionalLight extends Light implements LightSource{

    private Vector direction;

    //**** c-tor ***** //
    public DirectionalLight(Vector direction, Color _color) {
        super(_color);
        this.direction = direction;
    }
    public DirectionalLight(DirectionalLight dl) {
        super(dl.getColor());
        this.direction = dl.getDirection();
    }

    // **** Getters/Setters **** //  
    public Vector getDirection() {
        return new Vector(direction);
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    @Override
    public Color getIntensity(Point3D point) {    //return the intensity of the color
        return getColor();
    }

    @Override
    public Vector getL(Point3D point) {//vector between the light and the point
        return new Vector(point.subtract(getDirection().getHead()));
    }
}
