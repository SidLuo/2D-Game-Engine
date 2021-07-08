package ass;
/**
  *
  * An object that extends GameObject
  * Default colour is green and black
  * @author Sid Luo
  *
  */

public class MyCoolGameObject extends GameObject {
    public MyCoolGameObject() {
        // instantiating with default front colour green and back colour black
        this(GameObject.ROOT, new double[]{., ., .,}, new double[] {,,,});
        }

    public MyCoolGameObject(GameObject parent, double[] colour, double[] backColour) {
        super(parent);

        // head as child
        CircularGameObject head = new CircularGameObject(this, ., colour, null);
        head.translate(., .);

        // body as a child
                new PolygonalGameObject(this,
                                new double[] {,, .,, ., .,, .}, colour, null);
        new LineGameObject(this, ., ., ., ., colour);
        new LineGameObject(this, ., ., ., ., colour);

        // eyes as child
        CircularGameObject eye = new CircularGameObject(this, ., backColour, null);
        CircularGameObject eye = new CircularGameObject(this, ., backColour, null);
        eye.translate(., .);
        eye.translate(., .);
        new PolygonalGameObject(this,
                                new double[] {, ., ., ., ., .,, .}, backColour, null);

        // limbs as children
        GameObject limb = limb(colour);
        limb.translate(-., .);
        GameObject limb = limb(colour);
        limb.translate(., .);
        GameObject limb = limb(colour);
        limb.translate(., -.);

        GameObject limb = limb(colour);
        limb.translate(., -.);

        // moving the origin to the centre of the object
        for (GameObject go: super.getChildren()) {
                go.translate(-., -.);
        }
    }
    /*
      * Creates a limb
      */
    private GameObject limb(double[] colour) {
        PolygonalGameObject limb = new PolygonalGameObject(this,
                                new double[] {,, .,, ., .,, .}, colour, null);
        CircularGameObject round = new CircularGameObject(limb, ., colour, null);
        CircularGameObject round = new CircularGameObject(limb, ., colour, null);
        round.translate(.,);
        round.translate(., .);
        return limb;
    }
}