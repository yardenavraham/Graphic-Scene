import Elements.Camera;
import Geometries.Triangle;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import java.awt.Color;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

//This class test the functions of the triangle
public class TriangleTest {

    @Test
    public void test_Find_Intersections() throws Exception//test of rays construction
    {
        //big triangle
        //outside the triangle
        Camera camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        Ray r = camera.construct_Ray_Through_Pixel(3, 3, 0, 2, 1, 9, 9);
        Triangle t=new Triangle(new Point3D(0,10,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2),Color.white);
        int num_of_intersections=t.findIntersections(r).size();
        assertEquals(0, num_of_intersections, 1e-10);
        
        //inside the triangle
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        r = camera.construct_Ray_Through_Pixel(3, 3, 1, 1, 1, 9, 9);
        t=new Triangle(new Point3D(0,10,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2),Color.white);
        num_of_intersections=t.findIntersections(r).size();
        assertEquals(1, num_of_intersections, 1e-10);
        
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        r = camera.construct_Ray_Through_Pixel(3, 3, 1, 0, 1, 9, 9);
        t=new Triangle(new Point3D(0,10,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2),Color.white);
        num_of_intersections=t.findIntersections(r).size();
        assertEquals(1, num_of_intersections, 1e-10);
        
       
        //small triangle
        //outside the triangle
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        r = camera.construct_Ray_Through_Pixel(3, 3, 0, 0, 1, 9, 9);
        t=new Triangle(new Point3D(0,1,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2),Color.white);
        num_of_intersections=t.findIntersections(r).size();
        assertEquals(0, num_of_intersections, 1e-10);
        
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        r = camera.construct_Ray_Through_Pixel(3, 3, 1, 0, 1, 9, 9);
        t=new Triangle(new Point3D(0,1,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2),Color.white);
        num_of_intersections=t.findIntersections(r).size();
        assertEquals(0, num_of_intersections, 1e-10);
        
        //inside the triangle
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        r = camera.construct_Ray_Through_Pixel(3, 3, 1, 1, 1, 9, 9);
        t=new Triangle(new Point3D(0,1,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2),Color.white);
        num_of_intersections=t.findIntersections(r).size();
        assertEquals(1, num_of_intersections, 1e-10);
    }
    
    @Test
    public void test_getNormal()
    {
        Triangle t=new Triangle(new Point3D(0,10,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2),Color.white);
        Vector N=t.getNormal(new Point3D(0, 0, 0));
        assertTrue(new Vector(0,0,-1).compareTo(N)==0);
    }

}
