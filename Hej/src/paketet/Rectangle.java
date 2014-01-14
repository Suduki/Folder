package paketet;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.awt.Color;

public class Rectangle {
    public Vector pos, vel, acc, scales;

    public Rectangle(Vector scales) {
        pos = new Vector(50, 50);
        vel = new Vector(0, 0);
        acc = new Vector(0,-1);
        this.scales = scales;

    }

    public void move() {
        pos.setX(pos.getX() + vel.getX());
        pos.setY(pos.getY() + vel.getY());
        vel.setY(vel.getY() + acc.getY());
    }

    public void drawMe(Color color) {
        glBegin(GL_QUADS); {
            glVertex2d(-pos.getX() + scales.getX(), -pos.getY() + scales.getY());
            glVertex2d(pos.getX() + scales.getX(), -pos.getY() + scales.getY());
            glVertex2d(pos.getX() + scales.getX(), pos.getY() + scales.getY());
            glVertex2d(-pos.getX() + scales.getX(), pos.getY() + scales.getY());
        }glEnd();

    }


}
