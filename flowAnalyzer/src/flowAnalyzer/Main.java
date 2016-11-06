package flowAnalyzer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main extends JPanel {

	static Stream s = new Stream(5);

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// g2d.drawLine(Alma.loc.x,Alma.loc.y, Alma.loc.x+2,Alma.loc.y+2);
		this.setBackground(Color.black);
		for (FlowElement flowElement : s.stream) {
			flowElement.draw(g2d);

		}
		s.clickStream();

	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("FlowAnlyzer by ROM soft");
		Main game = new Main();
		frame.add(game);
		frame.setSize(2000, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while (true) {
			game.repaint();
			//s.clickStream();
			Thread.sleep(5);
		}
	}
}