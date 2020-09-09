import Elements.Camera;
import Geometries.Sphere;
import Geometries.Triangle;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import java.awt.Color;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
//this class test the functions of the sphere
public class SphereTest {
    @Test
    public void test_Find_Intersections() throws Exception//test of rays construction
    {
        //the sphere smaller then the view plane
        Camera camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        Ray r = camera.construct_Ray_Through_Pixel(3, 3, 1, 1, 1, 9, 9);
        Sphere sphere=new Sphere(new Point3D(0, 0, -3), 1,Color.white);
        int num_of_intersections=sphere.findIntersections(r).size();
        assertEquals(2, num_of_intersections, 1e-10);
        
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        r = camera.construct_Ray_Through_Pixel(3, 3, 0, 0, 1, 9, 9);
        sphere=new Sphere(new Point3D(0, 0, -3), 1,Color.white);
        num_of_intersections=sphere.findIntersections(r).size();
        assertEquals(0, num_of_intersections, 1e-10);
        
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        r = camera.construct_Ray_Through_Pixel(3, 3, 2, 2, 1, 9, 9);
        sphere=new Sphere(new Point3D(0, 0, -3), 1,Color.white);
        num_of_intersections=sphere.findIntersections(r).size();
        assertEquals(0, num_of_intersections, 1e-10);
        
        //the sphere bigger then the view plane
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        r = camera.construct_Ray_Through_Pixel(3, 3, 0, 0, 1, 9, 9);
        sphere=new Sphere(new Point3D(0, 0, -2), 3,Color.white);
        num_of_intersections=sphere.findIntersections(r).size();
        assertEquals(1, num_of_intersections, 1e-10);
        
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        r = camera.construct_Ray_Through_Pixel(3, 3, 0, 0, 1, 9, 9);
        sphere=new Sphere(new Point3D(0, 0, -2), 3,Color.white);
        num_of_intersections=sphere.findIntersections(r).size();
        assertEquals(1, num_of_intersections, 1e-10);
        
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        r = camera.construct_Ray_Through_Pixel(3, 3, 2, 2, 1, 9, 9);
        sphere=new Sphere(new Point3D(0, 0, -2), 3,Color.white);
        num_of_intersections=sphere.findIntersections(r).size();
        assertEquals(1, num_of_intersections, 1e-10);
    }
    public void test_getNormal()
    {
        Sphere sphere=new Sphere(new Point3D(0, 0, -3), 1,Color.white);
        //tests for 4 points on the sphere
        Vector N=sphere.getNormal(new Point3D(0, 0, -2));
        assertTrue(new Vector(0,0,1).compareTo(N)==0);
        
        N=sphere.getNormal(new Point3D(0, 0, -4));
        assertTrue(new Vector(0,0,-1).compareTo(N)==0);
        
        N=sphere.getNormal(new Point3D(1, 0, -3));
        assertTrue(new Vector(1,0,0).compareTo(N)==0);
        
        N=sphere.getNormal(new Point3D(-1, 0, -3));
        assertTrue(new Vector(-1,0,0).compareTo(N)==0);
    }
}
