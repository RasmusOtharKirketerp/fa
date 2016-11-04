package flowAnalyzer;

import java.awt.Color;
import java.awt.Graphics2D;

public class FlowElement {
	// Location prestent

	float locX, locY;

	float speedX, speedY;

	Color c = new Color(100, Util.randInt(200, 255), 255);

	public FlowElement(float x, float y, float speed, float angleInDegree) {
		super();
		this.locX = x;
		this.locY = y;

		this.speedX = (float) (speed * Math.cos(Math.toRadians(angleInDegree)));
		this.speedY = (float) (-speed * (float) Math.sin(Math.toRadians(angleInDegree)));
	}

	public void move() {
		this.locX += this.speedX;
		this.locY += this.speedY;
	}

	public float nextX() {
		return (this.locX + this.speedX);
	}

	public float nextY() {
		return (this.locY + this.speedY);
	}

	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.drawString("x:"+this.locX ,  this.locX, this.locY-20);
		
		
	}

}
