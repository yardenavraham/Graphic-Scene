
import Elements.AmbientLight;
import Elements.SpotLight;
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

//This class tests the functions - constructReflectedRay and constructRefractedRay
public class RecursiveTest {

    //A sphere within a sphere
    @Test
	public void recursiveTest() throws Exception
       {
		Scene scene = new Scene();
		scene.setScreenDistance(300);
		scene.setAmbientLight(new AmbientLight (0.1,Color.white));
		Sphere sphere = new Sphere(new Point3D(0.0, 0.0, -1000),500);
		Material M=sphere.getMaterial();
                M.setnShininess(20);
                M.setKt(0.5);
                sphere.setMaterial(M);
		sphere.setEmmission(new Color(0, 0, 100));
		scene.addGeometry(sphere);
		
		Sphere sphere2 = new Sphere(new Point3D(0.0, 0.0, -1000),250);
                Material M2=sphere2.getMaterial();
		M2.setnShininess(20);
                M2.setKt(0);
		sphere2.setEmmission(new Color(100, 20, 20));
		scene.addGeometry(sphere2);
		
		scene.addLight(new SpotLight(new Vector(new Point3D(2, 2, -3)), new Point3D(-200, -200, -150),0.1, 0.00001, 0.000005, new Color(255, 100, 100)));
		ImageWriter imageWriter = new ImageWriter("Recursive Test 1", 500, 500, 500, 500);
		
		Render render = new Render(scene, imageWriter);
		
		render.renderImage();
		render.getImageWriter().writeToimage();
	}

    //A sphere within a sphere reflected in two mirrors
    @Test
    public void recursiveTest2() throws Exception {

        Scene scene = new Scene();
        scene.setScreenDistance(300);
        scene.setAmbientLight(new AmbientLight(0.1, Color.white));
        Sphere sphere = new Sphere(new Point3D(-550, -500, -1000), 300);
        Material M = sphere.getMaterial();
        M.setnShininess(20);
        M.setKt(0.5);
        sphere.setMaterial(M);
        sphere.setEmmission(new Color(0, 0, 100));
        scene.addGeometry(sphere);

        Sphere sphere2 = new Sphere(new Point3D(-550, -500, -1000), 150);
        Material M2 = sphere2.getMaterial();
        M2.setnShininess(20);
        M2.setKt(0);
        sphere2.setMaterial(M2);
        sphere2.setEmmission(new Color(100, 20, 20));
        scene.addGeometry(sphere2);
		
        Triangle triangle = new Triangle(new Point3D(1500, -1500, -1500),
                new Point3D(-1500, 1500, -1500),
                new Point3D(200, 200, -375));
        
		
        Triangle triangle2 = new Triangle(new Point3D(1500, -1500, -1500),
                new Point3D(-1500, 1500, -1500),
                new Point3D(-1500, -1500, -1500));
		
        Color emmission = new Color(20, 20, 20);
        triangle.setEmmission(emmission);
        triangle2.setEmmission(emmission);

        Material M3 = triangle.getMaterial();
        M3.setKr(1);
        triangle.setMaterial(M3);
                
        Material M4 = triangle2.getMaterial();
        M4.setKr(0.5);
        triangle2.setMaterial(M4);
        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Vector(-2, -2, -3), new Point3D(200, 200, -150), 0, 0.00001, 0.000005, new Color(255, 100, 100)));

        ImageWriter imageWriter = new ImageWriter("Recursive Test 2", 500, 500, 500, 500);

        Render render = new Render(scene, imageWriter);

        render.renderImage();
        //render.printGrid(50);
        render.getImageWriter().writeToimage();

    }

    /*
    @Test
	public void recursiveTest3() throws Exception{
		
		Scene scene = new Scene();
		scene.setScreenDistance(300);
		scene.setAmbientLight(new AmbientLight(0.1,Color.white));
		Sphere sphere = new Sphere(new Point3D(0, 0, -1000),300);
		Material M=sphere.getMaterial();
                M.setnShininess(20);
                M.setKt(0.5);
                sphere.setMaterial(M);
		sphere.setEmmission(new Color(0, 0, 100));
		scene.addGeometry(sphere);
		
		Sphere sphere2 = new Sphere(new Point3D(0, 0, -1000),150);
		Material M2=sphere2.getMaterial();
                M2.setnShininess(20);
                M2.setKt(0);
		sphere2.setEmmission(new Color(100, 20, 20));
		scene.addGeometry(sphere2);
		
		Triangle triangle = new Triangle(new Point3D(  2000, -1000, -1500),
				                 new Point3D( -1000,  2000, -1500),
			                         new Point3D(  700,  700, -375));
		
		Triangle triangle2 = new Triangle(new Point3D(  2000, -1000, -1500),
						  new Point3D( -1000,  2000, -1500),
						  new Point3D( -1000, -1000, -1500));
		
		triangle.setEmmission(new Color(20, 20, 20));
		triangle2.setEmmission(new Color(20, 20, 20));
		Material M3=triangle.getMaterial();
                M3.setKr(1);
                triangle.setMaterial(M3);
		Material M4=triangle2.getMaterial();
                M4.setKr(0.5);
                triangle2.setMaterial(M4);
		scene.addGeometry(triangle);
		scene.addGeometry(triangle2);

	
                scene.addLight(new SpotLight(new Vector(new Point3D(-2, -2, -3)), new Point3D(200, 200, -150),0, 0.00001, 0.000005 ,new Color(255, 100, 100)));
		ImageWriter imageWriter = new ImageWriter("Recursive Test 3", 500, 500, 500, 500);
		
		Render render = new Render(scene, imageWriter);
		
		render.renderImage();
                
		render.getImageWriter().writeToimage();
	} 
*/
}
