package laab2;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

public class CarAnimations extends JPanel implements ActionListener{
	private static int maxWidth;
	private static int maxHeight;
	private Timer timer;

	private double angle = 0;
	private double scale = 1;
	private double delta = 0.01;

	public CarAnimations() {
		timer = new Timer(10, this);
		timer.start();
	}


	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		// Set rendering params.
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);

		// Set background color.
		g2d.setBackground(new Color(75, 0, 130));
		g2d.clearRect(0, 0, maxWidth, maxHeight);

		// Set (0;0) to the center to draw main Frame.
		g2d.translate(maxWidth/2, maxHeight/2);
		// Draw the main Frame.
		BasicStroke bs2 = new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g2d.setStroke(bs2);
		g2d.drawRect(-320, -320, 640, 640);


//		double[][] trianglePoints = {{160.0, 10.0}, {130.0, 150.0}, {280.0, 140.0}};
//
//		GeneralPath triangle = new GeneralPath();
//		triangle.moveTo(trianglePoints[0][0], trianglePoints[0][1]);
//		for (int k = 1; k < trianglePoints.length; k++)
//			triangle.lineTo(trianglePoints[k][0], trianglePoints[k][1]);
//		triangle.closePath();


		g2d.scale(scale, scale);
		g2d.rotate(angle, 10, -5);
		g2d.setColor(new Color(0, 100, 0));
		g2d.fillRect(-90, -25, 100, 90);


		g2d.setColor(new Color(0, 100, 0));
		g2d.fillRect(-12, -25, 130, 90);

		int xpoints[] = {-224, -120, -89, -89, -224};
		int ypoints[] = {3, -25, -25, 65, 65};
		int npoints = 5;


		g2d.setColor(new Color(0, 100, 0));
		g2d.fillPolygon(xpoints, ypoints, npoints);

		int xpoints1[] = {-120, -89, 60, 90};
		int ypoints1[] = {-25, -80,  -80, -25};
		int npoints1 = 4;


		g2d.setColor(new Color(173, 216, 230));
		g2d.fillPolygon(xpoints1, ypoints1, npoints1);

		GradientPaint gpBody = new GradientPaint(               
				-20, -20,
				new Color(192, 192, 192),
				100, 20,
				new Color(0, 128, 128),true);
		g2d.setPaint(gpBody);
		g2d.fill(new Ellipse2D.Double(-230, 8, 10, 30 ));

		g2d.setColor(Color.BLACK);
		Stroke stroke1 = new BasicStroke(3f);
		g2d.setStroke(stroke1);
		g2d.drawLine(-87, 65, -87, -80);

		g2d.setColor(Color.BLACK);
		Stroke stroke2 = new BasicStroke(3f);
		g2d.setStroke(stroke2);
		g2d.drawLine(-5, 65, -5, -80);

		g2d.setColor(Color.BLACK);
		Stroke stroke3 = new BasicStroke(3f);
		g2d.setStroke(stroke3);
		g2d.drawLine(58, 65, 58, -80);

		g2d.setColor(Color.BLACK);
		Stroke stroke4 = new BasicStroke(3f);
		g2d.setStroke(stroke4);
		g2d.drawLine(-87, -80, 58, -80);

		g2d.setColor(new Color(0, 0, 0));
		g2d.fillRoundRect(-165, 65, 55, 55, 100, 100);

		g2d.setColor(new Color(0, 0, 0));
		g2d.fillRoundRect(40, 65, 55, 55, 100, 100);

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Lab2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750, 750);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(new CarAnimations());
		frame.setVisible(true);

		Dimension size = frame.getSize();
		Insets insets = frame.getInsets();
		maxWidth = size.width - insets.left - insets.right - 1;
		maxHeight = size.height - insets.top - insets.bottom - 1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (scale < 0.01 || scale > 0.99)
			delta = -delta;

		scale += delta;
		angle -= 0.01;

		repaint();
	}

}



