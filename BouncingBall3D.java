package bouncingball3d;

/**
 *
 * @author Slam
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BouncingBall3D extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    // Dimensiones de la ventana de la aplicación
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    
    // Radio y color de la bola
    private static final int BALL_RADIUS = 50;
    private static final Color BALL_COLOR = Color.RED;
    
    // Coordenadas y velocidad de la bola en el espacio 3D
    private double x = WIDTH / 2;
    private double y = HEIGHT / 2;
    private double z = BALL_RADIUS;
    private double vx = 2;
    private double vy = 3;
    private double vz = 1;

    // Constructor de la clase
    public BouncingBall3D() {
        // Crea un temporizador que actualizará la animación 60 veces por segundo
        Timer timer = new Timer(1000 / 60, this);
        timer.start();
    }
    
    // Método que dibuja la bola en la pantalla
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Dibuja la bola como un círculo relleno
        g2d.setColor(BALL_COLOR);
        g2d.fillOval((int) (x - BALL_RADIUS), (int) (y - BALL_RADIUS), 2 * BALL_RADIUS, 2 * BALL_RADIUS);
    }
    
    // Método que actualiza la posición y velocidad de la bola en cada ciclo de animación
    public void actionPerformed(ActionEvent e) {
        // Actualiza la posición de la bola
        x += vx;
        y += vy;
        z += vz;
        
        // Rebota la bola si llega a los límites de la pantalla
        if (x - BALL_RADIUS < 0 || x + BALL_RADIUS > WIDTH) {
            vx = -vx;
        }
        if (y - BALL_RADIUS < 0 || y + BALL_RADIUS > HEIGHT) {
            vy = -vy;
        }
        if (z - BALL_RADIUS < 0 || z + BALL_RADIUS > Math.min(WIDTH, HEIGHT)) {
            vz = -vz;
        }
        
        // Redibuja la bola en la nueva posición
        repaint();
    }
    
    // Método principal que crea una ventana y agrega la bola al panel de dibujo
    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing Ball 3D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        BouncingBall3D ball = new BouncingBall3D();
        frame.add(ball);
        
        frame.setVisible(true);
    }
}
