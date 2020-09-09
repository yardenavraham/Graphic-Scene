package Primitives;

//This class represents material by 2 constants parameters and shininess
public class Material {

    private double _Kd;
    private double _Ks;
    private double _Kr;
    private double _Kt;
    private  int _nShininess;

    //c-tor
    public Material() {
        this._Kd = 1;
        this._Ks = 1;
        this._Kr = 0;
        this._Kt = 0;
        this._nShininess = 1;
    }
    
    public Material(Material m) {
        this._Kd = m.getKd();
        this._Ks = m.getKs();
         this._Kr = m.getKr();
        this._Kt = m.getKt();
        this._nShininess = m.getnShininess();
    }
    
    // **** Getters/Setters **** //
    public double getKd() {
        return _Kd;
    }

    public void setKd(double _Kd) {
        this._Kd = _Kd;
    }

    public double getKs() {
        return _Ks;
    }

    public void setKs(double _Ks) {
        this._Ks = _Ks;
    }

    public int getnShininess() {
        return _nShininess;
    }

    public void setnShininess(int _nShininess) {
        this._nShininess = _nShininess;
    }

    public double getKr() {
        return _Kr;
    }

    public void setKr(double _Kr) {
        this._Kr = _Kr;
    }

    public double getKt() {
        return _Kt;
    }

    public void setKt(double _Kt) {
        this._Kt = _Kt;
    }
    
}
