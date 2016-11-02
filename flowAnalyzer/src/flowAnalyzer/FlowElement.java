package flowAnalyzer;

import java.awt.Color;

public class FlowElement {
	// Location prestent

	float locX,locY;
	
	float speedX, speedY;
	
	Color c = new Color(Util.randInt(10, 255),Util.randInt(10, 255),Util.randInt(10, 255));

	public FlowElement(float x, float y, float speed, float angleInDegree) {
		super();
		this.locX = x;
		this.locY = y;
		
		this.speedX = (float)(speed * Math.cos(Math.toRadians(angleInDegree)));
		this.speedY = (float)(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
	}
	
	public void move() {
		this.locX += this.speedX;
		this.locY += this.speedY;
	}
    
	
	
}
