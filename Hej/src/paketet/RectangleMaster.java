package paketet;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class RectangleMaster {
	
	private List<Rectangle> rectangleList = null;
	
	public RectangleMaster(Vector pos, Vector vel, Vector acc, Vector size) {
		rectangleList = new ArrayList<Rectangle>();
		rectangleList.add(new Rectangle(pos, vel, acc, size));
	}
	
	public void addRectangle(Vector pos, Vector vel, Vector acc, Vector size) {
		rectangleList.add(new Rectangle(pos, vel, acc, size));
	}
	
	public void iterate() {

	    for (Rectangle r1: rectangleList) {
		    for (Rectangle r2: rectangleList) {
				Vector v = r1.inBounds(r2);
				if (v != null) {
					r1.pos.setX(r1.pos.getX() + v.getX());
					r1.pos.setY(r1.pos.getY() + v.getY());
					r1.vel.setX(-r1.vel.getX());
					r1.vel.setY(-r1.vel.getY());
				}
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
