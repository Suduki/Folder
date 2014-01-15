package paketet;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

public class Rectangle {
    
    public Vector pos, vel, acc, size;

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
     * @return if inside; how far from center the edge is,  
     *          else null
     */
    public Vector inBounds(Rectangle r) {
        
        
        double edgeW = r.pos.getX() - r.size.getX()/2;
        double edgeN = r.pos.getY() - r.size.getY()/2;
        double edgeE = r.pos.getX() + r.size.getX()/2;
        double edgeS = r.pos.getY() + r.size.getY()/2;
        
        Vector dif = new Vector(this.pos.getX() - r.pos.getX(), this.pos.getY() - r.pos.getY());
        Vector totalSize = new Vector(size.getX() + r.size.getX(), size.getY() + r.size.getY());
        
        Vector returner = new Vector(0,0);
        

        
        
        if(dif.getX() < totalSize.getX()/2 && 
                dif.getX() > - totalSize.getX()/2 &&
                dif.getY() < totalSize.getY()/2 && 
                dif.getY() > - totalSize.getY()/2 ) {
//              INSIDE - Initialize collision
                // Is E edge inside box?
            System.out.println("Collision!" + vel.getX());
                if( dif.getX() > 0) {
                    System.out.println("1");
                    returner.setX((dif.getX() - r. size.getX()/2));
                } 
                else if( dif.getX() < 0) {
                    System.out.println("2");
                    returner.setX(dif.getX() + r.size.getX()/2);
                } 
                if( dif.getY() > 0) {
                    
                    System.out.println("3");
                    returner.setY((dif.getY() - r.size.getY()/2));
                } 
                else if( dif.getY() < 0) {
                    System.out.println("4");
                    returner.setX(dif.getY() + r.size.getY()/2);
                } 
                return returner;
        }
        //TODO Kommer krocka två gånger
        
        return null;
    }
}
