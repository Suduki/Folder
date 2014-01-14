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
     * @param r
     * @return if inside; how far from center edge is,  
     *          else null
     */
    public Vector inBounds(Rectangle r) {
        
        
        double edgeW = r.pos.getX() - r.size.getX()/2;
        double edgeN = r.pos.getY() - r.size.getY()/2;
        double edgeE = r.pos.getX() + r.size.getX()/2;
        double edgeS = r.pos.getY() + r.size.getY()/2;
        
        Vector dif = new Vector(this.pos.getX() - r.pos.getX(), this.pos.getY() - r.pos.getX());
        
        if(dif.getX() < size.getX() + r.size.getX() && 
                dif.getX() > - size.getX() - r.size.getX() &&
                dif.getY() < size.getY() + r.size.getY() && 
                dif.getY() > - size.getY() - r.size.getY() ) {
            //INSIDE - Initialize collision
//            if(dif.getX() < size.getX() + r.size.getX() && {
//           
//          }
            System.out.println("Collision!");

        }
        
        
        return null;
    }
}
