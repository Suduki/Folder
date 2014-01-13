package paketet;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class Main {

	public static int mX, mY;
	public static final int SCREEN_SIZE_X = 1000;
	public static final int SCREEN_SIZE_Y = 480;
	
	
	public static void main(String[] args) {
		try {
			Display.setDisplayMode(new DisplayMode(SCREEN_SIZE_X, SCREEN_SIZE_Y));
			Display.setTitle("Hello, LWJGL!");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, SCREEN_SIZE_X, SCREEN_SIZE_Y, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		while(!Display.isCloseRequested()) {
			// Render
			glClear(GL_COLOR_BUFFER_BIT);
			mX = Mouse.getX();
			mY = SCREEN_SIZE_Y - Mouse.getY() - 1;

			glBegin(GL_QUADS);
				glVertex2d(mX - 5, mY - 5);
				glVertex2d(mX + 5, mY - 5);
				glVertex2d(mX + 5, mY + 5);
				glVertex2d(mX - 5, mY + 5);
			glEnd();
			
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
}
