package paketet;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class Main {

	public static Vector mouseCoords;
	public static int screenSizeX = 1200, screenSizeY = 800;
	
	
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
		double ay = -0.001, ax = 0.00;
		double sizeFactor = 5;
//		RectangleMaster rectangleMaster  = new RectangleMaster(new Vector(250, 250), new Vector(0, 0), new Vector(ax,ay), new Vector(5*sizeFactor, 2*sizeFactor));
//		rectangleMaster.addRectangle(new Vector(80, 80), new Vector(0, 0), new Vector(ax, -ay), new Vector(15*sizeFactor, 2*sizeFactor), 10, 2);
//		Thread a = (new Thread(rectangleMaster));
//		a.start();
		CircleMaster circleMaster  = new CircleMaster();
		circleMaster.addCircle(new Vector(80, 80), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0d, 1);
		circleMaster.addCircle(new Vector(80, 120), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0.01d, 1);
		circleMaster.addCircle(new Vector(79, 180), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0.01d, 1);
		circleMaster.addCircle(new Vector(80, 160), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0d, 1);
		circleMaster.addCircle(new Vector(80, 200), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0.01d, 1);
		circleMaster.addCircle(new Vector(79, 500-50), new Vector(1, 0), new Vector(ax, -ay), 2*sizeFactor, 0.01d, 50);
		circleMaster.addCircle(new Vector(100, 80), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0d, 1);
		circleMaster.addCircle(new Vector(120, 120), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0.01d, 1);
		circleMaster.addCircle(new Vector(149, 180), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0.01d, 1);
		circleMaster.addCircle(new Vector(160, 160), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0d, 1);
		circleMaster.addCircle(new Vector(180, 200), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0.01d, 1);
		circleMaster.addCircle(new Vector(209, 220), new Vector(1, 0), new Vector(ax, -ay), 2*sizeFactor, 0.01d, 1);
		circleMaster.addCircle(new Vector(300, 80), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0d, 1);
		circleMaster.addCircle(new Vector(380, 120), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0.01d, 1);
		circleMaster.addCircle(new Vector(379, 180), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0.01d, 1);
		circleMaster.addCircle(new Vector(380, 160), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0d, 1);
		circleMaster.addCircle(new Vector(380, 200), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0.01d, 1);
		circleMaster.addCircle(new Vector(379, 500-50), new Vector(1, 0), new Vector(ax, -ay), 2*sizeFactor, 0.01d, 50);
		circleMaster.addCircle(new Vector(400, 80), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0d, 1);
		circleMaster.addCircle(new Vector(420, 120), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0.01d, 1);
		circleMaster.addCircle(new Vector(449, 180), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0.01d, 1);
		circleMaster.addCircle(new Vector(460, 160), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0d, 1);
		circleMaster.addCircle(new Vector(480, 200), new Vector(0, 0), new Vector(ax, -ay), sizeFactor, 0.01d, 1);
		circleMaster.addCircle(new Vector(9, 220), new Vector(1, 0), new Vector(ax, -ay), 2*sizeFactor, 0.01d, 1);
		for (int i = 0; i < 50; i++){
		    for (int j=0; j<5; j++) {
		    circleMaster.addCircle(new Vector(600 + j*3*sizeFactor, 20 + i*3*sizeFactor), new Vector(1, 0), new Vector(ax, -ay), sizeFactor, 0.0, 1);
		    }
		}
		Thread a = (new Thread(circleMaster));
		a.start();
//        
		
		//TODO Maybe update physics more often than 60Hz?
        //TODO Why are they spinning inside the frame?
		
		while(!Display.isCloseRequested()) {
		   
			// Render
			glClear(GL_COLOR_BUFFER_BIT);
			
			mouseCoords.setX(Mouse.getX());
			mouseCoords.setY(screenSizeY - Mouse.getY() - 1);

			
			circleMaster.draw();
//			rectangleList.iterate();
//			circleList.iterate();
			
			Display.update();
			Display.sync(60);
		}
//		rectangleMaster.stop();
		circleMaster.stop();
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
