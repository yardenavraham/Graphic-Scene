
package Primitives;
//This class represents Coordinate by one double number.
public class Coordinate implements Comparable<Coordinate>{

    private double _coordinate;  
    
    //c-tor
    public Coordinate(double coordinate) {
        this._coordinate = coordinate;
    }

    public Coordinate(Coordinate _x) {
        this._coordinate=_x._coordinate;
    }
    
    // **** Getters/Setters **** //
    public double getCoordinate() {
        return _coordinate;
    }

    public void setCoordinate(double _coordinate) {
        this._coordinate = _coordinate;
    }

    @Override
    public int compareTo(Coordinate o) {//comare between coordinates, return 0 if equal.
        if(this._coordinate==o.getCoordinate())
            return 0;
        return -1;
    }

    //add coordinate to coordinate
    public Coordinate add(Coordinate x)
    {
        return new Coordinate(getCoordinate()+x.getCoordinate());
    }
    
    //subtract coordinate from coordinate
    public Coordinate subtract(Coordinate x)
    {
        return new Coordinate(getCoordinate()-x.getCoordinate());
    }
    
    
}
