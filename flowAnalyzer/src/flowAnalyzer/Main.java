package flowAnalyzer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main extends JPanel {

	static River river = new River(30);
	
	

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// g2d.drawLine(Alma.loc.x,Alma.loc.y, Alma.loc.x+2,Alma.loc.y+2);
		this.setBackground(Color.black);
		river.draw(g2d);
	}
	static public KeyListener myKL = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == 'w') {
				river.ChangeSpeedToStream(1.01);
			}
			if (e.getKeyChar() == 's') {
				river.ChangeSpeedToStream(0.99);
			}
			if (e.getKeyChar() == 'd') {
				river.ChangeMaxCount(+1);
			}
			if (e.getKeyChar() == 'a') {
				river.ChangeMaxCount(-1);
			}
			if (e.getKeyChar() == 'r') {
				river.reset();
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
		}
	};

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("FlowAnlyzer by ROM soft");
		Main game = new Main();
		frame.add(game);
		frame.setSize(2000, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(myKL);

		while (true) {
			game.repaint();
			Thread.sleep(10);
		}
	}
}