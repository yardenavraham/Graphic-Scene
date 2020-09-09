
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

//This class test the functions of the spot light
public class SpotLightTest {
    
    
	//This is test of one sphere with spot light on it
	@Test
	public void spotLightTest() throws Exception{
		
		Scene scene = new Scene();
                scene.setScreenDistance(100);
		Sphere sphere = new Sphere( new Point3D(0.0, 0.0, -1000),800);
		Material m=sphere.getMaterial();
		m.setnShininess(20);
                sphere.setMaterial(m);
		sphere.setEmmission(new Color(0, 0, 100));
		scene.addGeometry(sphere);
		scene.addLight(new SpotLight(new Vector(2, 2, -3),new Point3D(-200, 0, -100), 
					    0, 0.00001, 0.000005,new Color(255, 100, 100)));
	
		ImageWriter imageWriter = new ImageWriter("Spot test", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter);
		
		render.renderImage();
		render.getImageWriter().writeToimage();
		
	}
        
        //This is test of a triangle with spot light on it
        @Test
	public void spotLightTest2() throws Exception{
		
		Scene scene = new Scene();
                scene.setScreenDistance(25);

                Triangle triangle = new Triangle(new Point3D(  2000,  3000, -500),
				 		 new Point3D( -2000, -1000, -200),
				 		 new Point3D(  2000, -1000, -500),
                                                 Color.orange);
		scene.addGeometry(triangle);
		scene.addLight(new SpotLight(new Vector(2, 2, -3),new Point3D(1200, -500, -300), 
					    0, 0.000001, 0.0000005,new Color(255, 100, 100)));
	
		ImageWriter imageWriter = new ImageWriter("Spot test2", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter);
		
		render.renderImage();
		render.getImageWriter().writeToimage();
		
	}
        
