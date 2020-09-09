package renderer;

import Elements.LightSource;
import Geometries.FlatGeometry;
import Geometries.Geometry;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import scene.Scene;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//This class find intersections with scene objects and write to image
public class Render {

    private Scene _scene;
    private ImageWriter _imageWriter;
    private final int RECURSION_LEVEL = 3;

    public Render(Scene _scene, ImageWriter _imageWriter) {
        this._scene = _scene;
        this._imageWriter = _imageWriter;
    }

    public Render(Render r) {
        this._scene = r.getScene();
        this._imageWriter = r.getImageWriter();
    }
// ***************** Getters/Setters ********************** //

    public Scene getScene() {
        return new Scene(_scene);
    }

    public void setScene(Scene _scene) {
        this._scene = _scene;
    }

    public ImageWriter getImageWriter() {
        return new ImageWriter(_imageWriter);
    }

    public void setImageWriter(ImageWriter _imageWriter) {
        this._imageWriter = _imageWriter;
    }

// ***************** functions ********************** //
    //add grid to the image
    public void printGrid(int interval) {
        for (int i = 0; i < _imageWriter.getHeight(); i++) {
            _imageWriter.writePixel(0, i, Color.white);
        }
        for (int i = 0; i < _imageWriter.getWidth(); i++) {
            _imageWriter.writePixel(i, 0, Color.white);
        }
        for (int i = 1; i <= _imageWriter.getHeight(); i++) {
            for (int j = 1; j <= _imageWriter.getWidth(); j++) {
                if (i % interval == 0 || j % interval == 0) {
                    _imageWriter.writePixel(j - 1, i - 1, Color.white);
                }
            }
        }
    }

