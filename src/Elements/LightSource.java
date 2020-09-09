package Elements;

import Primitives.Point3D;
import Primitives.Vector;
import java.awt.Color;

//This interface represents Light Source
public interface LightSource {
    
    public Color getIntensity(Point3D point);
    public Vector getL(Point3D point);//vector between the light and the point
    
}
