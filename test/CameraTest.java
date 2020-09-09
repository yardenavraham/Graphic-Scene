import Elements.Camera;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
//this class test the functions of the camera
public class CameraTest {
    @Test
    public void testRaysConstruction() throws Exception//test of rays construction
    {
        Camera camera=new Camera(new Point3D(0,0,0), new Vector(0,1,0), new Vector(0,0,-1));
        Ray r=camera.construct_Ray_Through_Pixel(3, 3, 3, 3, 1, 9, 9);
        Ray test=new Ray(new Point3D(0, 0, 0),new Vector(6,-6,-1));
        assertTrue(test.compareTo(r)==0);
              
        camera=new Camera(new Point3D(0,0,0), new Vector(0,1,0), new Vector(0,0,-1));
         r=camera.construct_Ray_Through_Pixel(3, 3, 0, 0, 1, 9, 9);
         test=new Ray(new Point3D(0, 0, 0),new Vector(-3,3,-1));
        assertTrue(test.compareTo(r)==0);
        
        camera=new Camera(new Point3D(0,0,0), new Vector(0,1,0), new Vector(0,0,-1));
         r=camera.construct_Ray_Through_Pixel(3, 3, -3, -3, 1, 9, 9);
         test=new Ray(new Point3D(0, 0, 0),new Vector(-12,12,-1));
        assertTrue(test.compareTo(r)==0);
        
        camera=new Camera(new Point3D(0,0,0), new Vector(0,1,0), new Vector(0,0,-1));
         r=camera.construct_Ray_Through_Pixel(3, 3, 4.5, 4.5, 1, 9, 9);
         test=new Ray(new Point3D(0, 0, 0),new Vector(10.5,-10.5,-1));
        assertTrue(test.compareTo(r)==0);

    }
}
