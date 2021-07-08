package kitkat;

import java.io.IOException;

import javax.swing.JFrame;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;

import ass1.Camera;
import ass1.GameEngine;
import ass1.Mouse;
;

/**
 * A game that uses assignment 1's game engine
 * @author Guozhao Luo
 *
 */

public class KitKatGame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private GLJPanel panel;
	public static final KitKatGame theGame = new KitKatGame();
	
	private KitKatGame() {
		super("KitKat Game Demo");
	}
	
	private void init() throws IOException {
		// Initialise OpenGL
		GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities(glprofile);
        // create a GLJPanel to draw on
        panel = new GLJPanel(glcapabilities);

        // Create a camera
        
        Tessellation t = new Tessellation();
        Camera camera = new Camera(t);
        camera.scale(300);
        
        
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
        jFrame.setSize(800, 600);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        float scale[] = new float[2];
        panel.getCurrentSurfaceScale(scale);
        Mouse.theMouse.setSurfaceScale(scale);
        
	}
	
	public static void main(String[] args) throws IOException {
        theGame.init();
    }
}
