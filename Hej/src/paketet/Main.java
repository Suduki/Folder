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
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, screenSizeX, screenSizeY, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, screenSizeX, screenSizeY, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		//Rectangle r = new Rectangle(new Vector(20, 20));
		double g = -0.01;
		RectangleMaster rectangleList  = new RectangleMaster(new Vector(250, 250), new Vector(0, 0), new Vector(0,g), new Vector(50, 20));
		rectangleList.addRectangle(new Vector(80, 80), new Vector(1, 1), new Vector(0, -g), new Vector(20, 20), 10, 2);
		rectangleList.addRectangle(new Vector(50, 808), new Vector(0.2, 0.2), new Vector(0, -g), new Vector(20, 20), 0, 5);
		rectangleList.addRectangle(new Vector(350, 250), new Vector(0.1, -0.3), new Vector(0, -g), new Vector(20, 20), 20, 5);
		rectangleList.addRectangle(new Vector(200, 250), new Vector(0.3, -0.1), new Vector(0, -g), new Vector(20, 20));
		rectangleList.addRectangle(new Vector(10, 88), new Vector(0.3, 0.09), new Vector(0, -g), new Vector(20, 20));
        rectangleList.addRectangle(new Vector(50, 60), new Vector(0.2, 0.2), new Vector(0, -g), new Vector(20, 20));
        rectangleList.addRectangle(new Vector(350, 250), new Vector(0.1, -0.3), new Vector(0, 0), new Vector(20, 20));
        rectangleList.addRectangle(new Vector(100, 250), new Vector(0.3, -0.1), new Vector(0, -g), new Vector(20, 20));
//        
		
		//TODO Maybe update physics more often than 60Hz?
        //TODO Why are they spinning inside the frame?
		
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
