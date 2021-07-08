package ass1;
import javax.swing.JFrame;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;


/**
 * A simple class to view MyCoolGameObject
 *
 * @author angf
 */
public class TestMyCoolGameObject {

	public static void createTestObjects(){
		
		//Should look good when we create your MyCoolGameObject using the default constructor
		//By default we test on a black background
		//Write a comment to your tutor in your MyCoolGameObject file if your object does not look ok on a 
                //black background and it looks better with a white or a red backgroud
		//Write instructions in your comments if you have mouse interaction
		
		MyCoolGameObject cgo = new MyCoolGameObject(); //Won't compile until you have written this class
		
		// Should not break if we apply transformations for example
		// If we uncommented these lines (or wrote other transformations) 
		// it should not break your object
		
//		cgo.translate(-0.2,0.2);
//		cgo.rotate(45);
//		cgo.scale(2);
		 
	}
   
    /**
     * A simple test for MyCoolGameObject
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Initialise OpenGL
        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities(glprofile);
        
        // create a GLJPanel to draw on
        GLJPanel panel = new GLJPanel(glcapabilities);

        // Create a camera
        Camera camera = new Camera(GameObject.ROOT);
        
        createTestObjects();
        
        // Add the game engine
        GameEngine engine = new GameEngine(camera);
        panel.addGLEventListener(engine);

	// Add a mouse event handler        
        panel.addMouseListener(Mouse.theMouse);
        panel.addMouseMotionListener(Mouse.theMouse); 

        // Add an animator to call 'display' at 60fps        
        FPSAnimator animator = new FPSAnimator(60);
        animator.add(panel);
        animator.start();

        // Put it in a window
        JFrame jFrame = new JFrame("Test MyCoolGameObject");
        jFrame.add(panel);
        jFrame.setSize(600, 600);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        float scale[] = new float[2];
        panel.getCurrentSurfaceScale(scale);
        Mouse.theMouse.setSurfaceScale(scale);

    }

}
