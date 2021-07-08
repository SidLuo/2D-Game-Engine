package ass;
import java.util.ArrayList;
import java.util.List;
import com.jogamp.opengl.GL;

/**
  * A GameObject is an object that can move around in the game world.
  *
  * GameObjects form a scene tree. The root of the tree is the special ROOT object.
  *
  * Each GameObject is offset from its parent by a rotation, a translation and a scale factor.
  *
  * TODO: The methods you need to complete are at the bottom of the class
  *
  * @author Sid Luo
  */

public class GameObject {

    // the list of all GameObjects in the scene tree
    public final static List<GameObject> ALL_OBJECTS = new ArrayList<GameObject>();

    // the root of the scene tree
    public final static GameObject ROOT = new GameObject();

    // the links in the scene tree
    private GameObject myParent;
    private List<GameObject> myChildren;

    // the local transformation
    // myRotation should be normalised to the range [-..)
    private double myRotation;
    private double myScale;
    private double[] myTranslation;

    // is this part of the tree showing?
    private boolean amShowing;

    /**
      * Special private constructor for creating the root node. Do not use otherwise.
      */
    private GameObject() {
        myParent = null;
        myChildren = new ArrayList<GameObject>();

        myRotation =;
        myScale =;
        myTranslation = new double[];
        myTranslation[] =;
        myTranslation[] =;

        amShowing = true;

        ALL_OBJECTS.add(this);
    }

    /**
      * Public constructor for creating GameObjects, connected to a parent (possibly the ROOT).
      *
      * New objects are created at the same location, orientation and scale as the parent.
      *
      * @param parent
      */
    public GameObject(GameObject parent) {
        myParent = parent;
        myChildren = new ArrayList<GameObject>();

        parent.myChildren.add(this);

        myRotation =;
        myScale =;
        myTranslation = new double[];
        myTranslation[] =;
        myTranslation[] =;

        // initially showing
        amShowing = true;

        ALL_OBJECTS.add(this);
    }

    /**
      * Remove an object and all its children from the scene tree.
      */
    public void destroy() {
        List <GameObject> childrenList = new ArrayList<GameObject>(myChildren);
        for (GameObject child : childrenList) {
            child.destroy();
        }
        if(myParent != null)
                myParent.myChildren.remove(this);
        ALL_OBJECTS.remove(this);
    }

    /**
      * Get the parent of this game object
      *
      * @return
      */
    public GameObject getParent() {
        return myParent;
    }

    /**
      * Get the children of this object
      *
      * @return
      */
    public List<GameObject> getChildren() {
        return myChildren;
    }

    /**
      * Get the local rotation (in degrees)
      *
      * @return
      */
    public double getRotation() {
        return myRotation;
    }

    /**
      * Set the local rotation (in degrees)
      *
      * @return
      */
    public void setRotation(double rotation) {
        myRotation = MathUtil.normaliseAngle(rotation);
    }

    /**
      * Rotate the object by the given angle (in degrees)
      *
      * @param angle
      */
    public void rotate(double angle) {
        myRotation += angle;
        myRotation = MathUtil.normaliseAngle(myRotation);
    }

    /**
      * Get the local scale
      *
      * @return
      */
    public double getScale() {
        return myScale;
    }

    /**
      * Set the local scale
      *
      * @param scale
      */
    public void setScale(double scale) {
        myScale = scale;
    }

    /**
      * Multiply the scale of the object by the given factor
      *
      * @param factor
      */
    public void scale(double factor) {
        myScale *= factor;
    }

    /**
      * Get the local position of the object
      *
      * @return
      */
    public double[] getPosition() {
        double[] t = new double[];
        t[] = myTranslation[];
        t[] = myTranslation[];

        return t;
    }

    /**
      * Set the local position of the object
      *
      * @param x
      * @param y
      */
    public void setPosition(double x, double y) {
        myTranslation[] = x;
        myTranslation[] = y;
    }

    /**
      * Move the object by the specified offset in local coordinates
      *
      * @param dx
      * @param dy
      */
    public void translate(double dx, double dy) {
        myTranslation[] += dx;
        myTranslation[] += dy;
    }

    /**
      * Test if the object is visible
      *
      * @return
      */
    public boolean isShowing() {
        return amShowing;
    }

    /**
      * Set the showing flag to make the object visible (true) or invisible (false).
      * This flag should also apply to all descendents of this object.
      *
      * @param showing
      */
    public void show(boolean showing) {
        amShowing = showing;
    }