    //This funtion find all the intersections of the ray with the geometries
    private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray) {

        Iterator<Geometry> geometries = getScene().getGeometriesIterator();
        Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();

        while (geometries.hasNext()) {//pass over the geometries 
            Geometry geometry = geometries.next();
            List<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);
            if (!geometryIntersectionPoints.isEmpty()) {
                intersectionPoints.put(geometry, geometryIntersectionPoints);
            }
        }

        return intersectionPoints;
    }

    //This function return the closest intersection point from the list
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints) {

        double distance = Double.MAX_VALUE;
        Point3D P0 = getScene().getCamera().getP0();
        Map<Geometry, Point3D> minDistancePoint = new HashMap<Geometry, Point3D>();

        for (Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet()) {//pass the list of the intersections
            for (Point3D point : entry.getValue()) {
                if (P0.distance(point) < distance) {
                    minDistancePoint.clear();
                    minDistancePoint.put(entry.getKey(), new Point3D(point));
                    distance = P0.distance(point);
                }
            }

        }
        return minDistancePoint;
    }

    //This function calculate the diffuse color
    private Color calcDiffusiveComp(double kd, Vector N, Vector L, Color Il) {
        L.normalize();
        N.normalize();
        double scaleFactor = Math.abs(kd * (N.dot_product(L)));//calculate the scale factor,without meaning to direction

        int red = (int) Math.min(255, Il.getRed() * scaleFactor);
        int green = (int) Math.min(255, Il.getGreen() * scaleFactor);
        int blue = (int) Math.min(255, Il.getBlue() * scaleFactor);

        Color diffusive = new Color(red, green, blue);
        return diffusive;
    }

    //This function calculate the Specular color
    private Color calcSpecularComp(double ks, Vector V, Vector N, Vector L, int nShininess, Color Il) {
        V.normalize();//normelize the vectors
        N.normalize();
        L.normalize();
        N.scale(2 * (L.dot_product(N)));//הכפלת הנורמל בהיטל הכפול של הוקטור בין האור והנקודה על הנורמל
        Vector R = new Vector(L.subtract(N));//הוקטור המשתקף מעבר לנורמל של הוקטור בין הנק לאור
        R.normalize();

        double scaleFactor = 0;
        if (V.dot_product(R) > 0) {//בודק אם הוקטור של הספוקלר בכיוון המצלמה
            scaleFactor = ks * Math.pow(V.dot_product(R), nShininess);//calculate the scale factor
        }
        int red = (int) Math.min(255, Il.getRed() * scaleFactor);//takes the minimum value
        int green = (int) Math.min(255, Il.getGreen() * scaleFactor);
        int blue = (int) Math.min(255, Il.getBlue() * scaleFactor);

        Color specular = new Color(red, green, blue);
        return specular;
    }

    //בודקת האם הנקודה מוסתרת
    private boolean occluded(LightSource light, Point3D point, Geometry geometry) {
        Vector lightDirection = light.getL(point);
        lightDirection.scale(-1);
        lightDirection.normalize();
        Point3D geometryPoint = new Point3D(point);
        Vector epsVector = new Vector(geometry.getNormal(point));
        epsVector.scale(2);
        geometryPoint = new Point3D(geometryPoint.add(epsVector));
        Ray lightRay = new Ray(geometryPoint, lightDirection);
        Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(lightRay);
        // Flat geometry cannot self intersect
        if (geometry instanceof FlatGeometry) {// Flat geometry cannot self intersect
            intersectionPoints.remove(geometry);
        }
        for (Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet()) {
            //אם הגיאומטריה המסתירה שקופה או מראתית אז הנק לא מוסתרת
            if (entry.getKey().getMaterial().getKt() == 0 && entry.getKey().getMaterial().getKr() == 0) {
                return true;
            }
        }
        return false;

    }

    //This function return the color of the point
    private Color calcColor(Geometry geometry, Point3D point, Ray inRay) {
        return calcColor(geometry, point, inRay, 0);
    }

    //This function return the color of the point with the level of the recursion
    private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level) {

        if (level == RECURSION_LEVEL) {//אם הרקורסיה הסתיימה מחזירים שחור
            return Color.black;
        }

        Color ambientLight = getScene().getAmbientLight().getIntensity(point);
        Color emissionLight = geometry.getEmmission();
        Color diffuseLight = Color.black;
        Color specularLight = Color.black;
        Iterator<LightSource> lights = getScene().getLightsIterator();
        while (lights.hasNext()) {//pass over the diffrent lights in the scene
            LightSource light = lights.next();
            if (!occluded(light, point, geometry)) {//האם הנקודה לא מוסתרת

                Color temp1 = calcDiffusiveComp(geometry.getMaterial().getKd(),
                        geometry.getNormal(point),
                        light.getL(point),
                        light.getIntensity(point));
                Color newDiffuseLight = new Color(Math.min(255, diffuseLight.getRed() + temp1.getRed()),//calculate the new diffusive light
                        Math.min(255, diffuseLight.getGreen() + temp1.getGreen()),
                        Math.min(255, diffuseLight.getBlue() + temp1.getGreen()));
                diffuseLight = newDiffuseLight;
                Color temp2 = calcSpecularComp(geometry.getMaterial().getKs(),
                        new Vector(getScene().getCamera().getP0().subtract(point)),
                        geometry.getNormal(point),
                        light.getL(point),
                        geometry.getMaterial().getnShininess(),
                        light.getIntensity(point));
                Color newSpecularLight = new Color(Math.min(255, specularLight.getRed() + temp2.getRed()),//calculate the new specular light
                        Math.min(255, specularLight.getGreen() + temp2.getGreen()),
                        Math.min(255, specularLight.getBlue() + temp2.getGreen()));
                specularLight = newSpecularLight;

            }
        }

        // Recursive call for a reflected ray
        Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);
        Entry<Geometry, Point3D> reflectedEntry = findClosestIntersection(reflectedRay, geometry);
        Color reflectedColor = new Color(0, 0, 0);
        if (reflectedEntry != null)//if the ray has intersection points with geometries
        {
            reflectedColor = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), reflectedRay, level + 1);
        }
        double Kr = geometry.getMaterial().getKr();

        //calculate the reflected light with Kr
        Color reflectedLight = new Color((int) (Kr * reflectedColor.getRed()), (int) (Kr * reflectedColor.getGreen()), (int) (Kr * reflectedColor.getBlue()));
        // Recursive call for a refracted ray
        Ray refractedRay = constructRefractedRay(geometry, point, inRay);
        Entry<Geometry, Point3D> refractedEntry = findClosestIntersection(refractedRay, geometry);
        Color refractedColor = new Color(0, 0, 0);

        if (refractedEntry != null)//if the ray has intersection points with geometries
        {
            refractedColor = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level + 1);
        }
        double kt = geometry.getMaterial().getKt();

        //calculate the reflected light with Kt
        Color refractedLight = new Color((int) (kt * refractedColor.getRed()), (int) (kt * refractedColor.getGreen()), (int) (kt * refractedColor.getBlue()));

        int red = Math.min(255, ambientLight.getRed() + emissionLight.getRed() + diffuseLight.getRed() + specularLight.getRed() + reflectedLight.getRed() + refractedLight.getRed());//takes the minimum
        int green = Math.min(255, ambientLight.getGreen() + emissionLight.getGreen() + diffuseLight.getGreen() + specularLight.getGreen() + reflectedLight.getGreen() + refractedLight.getGreen());
        int blue = Math.min(255, ambientLight.getBlue() + emissionLight.getBlue() + diffuseLight.getBlue() + specularLight.getBlue() + reflectedLight.getBlue() + refractedLight.getBlue());

        return new Color(red, green, blue);
    }

    //This function gets ray and geometry and find the closest intersection
    private Entry<Geometry, Point3D> findClosestIntersection(Ray ray, Geometry geometry) {
        Map<Geometry, List<Point3D>> mapPointsList = getSceneRayIntersections(ray);
        if (geometry instanceof FlatGeometry)//אם גאומטריה שטוחה היא לא יכולה להשתקף או לשקף את עצמה
        {
            mapPointsList.remove(geometry);
        }
        if (mapPointsList.size() == 0) {//אם אין נקודות חיתוך
            return null;
        }
        Map<Geometry, Point3D> mapClosestPoint = getClosestPoint(mapPointsList);
        Entry<Geometry, Point3D> entry = mapClosestPoint.entrySet().iterator().next();
        return entry;

    }

    //This function calculates the reflection
    public Ray constructReflectedRay(Vector N, Point3D point, Ray ray)//בניית קרן משתקפת
    {
        N.normalize();
        Vector D = ray.getDirection();
        D.normalize();
        N.scale(2 * (D.dot_product(N)));//הכפלת הנורמל בהיטל הכפול של הוקטור בין האור והנקודה על הנורמל
        Vector R = new Vector(D.subtract(N));//הוקטור המשתקף מעבר לנורמל של הוקטור בין הנק לאור
        R.normalize();
        Point3D point1 = new Point3D(point.add(N));//floating point
        return new Ray(point1, R);
    }

    //This function calculates the refraction
    public Ray constructRefractedRay(Geometry geometry, Point3D point, Ray ray)//בניית קרן חודרת
    {
        Vector N = new Vector(geometry.getNormal(point));

        N.scale(-2);
        Point3D point1 = new Point3D(point.add(N));//floating point
        return new Ray(point1, ray.getDirection());

    }

