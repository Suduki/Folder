package paketet;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class Main {

	public static int mX, mY;
	public static int screenSizeX = 500, screenSizeY = 500;
	
	
	public static void main(String[] args) {
		try {
			Display.setDisplayMode(new DisplayMode(screenSizeX, screenSizeY));
			Display.setTitle("Hello, LWJGL!");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, screenSizeX, screenSizeY, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		Rectangle r = new Rectangle(new Vector(20, 20));
		
		while(!Display.isCloseRequested()) {
			// Render
			glClear(GL_COLOR_BUFFER_BIT);
			mX = Mouse.getX();
			mY = screenSizeY - Mouse.getY() - 1;

			r.move();
			r.drawMe(new Color(0,0,1));
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
}
