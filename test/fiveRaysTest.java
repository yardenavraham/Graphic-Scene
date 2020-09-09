
import Geometries.Sphere;
import Geometries.Triangle;
import Primitives.Point3D;
import java.awt.Color;
import org.junit.Test;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;


public class fiveRaysTest {
//    @Test
//    public void oneTriangle() throws Exception
//    {
//        Scene scene = new Scene();
//         
//         
//        Triangle triangle = new Triangle(new Point3D( 100, 0, -149),
//                                         new Point3D(  0, 100, -149),
//                                         new Point3D( 100, 100, -149),Color.green);
//         
//
//         
//        scene.addGeometry(triangle);
//
//        
//         
//        ImageWriter imageWriter = new ImageWriter("One Triangle", 500, 500, 500, 500);
//         
//        Render render = new Render(scene,imageWriter);
//         
//        render.renderImage();
//        render.getImageWriter().writeToimage();
//    }
//    @Test
//    public void twoTriangles() throws Exception
//    {
//        Scene scene = new Scene();
//         
//         
//        Triangle triangle = new Triangle(new Point3D( 100, 0, -149),
//                                         new Point3D(  0, 100, -149),
//                                         new Point3D( 100, 100, -149),Color.green);
//         
//        Triangle triangle2 = new Triangle(new Point3D( 100, 0, -149),
//                                          new Point3D(  0, -100, -149),
//                                          new Point3D( 100,-100, -149),Color.blue);
//         
//
//         
//        scene.addGeometry(triangle);
//        scene.addGeometry(triangle2);
//
//        
//         
//        ImageWriter imageWriter = new ImageWriter("Two Triangles", 500, 500, 500, 500);
//         
//        Render render = new Render(scene,imageWriter);
//         
//        render.renderImage();
//        render.getImageWriter().writeToimage();
//    }
    @Test
    public void threeTriangles() throws Exception
    {
        Scene scene = new Scene();
         
         
        Triangle triangle = new Triangle(new Point3D( 100, 0, -149),
                                         new Point3D(  0, 100, -149),
                                         new Point3D( 100, 100, -149),Color.green);
         
        Triangle triangle2 = new Triangle(new Point3D( 100, 0, -149),
                                          new Point3D(  0, -100, -149),
                                          new Point3D( 100,-100, -149),Color.blue);
         
        Triangle triangle3 = new Triangle(new Point3D(-100, 0, -149),
                                          new Point3D(  0, 100, -149),
                                          new Point3D(-100, 100, -149),Color.red);
         

         
        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        
         
        ImageWriter imageWriter = new ImageWriter("Three Triangles", 500, 500, 500, 500);
         
        Render render = new Render(scene,imageWriter);
         
        render.renderImage();
        render.getImageWriter().writeToimage();
    }
//    @Test
//    public void fourTriangles() throws Exception
//    {
//        Scene scene = new Scene();
//         
//         
//        Triangle triangle = new Triangle(new Point3D( 100, 0, -149),
//                                         new Point3D(  0, 100, -149),
//                                         new Point3D( 100, 100, -149),Color.green);
//         
//        Triangle triangle2 = new Triangle(new Point3D( 100, 0, -149),
//                                          new Point3D(  0, -100, -149),
//                                          new Point3D( 100,-100, -149),Color.blue);
//         
//        Triangle triangle3 = new Triangle(new Point3D(-100, 0, -149),
//                                          new Point3D(  0, 100, -149),
//                                          new Point3D(-100, 100, -149),Color.red);
//         
//        Triangle triangle4 = new Triangle(new Point3D(-100, 0, -149),
//                                          new Point3D(  0,  -100, -149),
//                                          new Point3D(-100, -100, -149),Color.yellow);
//         
//        scene.addGeometry(triangle);
//        scene.addGeometry(triangle2);
//        scene.addGeometry(triangle3);
//        scene.addGeometry(triangle4);
//        
//         
//        ImageWriter imageWriter = new ImageWriter("Four Triangles", 500, 500, 500, 500);
//         
//        Render render = new Render(scene,imageWriter);
//         
//        render.renderImage();
//        render.getImageWriter().writeToimage();
//    }
}
