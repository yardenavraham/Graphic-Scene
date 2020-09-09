import Elements.PointLight;
import Geometries.Plane;
import Geometries.Sphere;
import Geometries.Triangle;
import Primitives.Material;
import Primitives.Point3D;
import Primitives.Vector;
import java.awt.Color;
import org.junit.Test;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

//This class checkes the function on point light
public class PointLightTest {

    //This is test of one sphere with spot light on it
    @Test
    public void pointLightTest() throws Exception {

        Scene scene = new Scene();
        scene.setScreenDistance(100);
        Sphere sphere = new Sphere(new Point3D(0.0, 0.0, -1000), 800);
        Material m = sphere.getMaterial();
        m.setnShininess(20);
        sphere.setMaterial(m);
        sphere.setEmmission(new Color(0, 0, 100));
        scene.addGeometry(sphere);
        scene.addLight(new PointLight(new Point3D(-200, 0, -100),
                0, 0.00001, 0.000005, new Color(255, 100, 100)));

        ImageWriter imageWriter = new ImageWriter("Point test", 500, 500, 500, 500);

        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.getImageWriter().writeToimage();

    }

    //This is test with 2 triangles, sphere and spot light
    @Test
    public void pointLightTest2() throws Exception {

        Scene scene = new Scene();
        scene.setScreenDistance(100);

        Triangle triangle = new Triangle(new Point3D(3500, 3500, -2000),
                                         new Point3D(-3500, -3500, -1000),
                                         new Point3D(3500, -3500, -2000));

        Triangle triangle2 = new Triangle(new Point3D(3500, 3500, -2000),
                                          new Point3D(-3500, 3500, -1000),
                                          new Point3D(-3500, -3500, -1000));
        

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new PointLight(new Point3D(200, 200, -100),
                0, 0.000001, 0.0000005, new Color(255, 100, 100)));

        ImageWriter imageWriter = new ImageWriter("Point test 2", 500, 500, 500, 500);

        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.getImageWriter().writeToimage();

    }
    
    //This is test of a triangle with spot light on it
    @Test
	public void pointLightTest3() throws Exception{
		
		Scene scene = new Scene();
                scene.setScreenDistance(25);
		Triangle triangle = new Triangle(new Point3D(  2000,  3000, -500),
				 		 new Point3D( -2000, -1000, -200),
				 		 new Point3D(  2000, -1000, -500),
                                                 Color.orange);
		scene.addGeometry(triangle);
		scene.addLight(new PointLight(new Point3D(1200, -500, -300),
                0, 0.000001, 0.0000005, new Color(255, 100, 100)));
	
		ImageWriter imageWriter = new ImageWriter("pointLight test3", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter);
		
		render.renderImage();
		render.getImageWriter().writeToimage();
		
	}

    /*@Test
    public void planeTest() throws Exception {
        Scene scene = new Scene();
        Sphere sphere = new Sphere(new Point3D(0, 0, -1000), 800);
        sphere.getMaterial().setnShininess(20);
        sphere.setEmmission(new Color(0, 0, 100));

        Triangle triangle = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, -3500, -1000),
                new Point3D(3500, -3500, -2000));

        Triangle triangle2 = new Triangle(new Point3D(3500, 3500, -2000),
                new Point3D(-3500, 3500, -1000),
                new Point3D(-3500, -3500, -1000));

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new PointLight(new Point3D(-200, -200, -100), (float)0, (float)0.000001, (float)0.0000005, new Color(255, 100, 100)));
        ImageWriter imageWriter = new ImageWriter("point light test", 500, 500, 500, 500);

        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.getImageWriter().writeToimage();
    }*/
}
