package paketet;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

public class Rectangle {
    
    public Vector pos, vel, acc, size;
    public static final int NO_COLLISION = 0, EAST = 1, UP = 2, WEST = 3, DOWN = 4;

    public Rectangle(Vector pos, Vector vel, Vector acc, Vector size) {
        this.pos = pos;
        this.vel = vel;
        this.acc = acc;
        this.size = size;
    }

    public void move() {
        pos.setX(pos.getX() + vel.getX());
        pos.setY(pos.getY() + vel.getY());
        vel.setX(vel.getX() + acc.getX());
        vel.setY(vel.getY() + acc.getY());
    }

    public void drawMe(Color color) {
        glBegin(GL_QUADS); {
            glVertex2d(pos.getX() - (size.getX())/2, pos.getY() - (size.getY())/2);
            glVertex2d(pos.getX() + (size.getX())/2, pos.getY() - (size.getY())/2);
            glVertex2d(pos.getX() + (size.getX())/2, pos.getY() + (size.getY())/2);
            glVertex2d(pos.getX() - (size.getX())/2, pos.getY() + (size.getY())/2);
        } glEnd();
    }
    
    /**
     * Checks whether or not an other Rectangle is inside
     * 
     * @param pos
     * @param size
     * @return if inside; how far from center edge is,  
     *          else null
     */
    public Vector inBounds(Rectangle r) {
        
        
        double edgeW = r.pos.getX() - r.size.getX()/2;
        double edgeN = r.pos.getY() - r.size.getY()/2;
        double edgeE = r.pos.getX() + r.size.getX()/2;
        double edgeS = r.pos.getY() + r.size.getY()/2;
        
        Vector v = new Vector(0,0);
        
        v.setX(this.pos.getX() - edgeW);
        v.setY(this.pos.getX() - edgeW);
        
        
        if(this.pos.getX() < edgeW) {  //r is on
            
        } else if(this.pos.getX() < edgeE) {
            
        }
        
        
        return null;
    }
}
