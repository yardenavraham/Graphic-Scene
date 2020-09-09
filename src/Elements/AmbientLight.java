package Elements;
import Primitives.Point3D;
import java.awt.Color;

//This class represents ambient light and color
public class AmbientLight extends Light{

    private double Ka;

    //c-tor
    public AmbientLight(double Ka, Color _color)
    {
        super(_color);
        this.Ka=Ka;
    }

    public AmbientLight(Color _color) {
        super(_color);
        Ka= 0.1;
    }
    public AmbientLight() {
        super();
        Ka= 0.1;
    }
    //c-tor
    public AmbientLight(int r,int g,int b)
    {
        super(r,g,b);
        Ka=0.1;
    }
    
    //copy c-tor
    public AmbientLight(AmbientLight light)
    {
        super(light.getColor());
        this.Ka = light.getKa();
    }

    // **** Getters/Setters **** // 
    public double getKa() {
        return Ka;
    }

    public void setKa(double Ka) {
        this.Ka = Ka;
    }
    
    //return the intensity of the color
    @Override
    public Color getIntensity(Point3D point)
    {
        return new Color((int)(getColor().getRed()*getKa()),(int)(getColor().getGreen()*getKa()),(int)(getColor().getBlue()*getKa()));
    }
    
   
    
    
    
}
