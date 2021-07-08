package ass;
import com.jogamp.opengl.GL;

/**
  * A game object that has a circular shape
  *
  * This class extend GameObject to draw circular shapes
  *
  * @author Sid Luo
  *
  */

public class CircularGameObject extends GameObject {
    private double[] myCentre;
    private double myRadius;
    private double[] myFillColour;
    private double[] myLineColour;

    /**
      * Create a circular game object and add it to the scene tree
      *
      * The circle has centre (,) and radius
      *
      * The line and fill colours can possibly be null, in which case that part of the object
      * should not be drawn.
      *
      * @param parent The parent in the scene tree
      * @param fillColour The fill colour in [r, g, b, a] form
      * @param lineColour The outline colour in [r, g, b, a] form
      */
    public CircularGameObject(GameObject parent, double[] fillColour,
            double[] lineColour) {
        super(parent);
        myCentre = new double[] {,};
        myRadius =.;
        myFillColour = fillColour;
        myLineColour = lineColour;
    }

    /**
      * Create a circular game object and add it to the scene tree
      *
      * The circle has centre (,) and the radius given
      *
      * The line and fill colours can possibly be null, in which case that part of the object
      * should not be drawn.
      *
      * @param parent The parent in the scene tree
      * @param radius The radius of the circle
      * @param fillColour The fill colour in [r, g, b, a] form
      * @param lineColour The outline colour in [r, g, b, a] form
      */
    public CircularGameObject(GameObject parent, double radius, double[] fillColour,
            double[] lineColour) {
        super(parent);
        myCentre = new double[] {,};
        myRadius = radius;
        myFillColour = fillColour;
        myLineColour = lineColour;

    }

    /**
      * Get the centre
      *
      * @return centre coordinate
      */
    public double[] getCentre() {
        return myCentre;
    }

    /**
      * Set the centre
      *
      * @param centre
      */
    public void setCentre(double[] centre) {
        myCentre = centre;
    }

    /**
      * Get the radius
      *
      * @return radius
      */
    public double getRadius() {
        return myRadius;
    }

    /**
      * Set the radius
      *
      * @param radius
      */
    public void setRadius(double radius) {
        myRadius = radius;
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

    /**
      * Draws the circular GameObject
      * if the fill colour is non-null, fill the polygon with this colour
      * if the line colour is non-null, draw the outline with this colour
      */
    @Override
    public void drawSelf(GL gl) {
        int numVertices =;
        if (myLineColour != null) {
                gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);
                gl.glBegin(GL.GL_POLYGON);
                gl.glColord(myLineColour[], myLineColour[], myLineColour[]);
                gl.glVertexd(myCentre[], myCentre[]); // The centre of the circle
            double angle =;
            double angleIncrement = * Math.PI / numVertices;
            for(int i=; i <= numVertices; i++){
                angle = i * angleIncrement;
                double x = myRadius * Math.cos(angle);
                double y = myRadius * Math.sin(angle);

                gl.glVertexd(x, y);

            }
            gl.glEnd();
        }
        if (myFillColour != null) {
                gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);
                gl.glBegin(GL.GL_POLYGON);
                gl.glColord(myFillColour[], myFillColour[], myFillColour[]);
                gl.glVertexd(myCentre[], myCentre[]); // The centre of the circle
            double angle =;
            double angleIncrement = * Math.PI / numVertices;
            for(int i=; i <= numVertices; i++){
                angle = i * angleIncrement;
                double x = myRadius * Math.cos(angle);
                double y = myRadius * Math.sin(angle);
                gl.glVertexd(x, y);
            }
            gl.glEnd();
        }
        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);
    }
}