package flowAnalyzer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main extends JPanel {

	static Stream s = new Stream();

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// g2d.drawLine(Alma.loc.x,Alma.loc.y, Alma.loc.x+2,Alma.loc.y+2);
		FlowElement e;
		for (int idx = 0; idx < s.getSize(); idx++) {
			e = s.getFlowElement(idx);
			g2d.setColor(e.c);
			// g2d.drawLine(700,600, e.loc.x,e.loc.y);
			g2d.fillOval((int) e.locX, (int) e.locY, 14, 14);

			g2d.setColor(Color.BLACK);
			g2d.drawRect(1, s.y - 2, s.length, 16);
			
			e.draw(g2d);

		}

	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("FlowAnlyzer by ROM soft");
		Main game = new Main();
		frame.add(game);
		frame.setSize(1900, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while (true) {
			game.repaint();
			s.clickStream();
			Thread.sleep(100);
		}
	}
}