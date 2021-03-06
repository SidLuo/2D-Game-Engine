package ass;
import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

/**
  * The GameEngine is the GLEventListener for our game.
  *
  * Every object in the scene tree is updated on each display call.
  * Then the scene tree is rendered.
  *
  * You shouldn't need to modify this class.
  *
  * @author Sid Luo
  */

public class GameEngine implements GLEventListener {

    private Camera myCamera;
    private long myTime;

    /**
      * Construct a new game engine.
      *
      * @param camera The camera that is used in the scene.
      */
    public GameEngine(Camera camera) {
        myCamera = camera;
    }

    /**
      * @see javax.media.opengl.GLEventListener#init(javax.media.opengl.GLAutoDrawable)
      */
    @Override
    public void init(GLAutoDrawable drawable) {
        // initialise myTime
        myTime = System.currentTimeMillis();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // ignore
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
            int height) {

        // tell the camera and the mouse that the screen has reshaped
        GL gl = drawable.getGL().getGL();

        myCamera.reshape(gl, x, y, width, height);

        // this has to happen after myCamera.reshape() to use the new projection
        Mouse.theMouse.reshape(gl);
    }


    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL().getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        // set the view matrix based on the camera position
        myCamera.setView(gl);

        // update the mouse position
        Mouse.theMouse.update(gl);

        // update the objects
        update();

        // draw the scene tree
        GameObject.ROOT.draw(gl);
    }

    private void update() {

        // compute the time since the last frame
        long time = System.currentTimeMillis();
        double dt = (time - myTime) /.;
        myTime = time;

        // take a copy of the ALL_OBJECTS list to avoid errors
        // if new objects are created in the update
        List<GameObject> objects = new ArrayList<GameObject>(GameObject.ALL_OBJECTS);

        // update all objects
        for (GameObject g : objects) {
            g.update(dt);
        }
    }
}
