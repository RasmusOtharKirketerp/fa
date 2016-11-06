package flowAnalyzer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main extends JPanel {
	static int countStreams = 100;

	static Flow f = new Flow(countStreams);

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.setBackground(Color.black);
        f.draw(g2d);
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("FlowAnlyzer by ROM Soft");
		Main game = new Main();
		frame.add(game);
		frame.setSize(2000, countStreams * 100);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while (true) {
			game.repaint();
			// s.clickStream();
			Thread.sleep(5);
		}
	}
}