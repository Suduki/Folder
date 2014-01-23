package paketet;

public class Vector {
    private double x, y, z;
    public static final Vector ORIGO = new Vector(0,0);
    
    public Vector(double x, double y) {
        this.setX(x);
        this.setY(y);
        this.setZ(0);
    }
    public Vector(double x, double y, double z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
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
    
    public double getZ() {
        return z;
    }
    
    public void setZ(double z) {
        this.z = z;
    }
    
    /**
     * Does not rotate the vector, only returns a rotated clone of the vector.
     * @param alphar (in radians)
     * @return
     */
    public Vector rotate2d(double alphar) {
        double xr, yr;
        xr = x * Math.cos(alphar) + y * Math.sin(alphar);
        yr = y * Math.cos(alphar) - x * Math.sin(alphar);
        return new Vector(xr, yr);
    }
    
    
    /*
     * Some basic methods like plus,minus
     */
    public Vector plus(Vector v) {
        return new Vector(this.x + v.x, this.y + v.y, this.z + v.z);
    }
    public Vector minus(Vector v) {
        return new Vector(this.x - v.x, this.y - v.y, this.z - v.z);
    }
    public Vector times(Vector v) {
        return new Vector(this.x * v.x, this.y * v.y, this.z * v.z);
    }
    public Vector times(double c) {
        return new Vector(this.x * c, this.y * c, this.z *c);
    }
    
    public Vector divideBy(Vector v) {
        if (v.getX() == 0 || v.getY() == 0 || v.getZ() == 0) {
            throw new RuntimeException("You tried to divide by zero, why would you do that?");
        }
        return new Vector(this.x / v.x, this.y / v.y, this.z / v.z);
    }
    
    /**
     * uses the cross product: ||(this) x v|| 
     * Note that we are in 2d.
     */
    public double area2d(Vector v) {
        return Math.abs(this.x*v.getY() - this.y*v.getX());
    }
    public Vector cross(Vector v) {
        double xr, yr, zr;
        xr =  this.y*v.getZ() - this.z*v.getY();
        yr =  this.z*v.getX() - this.x*v.getZ();
        zr =  this.x*v.getY() - this.y*v.getX();
        return new Vector(xr, yr, zr);
    }
    public double length() {
        return Math.sqrt(x*x + y*y);
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
    public double dot(Vector v) {
        return this.x * v.getX() + this.y * v.getY();
    }
}
