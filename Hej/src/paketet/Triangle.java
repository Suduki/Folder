package paketet;

import static org.lwjgl.opengl.GL11.glVertex2d;

import org.lwjgl.opengl.GL11;

public class Triangle {

    private Vector vA, vB, vC;
    
    public Triangle(Vector vA, Vector vB, Vector vC) {
        this.vA = vA;
        this.vB = vB;
        this.vC = vC;
    }
    
    public Triangle rotate(double alphar) {
        return new Triangle(vA.rotate(alphar), vB.rotate(alphar), vC.rotate(alphar));
    }
    
    public double area() {
        return vB.minus(vA).area(vC.minus(vA))/2;
    }
    

    public void drawMe() {
        GL11.glPushMatrix();
        GL11.glColor3f(1f,0,0);

        GL11.glBegin(GL11.GL_TRIANGLES); {
            glVertex2d(vA.getX(), vA.getY());
            glVertex2d(vB.getX(), vB.getY());
            glVertex2d(vC.getX(), vC.getY());
        } GL11.glEnd();

        GL11.glPopMatrix();
    }
    
}
