
package Elements;

import Primitives.Point3D;
import java.awt.Color;

//This class represents light by color
public abstract class Light {
    
    private Color _color;
    public abstract Color getIntensity(Point3D point);

    //**** c-tor ***** //
    public Light(Color _color) {
        this._color = new Color(_color.getRed(), _color.getGreen(), _color.getBlue());
    }
    public Light() {
        _color=new Color(0,0,0);
    }
    public Light(int r,int g,int b)
    {
        _color=new Color(r,g,b);
    }
    public Light(Light light)
    {
        this._color = light.getColor();
    }

    // **** Getters/Setters **** //  
    public Color getColor() {
        return _color;
    }

    public void setColor(Color _color) {
        this._color = _color;
    }
}
