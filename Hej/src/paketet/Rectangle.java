package paketet;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

public class Rectangle {

    public Vector pos, vel, acc, size;
    public double alpha, alphar, omega; //alphar is in radians
    private Color color;
    private Vector pNW, pNE, pSE, pSW;

    public Rectangle(Vector pos, Vector vel, Vector acc, Vector size) {
        this.pos = pos;
        this.vel = vel;
        this.acc = acc;
        this.size = size;
        // Changing these will rotate the frames :P
        alpha = 0;
        omega = 0;
    }
    public Rectangle(Vector pos, Vector vel, Vector acc, Vector size, double alpha, double omega) {
        this.pos = pos;
        this.vel = vel;
        this.acc = acc;
        this.size = size;
        this.alpha = alpha;
        this.alphar = alpha * (Math.PI/180);
        this.omega = omega;
    }

    public void move() {
        pos = pos.plus(vel);
        vel = vel.plus(acc);

        alpha += omega; 
        if(alpha > 0) alpha = alpha - 180; 
        if(alpha < -180) alpha = alpha + 180; 
        
        // Update NW, NE, SE, SW
        double alphar = alpha*2*Math.PI/360;
        pNW = Vector.rotate(alphar, -size.getX()/2, -size.getY()/2);
        pNE = Vector.rotate(alphar, size.getX()/2, -size.getY()/2);
        pSE = Vector.rotate(alphar, size.getX()/2, size.getY()/2);
        pSW = Vector.rotate(alphar, -size.getX()/2, size.getY()/2);
        pNW = pNW.plus(pos);
        pNE = pNE.plus(pos); 
        pSE = pSE.plus(pos);
        pSW = pSW.plus(pos); 
//        pSE.setX(pos.getX() + pSE.getX()); pSE.setY(pos.getY() + pSE.getY()); 
//        pSW.setX(pos.getX() + pSW.getX()); pSW.setY(pos.getY() + pSW.getY()); 
    }

    public void drawMe(Color color) {
//        GL11.glPushMatrix();
        GL11.glColor3f(1f, 0f, 0f);
        GL11.glBegin(GL11.GL_QUADS); {
            glVertex2d(pNW.getX(), pNW.getY());
            glVertex2d(pNE.getX(), pNE.getY());
            glVertex2d(pSE.getX(), pSE.getY());
            glVertex2d(pSW.getX(), pSW.getY());
        } GL11.glEnd();
//        GL11.glPopMatrix();
        
    }

    /**
     * Checks whether or not an other Rectangle is inside
     * 
     * @param r
     * @return if inside; how far into the (this) Rectangle the corder of the other rectangle is,  
     *          else null
     */
    public Vector inBounds(Rectangle r) {

        Vector[] edges = {r.pNW, r.pNE, r.pSE, r.pSW};
        for(Vector edge : edges) {
            if(inBounds(edge)) {
                
                Vector[] myEdges = {pNW, pNE, pSE, pSW, pNW};
                double area, maxArea=this.area();
                int j=-1;
                for(int i = 0; i < (myEdges.length - 1); i += 1) {
                    area = Math.abs(new Triangle(myEdges[i], myEdges[i+1], edge).area());
//                    System.out.println(area);
//                    System.out.println(area() + ", " + r.area());
                    if( area < maxArea ){
                        j = i;
                        maxArea = area;
                    }
                    //Now we know between which two points (myEdges[edgeLoc, edgeLoc+1]) the Rectangle r should be moved towards.
                }
                if(j == -1) throw new RuntimeException("The edge is not correct or something (error in code)");
                Vector a,b,c;
                a = myEdges[j].clone();
                b = myEdges[j+1].clone();
                c = edge.clone();
                
                c = a.minus(b).unitVector().times(a.minus(c).length() / b.minus(c).length());
//                double factor;
//                factor = a.minus(c).length() / b.minus(c).length();
//                c = (a.times(factor).plus(b.times(factor))).times(1/2);
                
                return c;
//                return myEdges[j].plus(myEdges[j].minus(myEdges[j+1]).
//                        unitVector().times((myEdges[j].minus(edge).
//                                divideBy(myEdges[j+1].minus(edge)))));
                
            }
        }
        
        //TODO Kommer krocka två gånger

        return null;
    }
    
    /**
     * Returns the point that is inside the rectangle (this) or null, should be called areYouInsideMe
     * @return
     */
    private boolean inBounds(Vector v) {
//        System.out.println(v.getX());
        /*
         * for each node
         * check if alternativeArea is very close to true area 
         */
        double trueArea = this.area();
        double altArea = alternativeArea(v);
        if(Math.abs(trueArea - altArea) < 0.00001) {
            // Point is inside
//            System.out.println("coll");
            return true;
        }
        return false;
    }
    
    public double area() {
        return size.getX() * size.getY();
    }
    /**
     * Checks the area of the 5-edged polygon, joining "point" with "this"
     * Note that if "point" is within the boundaries this area will be equal to this.area()
     * @param point
     * @return
     */
    private double alternativeArea(Vector point) {
        
        Vector[] edges = {pNW, pNE, pSE, pSW, pNW};
        double area = 0;
        for(int i = 0; i < (edges.length - 1); i += 1) {
            area += Math.abs(new Triangle(edges[i], edges[i+1], point).area()); 
        }
        
        return area;
    }
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
