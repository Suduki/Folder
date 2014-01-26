package paketet;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

public class Circle {

    public Vector pos, vel, acc, gravityAcc;
    public double mass, inertia;
    private double rad;


    //TODO Fix omegar into 3d?
    public double omegar, alphar; //omegar is in radians 
    
    private Color color;

    public Circle(Vector pos, Vector vel, Vector acc, double rad) {
        this.pos = pos;
        this.vel = vel;
        this.acc = acc;
        this.gravityAcc = acc.clone();
        this.rad = rad;
        this.mass = 1;
        this.inertia = mass*rad*rad;
    }
    public Circle(Vector pos, Vector vel, Vector acc, double rad, double omegar, double mass) {
        this.pos = pos;
        this.vel = vel;
        this.acc = acc;
        this.gravityAcc = acc.clone();
        this.rad = rad;
        this.omegar = omegar;
        this.mass = mass;
        this.inertia = mass*rad*rad;
        
    }

    public void move() {
        pos = pos.plus(vel);
        
        vel = vel.plus(acc);
        alphar = alphar + omegar;
    }

    public void drawMe(Color color) {
        
        int numberOfSlices = 30;
        
        GL11.glPushMatrix();
        GL11.glColor3d(1f, Math.abs((float)omegar*10), Math.abs((float)omegar*100));
        GL11.glTranslated(pos.getX(), pos.getY(), 0);
        GL11.glScaled(rad, rad, 1);

        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glVertex2d(0, 0);
        for(int i = 0; i <= numberOfSlices; i++){ //NUM_PIZZA_SLICES decides how round the circle looks.
            double angle = Math.PI * 2 * i / numberOfSlices;
            GL11.glVertex2f((float)Math.cos(angle), (float)Math.sin(angle));
        }
        GL11.glEnd();

        GL11.glPopMatrix();
        
        
        //Draw a cross in the middle of the circle to demonstrate rotation.
        Vector crossV = new Vector(0,rad/2).rotate2d(alphar);
        drawLine(pos.plus(crossV), pos.minus(crossV));
        crossV = crossV.rotate2d(Math.PI/2);
        drawLine(pos.plus(crossV), pos.minus(crossV));
        
    }

    private void drawLine(Vector v1, Vector v2) {

        GL11.glPushMatrix();
        GL11.glColor3d(0d, 0d, 0d);
        GL11.glBegin(GL11.GL_LINES); {
            GL11.glVertex2d(v1.getX(), v1.getY());
            GL11.glVertex2d(v2.getX(), v2.getY());
        }GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    
    /**
     * 
     * @param r
     * @return if inside; how far into the (this) Circle the other Rectangle is,  
     *          else null.
     */
    public Vector inBounds(Rectangle r) {

        //TODO
        return null;
    }
    
    /**
     * 
     * @param c
     * @return if inside; how far into each other the circles are
     *          else null.
     */
    public Vector inBounds(Circle c) {
        Vector posDif = c.pos.minus(this.pos);
        if(posDif.length() < c.rad + this.rad) {
//            You are inside
            double tol = 1;
            double size = posDif.length() - tol - (c.rad + this.rad);
            return posDif.unitVector().times(size);
        }
        return null;
    }
    
    
    /**
     * should maybe be called areYouInsideMe
     * @return
     */
    private boolean inBounds(Vector v) {
        Vector posDif = this.pos.minus(v);
        if(posDif.length() < this.rad) {
//            You are inside
            return true;
        }
        return false;
    }
    
    public double area() {
        return rad*rad*Math.PI;
    }
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public double getRad() {
        return rad;
    }
    public void setRad(double rad) {
        this.rad = rad;
    }
}
