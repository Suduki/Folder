package paketet;

public class Vector {
    private double x, y;
    public static final Vector ORIGO = new Vector(0,0);
    
    public Vector(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double d) {
        this.x = d;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    
    /**
     * Does not rotate the vector, only returns a rotated clone of the vector.
     * @param alphar (in radians)
     * @return
     */
    public Vector rotate(double alphar) {
        double xr, yr;
        xr = x * Math.cos(alphar) + y * Math.sin(alphar);
        yr = y * Math.cos(alphar) - x * Math.sin(alphar);
        return new Vector(xr, yr);
    }
    
    /**
     * Rotates the coords and returns a vector to this new position.
     * @param alphar (in radians)
     * @return
     */
    public static Vector rotate(double alphar, double x, double y) {
        double xr, yr;
        xr = x * Math.cos(alphar) + y * Math.sin(alphar);
        yr = y * Math.cos(alphar) - x * Math.sin(alphar);
                
        return new Vector(xr, yr);
    }
    
    /*
     * Some basic methods like plus,minus
     */
    public Vector plus(Vector v) {
        return new Vector(this.x + v.x, this.y + v.y);
    }
    public Vector minus(Vector v) {
        return new Vector(this.x - v.x, this.y - v.y);
    }
    public Vector times(Vector v) {
        return new Vector(this.x * v.x, this.y * v.y);
    }
    public Vector times(double c) {
        return new Vector(this.x * c, this.y * c);
    }
    public Vector divideBy(Vector v) {
        return new Vector(this.x / v.x, this.y / v.y);
    }
    /**
     * uses the cross product: ||(this) x v|| 
     */
    public double area(Vector v) {
        return Math.abs(this.x*v.getY() - this.y*v.getX());
    }
    public double length() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    public Vector clone() {
        return new Vector(x, y);
    }
    public Vector mean(Vector v) {
        return new Vector((this.x + v.x)/2, (this.y + v.y)/2);
    }
    public Vector unitVector() {
        return new Vector(x/this.length(), y/this.length());
    }
}
