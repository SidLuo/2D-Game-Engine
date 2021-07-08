package kitkat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ass1.GameObject;
import ass1.Mouse;
import ass1.MyCoolGameObject;
import ass1.PolygonalGameObject;

/**
 * Creates a black background with tiles covered over it
 * The android moves as mouse clicks the tiles
 * @author Sidney Luo
 *
 */
public class Tessellation extends PolygonalGameObject {
	private static final double[][] FILL_COLOR = {
			{.957, .263, .212, 1.0},
			{.612, .153, .691, 1.0},
			{0, .737, .831, 1.0},
			{.298, .686, .314, 1.0},
			{1.0, .922, .231, 1.0},
			{1.0, .341, .133, 1.0},
			{.804, .863, .224, 1.0},
			{.129, .588, .953, 1.0},
			{.457, .333, .282, 1.0}
	};
	private static final int NUM_COLOR = 9;
	private ArrayList<PolygonalGameObject> tiles;
	MyCoolGameObject android;
	public Tessellation() {
		super(GameObject.ROOT, 
				new double[] {-600, 300, -600, -300, 600, -300, 600, 300}, 
				new double[] {0,0,0,0}, null);
		tiles = new ArrayList<PolygonalGameObject>();
		
		Random rand = new Random();
		int x0 = -600;
		int y0 = 300;
		int pad = 3; // 3 padding
		for (int i = 0; i < 6; i++) {
			x0 = -600;
			for (int j = 0; j < 12; j++) {
				
				System.out.println(x0 + " " + y0);
				PolygonalGameObject square = new PolygonalGameObject(this,
						new double[] {x0 + pad, y0 - pad, x0 + pad, y0-100+pad, x0+100-pad, y0-100+pad, x0+100-pad, y0-pad}, 
						FILL_COLOR[rand.nextInt(NUM_COLOR)], new double[] {0,0,0,1});
				tiles.add(square);
				x0 += 100;
			}
			y0 -= 100;
		}
	}
	
	/**
	 * updates the position of the object
	 */
	@Override
    public void update(double dt) {
		Random rand = new Random();
		if (Mouse.theMouse.wasPressed(1)) {
			double[] target = Mouse.theMouse.getPosition();
			for (PolygonalGameObject p: tiles) {
				List<double[]> points = p.getPoints();
				double x0 = points.get(0)[0];
				double x1 = points.get(2)[0];
				double y0 = points.get(1)[1];
				double y1 = points.get(0)[1];
				if ((target[0] >= x0 && target[0] <= x1) &&
						((target[1] >= y0 && target[1] <= y1))) {
							if (android != null) android.destroy();
							android = new MyCoolGameObject(this, 
									new double[]{.643, .776, .224, 1}, p.getFillColour());
							android.setPosition((x0+x1)/2, (y0+y1)/2);
							android.setRotation((rand.nextInt(4) - 2) * 90);
							android.scale(100);
							//android.scale(p.getScale());
						}
			}
	    }
	}
}