    /**
      * Update the object. This method is called once per frame.
      *
      * This does nothing in the base GameObject class. Override this in subclasses.
      *
      * @param dt The amount of time since the last update (in seconds)
      */
    public void update(double dt) {
        // do nothing
    }

    /**
      * Draw the object (but not any descendants)
      *
      * This does nothing in the base GameObject class. Override this in subclasses.
      *
      * @param gl
      */
    public void drawSelf(GL gl) {
        // do nothing
    }


    // ===========================================
    // COMPLETE THE METHODS BELOW
    // ===========================================

    /**
      * Draw the object and all of its descendants recursively.
      *
      * TODO: Complete this method
      *
      * @param gl
      */
    public void draw(GL gl) {

        // don't draw if it is not showing
        if (!amShowing) {
            return;
        }

        // TODO: setting the model transform appropriately
        // draw the object (Call drawSelf() to draw the object itself)
        // and all its children recursively
        gl.glPushMatrix();
        gl.glTranslated(myTranslation[], myTranslation[],);
        gl.glRotated(myRotation,,,);
        gl.glScaled(myScale, myScale,);

        drawSelf(gl);
        for (GameObject go : myChildren) {
            go.draw(gl);
        }
        gl.glPopMatrix();
    }

    /**
      * Compute the object's position in world coordinates
      *
      * TODO: Write this method
      *
      * @return a point in world coordinats in [x,y] form
      */
    public double[] getGlobalPosition() {
        if (myParent == null) return new double[] {,};
        double[] p = myParent.getGlobalPosition();
        double[][] m = MathUtil.multiply(MathUtil.translationMatrix(p),
                        MathUtil.rotationMatrix(myParent.getGlobalRotation()));
        m = MathUtil.multiply(m, MathUtil.scaleMatrix(myParent.getGlobalScale()));
        m = MathUtil.multiply(m, MathUtil.translationMatrix(myTranslation));
        p[] = m[][];
        p[] = m[][];
        return p;
    }

    /**
      * Compute the object's rotation in the global coordinate frame
      *
      * TODO: Write this method
      *
      * @return the global rotation of the object (in degrees) and
      * normalized to the range (-,) degrees.
      */
    public double getGlobalRotation() {
        if (myParent == null) return;
        double r = myParent.getGlobalRotation();
        r += myRotation;
        r = MathUtil.normaliseAngle(r);
        return r;
    }

    /**
      * Compute the object's scale in global terms
      *
      * TODO: Write this method
      *
      * @return the global scale of the object
      */
    public double getGlobalScale() {
        if (myParent == null) return.;
        double s = myParent.getGlobalScale();
        s *= myScale;
        return s;
    }

    /**
      * Change the parent of a game object.
      *
      * TODO: add code so that the object does not change its global position, rotation or scale
      * when it is reparented. You may need to add code before and/or after
      * the fragment of code that has been provided - depending on your approach
      *
      * @param parent
      */
    public void setParent(GameObject parent) {
        // get global variables first
        double[] globalPosition = getGlobalPosition();
        double globalRotation = getGlobalRotation();
        double globalScale = getGlobalScale();

        // get parent variables
        double[] parentPosition = parent.getGlobalPosition();
        // negate the translation
        parentPosition[] = -parentPosition[];
        parentPosition[] = -parentPosition[];
        double parentRotation = parent.getGlobalRotation();
        double parentScale = parent.getGlobalScale();
        double[][] m =  MathUtil.multiply(MathUtil.scaleMatrix(/parentScale),
                                                                                MathUtil.rotationMatrix(-parentRotation));
        m = MathUtil.multiply(m, MathUtil.translationMatrix(parentPosition));

        // translation
        double[][] local = MathUtil.multiply(m, MathUtil.translationMatrix(globalPosition));
        myTranslation[] = local[][];
        myTranslation[] = local[][];
        // rotation
        local = MathUtil.multiply(m, MathUtil.rotationMatrix(globalRotation));
        myRotation =  Math.toDegrees(Math.atan(local[][], local[][]));
        // scale
        local = MathUtil.multiply(m, MathUtil.scaleMatrix(globalScale));
        myScale = Math.sqrt(local[][] * local[][] + local[][] * local[][]);

        // set new parent and children
        myParent.myChildren.remove(this);
        myParent = parent;
        myParent.myChildren.add(this);
    }
}