package paketet;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class Main {

	public static Vector mouseCoords;
	public static int screenSizeX = 500, screenSizeY = 500;
	
	
	public static void main(String[] args) {
		try {
			Display.setDisplayMode(new DisplayMode(screenSizeX, screenSizeY));
			Display.setTitle("Hello, LWJGL!");
			Display.create();
		} catch (LWJGLException e) {
		    e.printStackTrace();
		}
		
		mouseCoords = new Vector(0,0);
		
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
		double ay = 0.00001, ax = 0.000001;
		double sizeFactor = 3;
		RectangleMaster rectangleMaster  = new RectangleMaster(new Vector(250, 250), new Vector(0, 0), new Vector(ax,ay), new Vector(5*sizeFactor, 2*sizeFactor));
		rectangleMaster.addRectangle(new Vector(80, 80), new Vector(0, 0), new Vector(ax, -ay), new Vector(15*sizeFactor, 2*sizeFactor), 10, 2);
		rectangleMaster.addRectangle(new Vector(50, 408), new Vector(0, 0), new Vector(-ax, -ay), new Vector(2*sizeFactor, 2*sizeFactor), 0, 5);
		rectangleMaster.addRectangle(new Vector(353, 250), new Vector(0, 0), new Vector(ax, -ay), new Vector(2*sizeFactor, 2*sizeFactor), 20, 5);
		rectangleMaster.addRectangle(new Vector(83, 80), new Vector(0, 0), new Vector(ax, -ay), new Vector(2*sizeFactor, 2*sizeFactor), 10, 2);
		rectangleMaster.addRectangle(new Vector(56, 408), new Vector(0, 0), new Vector(-ax, -ay), new Vector(2*sizeFactor, 2*sizeFactor), 0, 5);
		rectangleMaster.addRectangle(new Vector(356, 250), new Vector(0, 0), new Vector(ax, -ay), new Vector(40*sizeFactor, 2*sizeFactor), 20, 5);
//		rectangleMaster.run();
		Thread a = (new Thread(rectangleMaster));
		a.start();
//        
		
		//TODO Maybe update physics more often than 60Hz?
        //TODO Why are they spinning inside the frame?
		
		while(!Display.isCloseRequested()) {
		   
			// Render
			glClear(GL_COLOR_BUFFER_BIT);
			
			mouseCoords.setX(Mouse.getX());
			mouseCoords.setY(screenSizeY - Mouse.getY() - 1);

			
			rectangleMaster.draw();
//			rectangleList.iterate();
			
			Display.update();
			Display.sync(60);
		}
		rectangleMaster.stop();
		while(a.isAlive()) { //Wait for thread to die
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
		}
		Display.destroy();
        
	}
}
