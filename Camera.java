package ass;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.glu.GLU;

/**
  * The camera is a GameObject that can be moved, rotated and scaled like any other.
  *
  * TODO: You need to implement the setView() method.
  *       The methods you need to complete are at the bottom of the class
  *
  * @author Sid Luo
  */

public class Camera extends GameObject {

    private float[] myBackground;

    public Camera(GameObject parent) {
        super(parent);

        myBackground = new float[];
    }

    public Camera() {
        this(GameObject.ROOT);
    }

    public float[] getBackground() {
        return myBackground;
    }

    public void setBackground(float[] background) {
        myBackground = background;
    }

    // ===========================================
    // COMPLETE THE METHODS BELOW
    // ===========================================


    public void setView(GL gl) {

        gl.glClearColor(myBackground[], myBackground[], myBackground[], myBackground[]);

        // TODO. set the view matrix to account for the camera's position
        gl.glMatrixMode(GL.GL_MODELVIEW);
        //set it to the identity matrix
        gl.glLoadIdentity();
        // apply the view transform
        gl.glScaled(. / super.getGlobalScale(),. / super.getGlobalScale(),);
        gl.glRotated(-super.getGlobalRotation(),,,);
        gl.glTranslated(-super.getGlobalPosition()[], -super.getGlobalPosition()[],);

    }

    public void reshape(GL gl, int x, int y, int width, int height) {

        // match the projection aspect ratio to the viewport
        // to avoid stretching

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        double top, bottom, left, right;

        if (width > height) {
            double aspect = (. * width) / height;
            top =.;
            bottom = -.;
            left = -aspect;
            right = aspect;
        }
        else {
            double aspect = (. * height) / width;
            top = aspect;
            bottom = -aspect;
            left = -;
            right =;
        }
        GLU myGLU = new GLU();
        // coordinate system (left, right, bottom, top)
        myGLU.gluOrthoD(left, right, bottom, top);
    }
}