        //This is test with 2 triangles and spot light
        @Test
	public void spotLightTest3() throws Exception{
	
		Scene scene = new Scene();
		scene.setScreenDistance(100);
		Triangle triangle = new Triangle(new Point3D(  3500,  3500, -2000),
				 		 new Point3D( -3500, -3500, -1000),
				 		 new Point3D(  3500, -3500, -2000));

		Triangle triangle2 = new Triangle(new Point3D(  3500,  3500, -2000),
				  		  new Point3D( -3500,  3500, -1000),
				  		  new Point3D( -3500, -3500, -1000));
		//add 2 triangles to the test
		scene.addGeometry(triangle);
		scene.addGeometry(triangle2);
		
		scene.addLight(new SpotLight( new Vector(-2, -2, -3), new Point3D(200, 200, -100), 
					   0, 0.000001, 0.0000005,new Color(255, 100, 100)));
	
		
		ImageWriter imageWriter = new ImageWriter("Spot test 3", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter);
		
		render.renderImage();
		render.getImageWriter().writeToimage();
		
	}
    @Test
    public void spotLightTest4() throws Exception{
		
		Scene scene = new Scene();
		scene.setScreenDistance(200);
		Sphere sphere = new Sphere(new Point3D(0.0, 0.0, -1000),500);
                Material m=sphere.getMaterial();
		m.setnShininess(20);
                sphere.setMaterial(m);
		sphere.setEmmission(new Color(0, 0, 100));
		scene.addGeometry(sphere);
		
		Triangle triangle = new Triangle(new Point3D(-125, -225, -260),
										 new Point3D(-225, -125, -260),
										 new Point3D(-225, -225, -270));
		
		triangle.setEmmission(new Color (0, 0, 100));
                m=triangle.getMaterial();
		m.setnShininess(4);
		triangle.setMaterial(m);
		scene.addGeometry(triangle);
		
		scene.addLight(new SpotLight(new Vector(2, 2, -3), new Point3D(-200, -200, -150), 
					    0.1, 0.00001, 0.000005,new Color(255, 100, 100)));
	
		ImageWriter imageWriter = new ImageWriter("Spot test 4", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter);
		
		render.renderImage();
		render.getImageWriter().writeToimage();
		
	}
    @Test
    public void spotLightTest4T1() throws Exception{
		
		Scene scene = new Scene();
		scene.setScreenDistance(200);
		Sphere sphere = new Sphere(new Point3D(0.0, 0.0, -1000),500);
                Material m=sphere.getMaterial();
		m.setnShininess(20);
                sphere.setMaterial(m);
		sphere.setEmmission(new Color(0, 0, 100));
		scene.addGeometry(sphere);
		
		Triangle triangle = new Triangle(new Point3D(-83, -150, -260),
										 new Point3D(-150, -83, -260),
										 new Point3D(-150, -150, -270));
		
		triangle.setEmmission(new Color (0, 0, 100));
                m=triangle.getMaterial();
		m.setnShininess(4);
		triangle.setMaterial(m);
		scene.addGeometry(triangle);
		
		scene.addLight(new SpotLight(new Vector(2, 2, -3), new Point3D(-200, -200, -150), 
					    0.1, 0.00001, 0.000005,new Color(255, 100, 100)));
	
		ImageWriter imageWriter = new ImageWriter("Spot test 4 t1", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter);
		
		render.renderImage();
		render.getImageWriter().writeToimage();
		
	}
    @Test
    public void spotLightTest4T2() throws Exception{
		
		Scene scene = new Scene();
		scene.setScreenDistance(200);
		Sphere sphere = new Sphere(new Point3D(0.0, 0.0, -1000),500);
                Material m=sphere.getMaterial();
		m.setnShininess(20);
                sphere.setMaterial(m);
		sphere.setEmmission(new Color(0, 0, 100));
		scene.addGeometry(sphere);
		
		Triangle triangle = new Triangle(new Point3D(-113.6, -204.5, -260),
										 new Point3D(-204.5, -113.6, -260),
										 new Point3D(-204.5, -204.5, -270));
		
		triangle.setEmmission(new Color (0, 0, 100));
                m=triangle.getMaterial();
		m.setnShininess(4);
		triangle.setMaterial(m);
		scene.addGeometry(triangle);
		
		scene.addLight(new SpotLight(new Vector(2, 2, -3), new Point3D(-200, -200, -150), 
					    0.1, 0.00001, 0.000005,new Color(255, 100, 100)));
	
		ImageWriter imageWriter = new ImageWriter("Spot test 4 t2", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter);
		
		render.renderImage();
		render.getImageWriter().writeToimage();
		
	}
     @Test
    public void spotLightTest4L1() throws Exception{
		
		Scene scene = new Scene();
		scene.setScreenDistance(200);
		Sphere sphere = new Sphere(new Point3D(0.0, 0.0, -1000),500);
                Material m=sphere.getMaterial();
		m.setnShininess(20);
                sphere.setMaterial(m);
		sphere.setEmmission(new Color(0, 0, 100));
		scene.addGeometry(sphere);
		
		Triangle triangle = new Triangle(new Point3D(-125, -225, -260),
										 new Point3D(-225, -125, -260),
										 new Point3D(-225, -225, -270));
		
		triangle.setEmmission(new Color (0, 0, 100));
                m=triangle.getMaterial();
		m.setnShininess(4);
		triangle.setMaterial(m);
		scene.addGeometry(triangle);
		
		scene.addLight(new SpotLight(new Vector(2, 2, -3), new Point3D(-300, -300, -150), 
					    0.1, 0.00001, 0.000005,new Color(255, 100, 100)));
	
		ImageWriter imageWriter = new ImageWriter("Spot test 4 l1", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter);
		
		render.renderImage();
		render.getImageWriter().writeToimage();
		
	}
     @Test
    public void spotLightTest4L2() throws Exception{
		
		Scene scene = new Scene();
		scene.setScreenDistance(200);
		Sphere sphere = new Sphere(new Point3D(0.0, 0.0, -1000),500);
                Material m=sphere.getMaterial();
		m.setnShininess(20);
                sphere.setMaterial(m);
		sphere.setEmmission(new Color(0, 0, 100));
		scene.addGeometry(sphere);
		
		Triangle triangle = new Triangle(new Point3D(-125, -225, -260),
										 new Point3D(-225, -125, -260),
										 new Point3D(-225, -225, -270));
		
		triangle.setEmmission(new Color (0, 0, 100));
                m=triangle.getMaterial();
		m.setnShininess(4);
		triangle.setMaterial(m);
		scene.addGeometry(triangle);
		
		scene.addLight(new SpotLight(new Vector(2, 2, -3), new Point3D(-222.2, -222.2, -150), 
					    0.1, 0.00001, 0.000005,new Color(255, 100, 100)));
	
		ImageWriter imageWriter = new ImageWriter("Spot test 4 l2", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter);
		
		render.renderImage();
		render.getImageWriter().writeToimage();
		
	}
}
