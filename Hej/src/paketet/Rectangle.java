package paketet;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

public class Rectangle {
    public Vector pos, vel, acc, scales;

    public Rectangle(Vector scales) {
        pos = new Vector(100, 5);
        vel = new Vector(0, 0);
        acc = new Vector(0,1);
        this.scales = scales;

    }

    public void move() {
        pos.setX(pos.getX() + vel.getX());
        pos.setY(pos.getY() + vel.getY());
        vel.setY(vel.getY() + acc.getY());
    }

    public void drawMe(Color color) {
        glBegin(GL_QUADS); {
            glVertex2i(pos.getX() - scales.getX(), pos.getY() - scales.getY());
            glVertex2i(pos.getX() + scales.getX(), pos.getY() - scales.getY());
            glVertex2i(pos.getX() + scales.getX(), pos.getY() + scales.getY());
            glVertex2i(pos.getX() - scales.getX(), pos.getY() + scales.getY());
        }glEnd();

    }


}
