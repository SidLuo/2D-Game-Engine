package ass;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashMap;
import java.util.Map;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.glu.GLU;

/**
  * A mouse handler for the game engine.
  *
  * It keeps track of:
  *) the mouse position in world coordinates
  *) mouse press/release events that have happened in the past frame
  *
  * Mouse is a singleton class. There is only one instance, stored in Mouse.theMouse.
  *
  * You need to add this object as a MouseListener and MouseMotionListener to your GLJPanel to make it work:
  *
  * panel.addMouseListener(Mouse.theMouse);
  * panel.addMouseMotionListener(Mouse.theMouse);
  *
  * You should not need to modify this class.
  *
  * @author Sid Luo
  */

public class Mouse extends MouseAdapter {

    public static final Mouse theMouse = new Mouse();
    private int[] myViewport;
    private double[] myProjMatrix;
    private double[] myMVMatrix;

    private MouseEvent myMouse;

    private boolean[] myPressed;
    private boolean[] myReleased;
    private boolean[] myWasPressed;
    private boolean[] myWasReleased;

    private float surfaceScale[];

    private Mouse() {
        myMouse = null;
        myViewport = new int[];
        myProjMatrix = new double[];
        myMVMatrix = new double[];

        myMouse = null;

        myPressed = new boolean[];
        myReleased = new boolean[];
        myWasPressed = new boolean[];
        myWasReleased = new boolean[];

        surfaceScale = new float[]{.f,.f};
    }

    /**
      * When the window is reshaped, store the new projection matrix and viewport
      *
      * @param gl
      */
    public void reshape(GL gl) {
        gl.glGetIntegerv(GL.GL_VIEWPORT, myViewport,);
        gl.glGetDoublev(GL.GL_PROJECTION_MATRIX, myProjMatrix,);
    }

    /**
      * When the view is updated, store the new model-view matrix.
      *
      * Update any mouse presses or releases that have happened.
      *
      * @param gl
      */
    public void update(GL gl) {
        gl.glGetDoublev(GL.GL_MODELVIEW_MATRIX, myMVMatrix,);

        for (int i =; i <; i++) {
            myWasPressed[i] = myPressed[i];
            myWasReleased[i] = myReleased[i];
            myPressed[i] = false;
            myReleased[i] = false;
        }
    }

    public void setSurfaceScale(float[] scale) {
        surfaceScale = scale;
    }

    /**
      * Get the current mouse position in world coordinates.
      *
      * @return
      */
    public double[] getPosition() {
        double[] p = new double[];
        if (myMouse != null) {
            double x = myMouse.getX()*surfaceScale[];
            double y = myMouse.getY()*surfaceScale[];

            GLU glu = new GLU();
            /* note viewport[] is height of window in pixels */
            y = myViewport[] - y -;

            glu.gluUnProject((double) x, (double) y,., //
                myMVMatrix,, myProjMatrix,, myViewport,, p,);

        }

        return p;
    }

    /**
      * Store mouse movement events to record the latest mouse position.
      *
      * @see java.awt.event.MouseAdapter#mouseMoved(java.awt.event.MouseEvent)
      */
    @Override
    public void mouseMoved(MouseEvent e) {
        myMouse = e;
    }

    /**
      * Store mouse movement events to record the latest mouse position.
      *
      * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
      */
    @Override
    public void mouseDragged(MouseEvent e) {
        myMouse = e;
    }

    /**
      * Store the most recent mouse press event for each of the buttons.
      *
      * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
      */
    @Override
    public void mousePressed(MouseEvent e) {
        myPressed[e.getButton()-] = true;
    }

    /**
      * Store the most recent mouse press release for each of the buttons.
      *
      * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
      */
    @Override
    public void mouseReleased(MouseEvent e) {
        myReleased[e.getButton()-] = true;
    }

    /**
      * Returns true if the specified mouse button was pressed this frame.
      *
      * @param button should be, or
      * @return
      */
    public boolean wasPressed(int button) {
        return myWasPressed[button-];
    }

    /**
      * Returns true if the specified mouse button was released this frame.
      *
      * @param button should be, or
      * @return
      */
    public boolean wasReleased(int button) {
        return myWasReleased[button-];
    }

}
