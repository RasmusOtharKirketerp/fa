package flowAnalyzer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main extends JPanel {

	static River river = new River(200);

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// g2d.drawLine(Alma.loc.x,Alma.loc.y, Alma.loc.x+2,Alma.loc.y+2);
		this.setBackground(Color.black);
		river.draw(g2d);
		

	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("FlowAnlyzer by ROM soft");
		Main game = new Main();
		frame.add(game);
		frame.setSize(2000, 1100);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while (true) {
			game.repaint();
			//s.clickStream();
			Thread.sleep(5);
		}
	}
}