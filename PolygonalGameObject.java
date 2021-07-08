package ass;
import java.util.List;
import java.util.ArrayList;

import com.jogamp.opengl.GL;

/**
  * A game object that has a polygonal shape.
  *
  * This class extend GameObject to draw polygonal shapes.
  *
  * TODO: The methods you need to complete are at the bottom of the class
  *
  * @author Sid Luo
  *
  *
  */

public class PolygonalGameObject extends GameObject {

    private List<double[]> myPoints;
    private double[] myFillColour;
    private double[] myLineColour;

    /**
      * Create a polygonal game object and add it to the scene tree
      *
      * The polygon is specified as a list of doubles in the form:
      *
      * [ [x, y], [x, y], [x, y], ... ]
      *
      * The line and fill colours can possibly be null, in which case that part of the object
      * should not be drawn.
      *
      * @param parent The parent in the scene tree
      * @param points A list of points defining the polygon
      * @param fillColour The fill colour in [r, g, b, a] form
      * @param lineColour The outline colour in [r, g, b, a] form
      */
    public PolygonalGameObject(GameObject parent, List<double[]> points,
            double[] fillColour, double[] lineColour) {
        super(parent);

        myPoints = points;
        myFillColour = fillColour;
        myLineColour = lineColour;
    }

      /**
      * Create a polygonal game object and add it to the scene tree
      *
      * The polygon is specified as a list of doubles in the form:
      *
      * [ x, y, x, y, x, y, ... ]
      *
      * The line and fill colours can possibly be null, in which case that part of the object
      * should not be drawn.
      *
      * @param parent The parent in the scene tree
      * @param points A list of points defining the polygon
      * @param fillColour The fill colour in [r, g, b, a] form
      * @param lineColour The outline colour in [r, g, b, a] form
      */

    public PolygonalGameObject(GameObject parent, double points[],
            double[] fillColour, double[] lineColour) {
        super(parent);

        myPoints = new ArrayList<double[]>();
        for(int i =; i < points.length; i+=){
                  myPoints.add(new double[]{points[i],points[i+]});
        }
        myFillColour = fillColour;
        myLineColour = lineColour;
    }

    /**
      * Get the polygon
      *
      * @return
      */
    public List<double[]> getPoints() {
        return myPoints;
    }

    /**
      * Set the polygon
      *
      * @param points
      */
    public void setPoints(List<double[]> points) {
        myPoints = points;
    }

    /**
      * Get the fill colour
      *
      * @return
      */
    public double[] getFillColour() {
        return myFillColour;
    }

    /**
      * Set the fill colour.
      *
      * Setting the colour to null means the object should not be filled.
      *
      * @param fillColour The fill colour in [r, g, b, a] form
      */
    public void setFillColour(double[] fillColour) {
        myFillColour = fillColour;
    }

    /**
      * Get the outline colour.
      *
      * @return
      */
    public double[] getLineColour() {
        return myLineColour;
    }

    /**
      * Set the outline colour.
      *
      * Setting the colour to null means the outline should not be drawn
      *
      * @param lineColour
      */
    public void setLineColour(double[] lineColour) {
        myLineColour = lineColour;
    }

    // ===========================================
    // COMPLETE THE METHODS BELOW
    // ===========================================


    /**
      * TODO: Draw the polygon
      *
      * if the fill colour is non-null, fill the polygon with this colour
      * if the line colour is non-null, draw the outline with this colour
      *
      * @see ass.GameObject#drawSelf(javax.media.opengl.GL)
      */
    @Override
    public void drawSelf(GL gl) {
        if (myLineColour != null) {
                gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);
                gl.glBegin(GL.GL_POLYGON);
                gl.glColord(myLineColour[], myLineColour[], myLineColour[]);
                for (double[] point : myPoints) {
                gl.glVertexd(point[], point[]);
            }
                gl.glEnd();
        }

        if (myFillColour != null) {
                gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);
                gl.glBegin(GL.GL_POLYGON);
                gl.glColord(myFillColour[], myFillColour[], myFillColour[]);
                for (double[] point : myPoints) {
                        gl.glVertexd(point[], point[]);
            }
                gl.glEnd();
        }
        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);
    }

}