package ass;
import com.jogamp.opengl.GL;

/**
  * A game object that is a line
  *
  * This class extend GameObject to draw lines
  *
  * @author Sid Luo
  *
  */

public class LineGameObject extends GameObject {
        private double[] start;
        private double[] end;
    private double[] myLineColour;

    /**
      * Create a line game object and add it to the scene tree
      *
      * The line is from [,] to [,]
      *
      * The line colour can possibly be null, in which case the object
      * should not be drawn.
      *
      * @param parent The parent in the scene tree
      * @param lineColour The line colour in [r, g, b, a] form
      */
    public LineGameObject(GameObject parent, double[] lineColour) {
        super(parent);
        start = new double[] {,};
        end = new double[] {,};
        myLineColour = lineColour;
    }

    /**
      * Create a line game object and add it to the scene tree
      *
      * the line starts from (x, y) to (x, y)
      *
      * The line colour can possibly be null, in which case the object
      * should not be drawn.
      *
      * @param parent The parent in the scene tree
      * @param x, y The coordinate where the line starts
      * @param x, y The coordinate where the line ends
      * @param lineColour The line colour in [r, g, b, a] form
      */
    public LineGameObject(GameObject parent,  double x, double y,
            double x, double y, double[] lineColour) {
        super(parent);
        start = new double[] {x, y};
        end = new double[] {x, y};
        myLineColour = lineColour;
    }

    /**
      * Get the start coordinate point
      *
      * @return start coordinate
      */
    public double[] getStart() {
        return start;
    }

    /**
      * Set the start coordinate point
      *
      * @param start
      */
    public void setStart(double[] start) {
        this.start = start;
    }

    /**
      * Get the end coordinate point
      *
      * @return end coordinate
      */
    public double[] getEnd() {
        return end;
    }

    /**
      * Set the end coordinate point
      *
      * @param end
      */
    public void setEnd(double[] end) {
        this.end = end;
    }

    /**
      * Get the line colour.
      *
      * @return
      */
    public double[] getLineColour() {
        return myLineColour;
    }

    /**
      * Set the line colour.
      *
      * Setting the colour to null means the outline should not be drawn
      *
      * @param lineColour
      */
    public void setLineColour(double[] lineColour) {
        myLineColour = lineColour;
    }

    /**
      * Draws the circular GameObject if the lineColour is not null
      *
      */
    @Override
    public void drawSelf(GL gl) {
        if (myLineColour == null) return;
        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glColord(myLineColour[], myLineColour[], myLineColour[]);
        gl.glVertexd(start[], start[]);
        gl.glVertexd(end[], end[]);
        gl.glEnd();
    }
}