//    This function pass over the pixels, and create the image
    public void renderImage() throws Exception {
        double widthPixel = getImageWriter().getWidth() / getImageWriter().getNx();
        double heightPixel = getImageWriter().getHeight() / getImageWriter().getNy();
        for (int i = 0; i < _imageWriter.getWidth(); i++) {//pass over the pixels
            for (int j = 0; j < _imageWriter.getHeight(); j++) {
                ArrayList<Ray> rays = new ArrayList<Ray>();
               //Create 5 rays for each pixel
                rays.add(getScene().getCamera().construct_Ray_Through_Pixel(getImageWriter().getNx(), getImageWriter().getNy(), i, j,
                        getScene().getScreenDistance(), getImageWriter().getWidth(),
                        getImageWriter().getHeight()));//Send a ray to the top left corner of the pixel
                rays.add(getScene().getCamera().construct_Ray_Through_Pixel(getImageWriter().getNx(), getImageWriter().getNy(), i + widthPixel, j,
                        getScene().getScreenDistance(), getImageWriter().getWidth(),
                        getImageWriter().getHeight()));//Send a ray to the top right corner of the pixel
                rays.add(getScene().getCamera().construct_Ray_Through_Pixel(getImageWriter().getNx(), getImageWriter().getNy(), i + widthPixel / 2.0, j + heightPixel / 2.0,
                        getScene().getScreenDistance(), getImageWriter().getWidth(),
                        getImageWriter().getHeight()));//Send a ray to the middle of the pixel
                rays.add(getScene().getCamera().construct_Ray_Through_Pixel(getImageWriter().getNx(), getImageWriter().getNy(), i, j + heightPixel,
                        getScene().getScreenDistance(), getImageWriter().getWidth(),
                        getImageWriter().getHeight()));//Send a ray to the bottom left corner of the pixel
                rays.add(getScene().getCamera().construct_Ray_Through_Pixel(getImageWriter().getNx(), getImageWriter().getNy(), i + widthPixel, j + heightPixel,
                        getScene().getScreenDistance(), getImageWriter().getWidth(),
                        getImageWriter().getHeight()));//Send a ray to the bottom right corner of the pixel
                ArrayList<Color> colors = new ArrayList<Color>(5);//list for all colors
                for (int k = 0; k < 5; k++) {//pass over the rays
                    Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(rays.get(k));//find the intersetions
                    if (intersectionPoints.isEmpty()) {//pixel without intersection point
                        colors.add(getScene().getBackground());

                    } else {//find intersetion
                        Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
                        for (Entry<Geometry, Point3D> g : closestPoint.entrySet()) {
                            Color color = calcColor(g.getKey(), g.getValue(), rays.get(k));
                            colors.add(color);
                        }
                    }
                }
                int red=0;
                int green=0;
                int blue=0;
                for (int k = 0; k < 5; k++) {//calculate the sum of the colors
                    red+=colors.get(k).getRed();
                    green+=colors.get(k).getGreen();
                    blue+=colors.get(k).getBlue();
                }
                //calculate the average
                red=Math.min(255, (int)(red/5.0));
                green=Math.min(255, (int)(green/5.0));
                blue=Math.min(255, (int)(blue/5.0));
                Color finalColor=new Color(red,green,blue);
                _imageWriter.writePixel(i, j, finalColor.getRed(), finalColor.getGreen(), finalColor.getBlue());

            }
        }
    }
//    public void renderImage() throws Exception {
//        for (int i = 0; i < _imageWriter.getWidth(); i++) {//pass over the pixels
//            for (int j = 0; j < _imageWriter.getHeight(); j++) {
//                Ray ray = getScene().getCamera().construct_Ray_Through_Pixel(getImageWriter().getNx(), getImageWriter().getNy(), i, j,
//                        getScene().getScreenDistance(), getImageWriter().getWidth(),
//                        getImageWriter().getHeight());//Create a ray for each pixel
//                Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);//find the intersetions
//                if (intersectionPoints.isEmpty()) {//pixel without intersection point
//                    _imageWriter.writePixel(i, j, getScene().getBackground());
//
//                } else {//find intersetion
//                    Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
//                    for (Entry<Geometry, Point3D> g : closestPoint.entrySet()) {
//                        Color color = calcColor(g.getKey(), g.getValue(), ray);
//                        _imageWriter.writePixel(i, j, color.getRed(), color.getGreen(), color.getBlue());
//                    }
//                }
//
//            }
//        }
//    }

}
