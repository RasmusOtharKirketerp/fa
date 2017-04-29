package flowAnalyzer;

import java.awt.Color;
import java.awt.Graphics2D;

public class FlowElement implements Comparable<FlowElement> {
	// Location present

	int locX, locY;

	int size = 10;
	
	boolean didBreak = false;

	double acc = Util.randInt(10, 100) / 2;
	//double acc = 1.1;

	//private float speedX, speedY;

	Color cForward = new Color(100, Util.randInt(200, 255), Util.randInt(0, 255));
	Color cBack = new Color(255, 0, 0);
	Color c = new Color(50, Util.randInt(200, 255), 255);

	public void setColor() {
		// c. = Color(50, Util.randInt(200, 255), 255);
	}

	public FlowElement(int x, int y, float speed, float angleInDegree) {
		super();
		this.locX = x;
		this.locY = y;

		//this.speedX = (float) (speed * Math.cos(Math.toRadians(angleInDegree)));
		//this.speedY = (float) (-speed * (float) Math.sin(Math.toRadians(angleInDegree)));
	}

	public void move() {
		this.locX += acc;
		//this.locY += this.speedY / 1;
	}

	public float nextX() {
		return (this.locX + (int)this.acc);
	}

	public void speedUp() {
		this.acc += acc/200;
		//this.c = this.cForward;

	}

	public void speedBreake() {
		this.acc = this.acc / 30;
	}

	public void speedDown() {
		speedBreake();
		this.c = this.cBack;
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(this.cForward);
		g2d.fillOval((int) this.locX, (int) this.locY, size, size);
	}

	@Override
	public int compareTo(FlowElement o) {
		return this.locX - o.locX;
	}
}
