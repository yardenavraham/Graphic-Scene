import Elements.Camera;
import Geometries.Plane;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import java.awt.Color;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
//this class test the functions of the Plane

public class PlaneTest {
    @Test
    public void test_Find_Intersections() throws Exception//test of rays construction
    {
        //Parallel plane
        Camera camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        Ray r = camera.construct_Ray_Through_Pixel(3, 3, 1, 1, 1, 9, 9);
        Plane plane=new Plane(new Vector(0,0,1), new Point3D(0, 0, -2),Color.white);
        Point3D p=plane.findIntersections(r).get(0);
        assertEquals(0, p.getX().getCoordinate(), 1e-10);
        assertEquals(0, p.getY().getCoordinate(), 1e-10);
        assertEquals(-2, p.getZ().getCoordinate(), 1e-10);
        
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        r = camera.construct_Ray_Through_Pixel(3, 3, 0, 0, 1, 9, 9);
        plane=new Plane(new Vector(0,0,1), new Point3D(0, 0, -2),Color.white);
        p=plane.findIntersections(r).get(0);
        assertEquals(-6, p.getX().getCoordinate(), 1e-10);
        assertEquals(6, p.getY().getCoordinate(), 1e-10);
        assertEquals(-2, p.getZ().getCoordinate(), 1e-10);
        
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        r = camera.construct_Ray_Through_Pixel(3, 3, 2, 2, 1, 9, 9);
        plane=new Plane(new Vector(0,0,1), new Point3D(0, 0, -2),Color.white);
        p=plane.findIntersections(r).get(0);
        assertEquals(6, p.getX().getCoordinate(), 1e-10);
        assertEquals(-6, p.getY().getCoordinate(), 1e-10);
        assertEquals(-2, p.getZ().getCoordinate(), 1e-10);
        
        //diagonal plane
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        r = camera.construct_Ray_Through_Pixel(3, 3, 0, 0, 1, 9, 9);
        plane=new Plane(new Vector(0,1,-1), new Point3D(0, 0, -2),Color.white);
        p=plane.findIntersections(r).get(0);
        assertEquals(-1.5, p.getX().getCoordinate(), 1e-10);
        assertEquals(1.5, p.getY().getCoordinate(), 1e-10);
        assertEquals(-0.5, p.getZ().getCoordinate(), 1e-10);
//        assertEquals(3, p.getX().getCoordinate(), 1e-10);
//        assertEquals(-3, p.getY().getCoordinate(), 1e-10);
//        assertEquals(1, p.getZ().getCoordinate(), 1e-10);
        
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        r = camera.construct_Ray_Through_Pixel(3, 3, 1, 1, 1, 9, 9);
        plane=new Plane(new Vector(0,1,-1), new Point3D(0, 0, -2),Color.white);
        p=plane.findIntersections(r).get(0);
        assertEquals(0, p.getX().getCoordinate(), 1e-10);
        assertEquals(0, p.getY().getCoordinate(), 1e-10);
        assertEquals(-2, p.getZ().getCoordinate(), 1e-10);
        
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        r = camera.construct_Ray_Through_Pixel(3, 3, 2, 2, 1, 9, 9);
        plane=new Plane(new Vector(0,1,-1), new Point3D(0, 0, -2),Color.white);
        p=plane.findIntersections(r).get(0);
        assertEquals(-3, p.getX().getCoordinate(), 1e-10);
        assertEquals(3, p.getY().getCoordinate(), 1e-10);
        assertEquals(1, p.getZ().getCoordinate(), 1e-10);
    }
}
