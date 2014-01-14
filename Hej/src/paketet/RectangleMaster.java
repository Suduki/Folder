package paketet;

import java.util.ArrayList;
import java.util.List;

public class RectangleMaster {
	
	private List<Rectangle> rectangleList = null;
	
	public RectangleMaster(Vector pos, Vector vel, Vector acc, Vector scale) {
		rectangleList = new ArrayList<Rectangle>();
		rectangleList.add(new Rectangle(pos, vel, acc, scale));
	}
	
	public addRectangle(Vector pos, Vector vel, Vector acc, Vector scale) {
		rectangleList.add(new Rectangle(pos, vel, acc, scale));
	}
	
	public iterate() {
		int i, j;
		for (i = 0; i < rectangleList.size(); i += 1) {
			for (j = 0; j < rectangleList.size(); j += 1) {
				Rectangle r1 = rectangleList.get(i);
				Rectangle r2 = rectangleList.get(j);
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
}
