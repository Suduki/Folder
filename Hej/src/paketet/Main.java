package paketet;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
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
		
		//Rectangle r = new Rectangle(new Vector(20, 20));
		RectangleMaster rectangleList  = new RectangleMaster(new Vector(250, 250), new Vector(0, 0), new Vector(0, 0), new Vector(50, 50));
		rectangleList.addRectangle(new Vector(0, 0), new Vector(5, 5), new Vector(0, 0), new Vector(20, 20));
		rectangleList.addRectangle(new Vector(200, 250), new Vector(2, -2), new Vector(0, 0), new Vector(20, 20));
		rectangleList.addRectangle(new Vector(350, 250), new Vector(1, -3), new Vector(0, 0), new Vector(20, 20));
		rectangleList.addRectangle(new Vector(300, 250), new Vector(3, -1), new Vector(0, 0), new Vector(20, 20));
		
		
		while(!Display.isCloseRequested()) {
	        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
	            try {
	                Thread.sleep(100);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }  
			// Render
			glClear(GL_COLOR_BUFFER_BIT);
			mX = Mouse.getX();
			mY = screenSizeY - Mouse.getY() - 1;

			rectangleList.update();
			rectangleList.iterate();
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
}
