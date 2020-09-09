
package Primitives;

//This class represents Point2D by 2 Coordinates.
public class Point2D {

    public Coordinate getX() {
        return _x;
    }

    public void setX(Coordinate _x) {
        this._x = _x;
    }

    public Coordinate getY() {
        return _y;
    }

    public void setY(Coordinate _y) {
        this._y = _y;
    }

    public Point2D(Coordinate x, Coordinate y) {
        this._x = x;
        this._y = y;
    }
    
    private Coordinate _x;
    private Coordinate _y;
}
