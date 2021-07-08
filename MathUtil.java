package ass;
import java.lang.Math;

/**
  * A collection of useful math methods
  *
  * TODO: The methods you need to complete are at the bottom of the class
  *
  * @author Sid Luo
  */

public class MathUtil {

    /**
      * Normalise an angle to the range [-,)
      *
      * @param angle
      * @return
      */
    static public double normaliseAngle(double angle) {
        return ((angle +.) %. +.) %. -.;
    }

    /**
      * Clamp a value to the given range
      *
      * @param value
      * @param min
      * @param max
      * @return
      */

    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
      * Multiply two matrices
      *
      * @param p Ax matrix
      * @param q Ax matrix
      * @return
      */
    public static double[][] multiply(double[][] p, double[][] q) {

        double[][] m = new double[][];

        for (int i =; i <; i++) {
            for (int j =; j <; j++) {
                m[i][j] =;
                for (int k =; k <; k++) {
                    m[i][j] += p[i][k] * q[k][j];
                }
            }
        }

        return m;
    }

    /**
      * Multiply a vector by a matrix
      *
      * @param m Ax matrix
      * @param v Ax vector
      * @return
      */
    public static double[] multiply(double[][] m, double[] v) {

        double[] u = new double[];

        for (int i =; i <; i++) {
            u[i] =;
            for (int j =; j <; j++) {
                u[i] += m[i][j] * v[j];
            }
        }

        return u;
    }



    // ===========================================
    // COMPLETE THE METHODS BELOW
    // ===========================================


    /**
      * TODO: AD translation matrix for the given offset vector
      *
      * @param pos
      * @return
      */
    public static double[][] translationMatrix(double[] v) {
        double[][] translate = new double[][];
        translate[][] =.;
        translate[][] =.;
        translate[][] =.;
        translate[][] = v[];
        translate[][] = v[];
        return translate;
    }

    /**
      * TODO: AD rotation matrix for the given angle
      *
      * @param angle in degrees
      * @return
      */
    public static double[][] rotationMatrix(double angle) {
        angle = Math.toRadians(angle);
        double[][] rotate = new double[][];
        rotate[][] = rotate[][] = Math.cos(angle);
        rotate[][] -= Math.sin(angle);
        rotate[][] = Math.sin(angle);
        rotate[][] =.;
        return rotate;
    }

    /**
      * TODO: AD scale matrix that scales both axes by the same factor
      *
      * @param scale
      * @return
      */
    public static double[][] scaleMatrix(double scale) {
        double[][] scaleM = new double[][];
        scaleM[][] = scaleM[][] = scale;
        scaleM[][] =.;
        return scaleM;
    }
}
