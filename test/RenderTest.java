import Geometries.Sphere;
import Geometries.Triangle;
import Primitives.Point3D;
import java.awt.Color;
import org.junit.Test;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

//This class tests the functions of image writer
public class RenderTest {
    /*@Test
    test of the print grid
    public void testGrid() throws Exception
    {
        Scene scene=new Scene();
        ImageWriter imageWriter = new ImageWriter("grid", 500, 500, 500, 500);
         
        Render render = new Render(scene,imageWriter);
        Run the functions of the render to create the image
        render.renderImage();
        render.printGrid(50);
        render.getImageWriter().writeToimage();
    }*/
    @Test
    //Test of 4 triangles and 1 sphere.
    public void basicRendering() throws Exception{
         
        Scene scene = new Scene();
         
        scene.addGeometry(new Sphere(new Point3D(0.0, 0.0, -150),50));
         
        Triangle triangle = new Triangle(new Point3D( 100, 0, -149),
                                         new Point3D(  0, 100, -149),
                                         new Point3D( 100, 100, -149));
         
        Triangle triangle2 = new Triangle(new Point3D( 100, 0, -149),
                                          new Point3D(  0, -100, -149),
                                          new Point3D( 100,-100, -149),Color.blue);
         
        Triangle triangle3 = new Triangle(new Point3D(-100, 0, -149),
                                          new Point3D(  0, 100, -149),
                                          new Point3D(-100, 100, -149),Color.red);
         
        Triangle triangle4 = new Triangle(new Point3D(-100, 0, -149),
                                          new Point3D(  0,  -100, -149),
                                          new Point3D(-100, -100, -149),Color.yellow);
         
        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);
        
         
        ImageWriter imageWriter = new ImageWriter("Render test", 500, 500, 500, 500);
         
        Render render = new Render(scene,imageWriter);
         
        render.renderImage();
        render.printGrid(50);
        render.getImageWriter().writeToimage();
         
    }
/*    @Test
    //Test of 4 triangles and 1 sphere with colors.
    public void colorRendering() throws Exception{
         
        Scene scene = new Scene();
         
        scene.addGeometry(new Sphere(new Point3D(0.0, 0.0, -150),50,Color.gray));
         
//        Triangle triangle1 = new Triangle(new Point3D( 100, 0, -149),
//                                         new Point3D(  0, 100, -149),
//                                         new Point3D( 100, 100, -149),Color.gray);
         
//        Triangle triangle2 = new Triangle(new Point3D( 100, 0, -149),
//                                          new Point3D(  0, -100, -149),
//                                          new Point3D( 100,-100, -149),Color.blue);
         
//        Triangle triangle3 = new Triangle(new Point3D(-100, 0, -149),
//                                          new Point3D(  0, 100, -149),
//                                          new Point3D(-100, 100, -149),Color.green);
//         
//        Triangle triangle4 = new Triangle(new Point3D(-100, 0, -149),
//                                          new Point3D(  0,  -100, -149),
//                                          new Point3D(-100, -100, -149),Color.red);      
        //scene.addGeometry(triangle1);
//        scene.addGeometry(triangle2);
        //scene.addGeometry(triangle3);
        //scene.addGeometry(triangle4);
        
         
        ImageWriter imageWriter = new ImageWriter("color render test1", 500, 500, 500, 500);
         
        Render render = new Render(scene,imageWriter);
         
        render.renderImage();
        render.printGrid(50);
        render.getImageWriter().writeToimage();
         
    }*/

    
}
