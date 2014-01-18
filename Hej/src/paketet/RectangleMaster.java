package paketet;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Color;

public class RectangleMaster {
	
	private ArrayList<Rectangle> rectangleList;
	
	Rectangle northFrame, eastFrame, southFrame, westFrame;
	
	public RectangleMaster(Vector pos, Vector vel, Vector acc, Vector size) {
		rectangleList = new ArrayList<Rectangle>();
		rectangleList.add(new Rectangle(pos, vel, acc, size));
		generateFrames();
	}
	
	public void addRectangle(Vector pos, Vector vel, Vector acc, Vector size) {
		rectangleList.add(new Rectangle(pos, vel, acc, size));
		generateFrames();
		
	}
	public void addRectangle(Vector pos, Vector vel, Vector acc, Vector size, double alpha, double omega) {
	    rectangleList.add(new Rectangle(pos, vel, acc, size, alpha, omega));
	    generateFrames();
	    
	}
	
	private void generateFrames() {
	    double depth =100;

	    northFrame = new Rectangle(new Vector(Main.screenSizeX/2, -depth/2),
	            new Vector(0,0), new Vector(0,0), new Vector(Main.screenSizeX + 2*depth, depth));
	    eastFrame = new Rectangle(new Vector(Main.screenSizeX + depth/2, Main.screenSizeY/2),
	            new Vector(0,0), new Vector(0,0), new Vector(depth,Main.screenSizeY + 2*depth));
	    southFrame = new Rectangle(new Vector(Main.screenSizeX/2, Main.screenSizeY + depth/2),
	            new Vector(0,0), new Vector(0,0), new Vector(Main.screenSizeX + 2*depth, depth));
	    westFrame = new Rectangle(new Vector(-depth/2,Main.screenSizeY/2),
	            new Vector(0,0), new Vector(0,0), new Vector(depth,Main.screenSizeY + 2*depth));
	    
	    northFrame.move();
	    eastFrame.move(); 
	    southFrame.move(); 
	    westFrame.move();
	}
	
	public void iterate() {

	    Random r = new Random();
	    
	    for (Rectangle r1: rectangleList) {
		    for (Rectangle r2: rectangleList) {
		        Vector v;
		        if(!r1.equals(r2)) {
				    v = r1.inBounds(r2);
				    if (v != null) {
				        
				        r1.pos = r1.pos.plus(v);
				        r1.vel = r2.vel.times(new Vector(1,1));
				        r2.vel = r1.vel.times(new Vector(1,1));
				        r1.omega +=1;
				        r2.omega -=1;
				    }
		        }
			}
		}
	    for (Rectangle r1: rectangleList) {
	        Vector v;
	        v = northFrame.inBounds(r1);
	        if (v != null) {
	            r1.pos = r1.pos.plus(v);
	            r1.vel = r1.vel.times(new Vector(1,-1));
	        }
	        v=null;
	        v = westFrame.inBounds(r1);
	        if (v != null) {
	            r1.pos = r1.pos.plus(v);
	            r1.vel = r1.vel.times(new Vector(-1,1));
	        }
	        v=null;
	        v = southFrame.inBounds(r1);
	        if (v != null) {
	            r1.pos = r1.pos.plus(v);
	            r1.vel = r1.vel.times(new Vector(1,-1));
	        }
	        v=null;
	        v = eastFrame.inBounds(r1);
	        if (v != null) {
	            r1.pos = r1.pos.plus(v);
	            r1.vel = r1.vel.times(new Vector(-1,1));
	        }
	        
	    }
	}
	
	public void update() {
		for (Rectangle r : rectangleList) {
		    r.move();
			r.drawMe(new Color(0,0,1));
		}
	}
}
