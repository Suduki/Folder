package paketet;

public class Vector {
    private double x, y;
    
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
}
