package scene;

import Elements.AmbientLight;
import Elements.Camera;
import Elements.LightSource;
import Geometries.Geometry;
import Primitives.Point3D;
import Primitives.Vector;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

//This class represents scene 
public class Scene {

    private String _sceneName;
    private Color _background;
    private AmbientLight _ambientLight;
    private List<Geometry> _geometries;
    private Camera _camera;
    private double _screenDistance;
    private List<LightSource> _lights;

    // ***************** Getters/Setters ********************** //
    public String getSceneName() {
        return _sceneName;
    }

    public void setSceneName(String _sceneName) {
        this._sceneName = _sceneName;
    }

    public Color getBackground() {
        return _background;
    }

    public void setBackground(Color _background) {
        this._background = _background;
    }

    public AmbientLight getAmbientLight() {
        return new AmbientLight(_ambientLight);
    }

    public void setAmbientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    public List<Geometry> getGeometries() {
        return _geometries;
    }

    public void setGeometries(List<Geometry> _geometries) {
        this._geometries = _geometries;
    }

    public Camera getCamera() {
        return new Camera(_camera);
    }

    public void setCamera(Camera _camera) {
        this._camera = _camera;
    }

    public double getScreenDistance() {
        return _screenDistance;
    }

    public void setScreenDistance(double _screenDistance) {
        this._screenDistance = _screenDistance;
    }

    public List<LightSource> getLights() {
        return _lights;
    }

    public void setLights(List<LightSource> _lights) {
        this._lights = _lights;
    }
    

    //c-tor
    public Scene(String _sceneName, Color _background, AmbientLight _ambientLight, List<Geometry> _geometries, Camera _camera, double _screenDistance, List<LightSource> _lights) {
        this._sceneName = _sceneName;
        this._background = _background;
        this._ambientLight = _ambientLight;
        this._geometries = _geometries;
        this._camera = _camera;
        this._screenDistance = _screenDistance;
        this._lights = _lights;
    }

    // default c-tor
    public Scene() {
        _background = Color.black;
        _screenDistance = 149;
        _geometries = new LinkedList<Geometry>();
        _sceneName = "empty scene";
        _ambientLight = new AmbientLight();
        _camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        _lights = new ArrayList<LightSource>();
    }

    //copy c-tor
    public Scene(Scene scene) {
        this._sceneName = scene.getSceneName();
        this._background = scene.getBackground();
        this._ambientLight = scene.getAmbientLight();
        this._geometries = new LinkedList<Geometry>();
        this._lights = new ArrayList<LightSource>();
        this._camera = scene.getCamera();
        this._screenDistance = scene.getScreenDistance();
        Iterator<Geometry> it = scene.getGeometriesIterator();
        for (; it.hasNext();) {
            _geometries.add(it.next());

        }
        Iterator<LightSource> it2 = scene.getLightsIterator();
        for (; it2.hasNext();) {
            _lights.add(it2.next());
        }
    }
    //add geometry to the list
    public void addGeometry(Geometry geometry) {
        _geometries.add(geometry);
    }
    
    //add light to the list
    public void addLight(LightSource light) {
        _lights.add(light);
    }

    //return iterator of the geometry
    public Iterator<Geometry> getGeometriesIterator() {
        return _geometries.iterator();
    }
    
    //return iterator of the light
    public Iterator<LightSource> getLightsIterator() {
        return _lights.iterator();
    }
}
