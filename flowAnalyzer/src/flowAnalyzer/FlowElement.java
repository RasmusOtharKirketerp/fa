package flowAnalyzer;

import java.awt.Color;
import java.awt.Graphics2D;

public class FlowElement implements Comparable<FlowElement> {
	// Location prestent

	int locX, locY;
	
	int size = 10;
	
	float acc = (Util.randInt(1, 10) /10) + 1;

	private float speedX, speedY;

	Color cForward = new Color(100, Util.randInt(200, 255), Util.randInt(0, 255));
	Color cBack = new Color(255,0,0);
	Color c = new Color(50, Util.randInt(200, 255), 255);
    
	
	public FlowElement(int x, int y, float speed, float angleInDegree) {
		super();
		this.locX = x;
		this.locY = y;

		this.speedX = (float) (speed * Math.cos(Math.toRadians(angleInDegree)));
		this.speedY = (float) (-speed * (float) Math.sin(Math.toRadians(angleInDegree)));
	}

	public void move() {
		this.locX += this.speedX/30;
		this.locY += this.speedY/30;
	}

	public float nextX() {
		return (this.locX + this.speedX);
	}

	public float nextY() {
		return (this.locY + this.speedY);
	}
	
	public void speedUp(){
		this.speedX += acc;
		this.c = this.cForward; 
	
	}	
	
	public void speedBreake()
	{
		this.speedX = this.speedX / 3;
	}
	
	public void speedDown(){
		if (this.speedX > 1)
			this.speedX = (float) (this.speedX - (this.speedX * 0.9));
		
		this.c = this.cBack;
	}

	public float getSpeedX() {
		return speedX;
	}

	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		//g2d.drawString("x:" + this.locX + " s:" + this.speedX, this.locX, this.locY - 20);
		g2d.setColor(this.c);
		// g2d.drawLine(700,600, e.loc.x,e.loc.y);
		g2d.fillOval((int) this.locX, (int) this.locY, size	, size);    
	}

	@Override
	public int compareTo(FlowElement o) {
		// TODO Auto-generated method stub

		return this.locX - o.locX;
	}
}
