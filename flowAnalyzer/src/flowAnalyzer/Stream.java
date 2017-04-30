package flowAnalyzer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Vector;

import flowAnalyzer.Util;

public class Stream {

	// Private
	private double maxAcc = 2;
	private double minSpeed = 0;
	private boolean canRandomBreake = false;
	private int streamId = 0;

	private FlowElement generateNewFlowElement() {
		double speed = Util.randInt((int) minSpeed * 1000, (int) maxAcc * 1000) / 100000;
		return new FlowElement(x, y, speed);
	}

	// Public
	public boolean activeBreakeInStream = false;
	public int addRateMin = 1;
	public int addRateMax = 5;
	public int maxCount = 10;
	public int throughPut = 0;
	public int BreakCounts = 0;
	public int spaceBetweenElement;

	public int x = 1;
	public int y;
	public int length = 1500;
	public Vector<FlowElement> stream = new Vector<FlowElement>(10, 10);

	// Constuctor
	public Stream(int id,int spaceBetweenElement, int yPosition, boolean CanBreake) {
		this.streamId = id;
		this.spaceBetweenElement = spaceBetweenElement;
		this.y = yPosition;
		this.canRandomBreake = CanBreake;
		stream.add(0, generateNewFlowElement());
	}

	// getters and setters
	public double getMaxAcc() {
		return maxAcc;
	}

	public void setMaxAcc(double maxAcc) {
		this.maxAcc = maxAcc;
	}
	
	
	

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public int getStreamId() {
		return streamId;
	}

	public void draw(Graphics2D g2d) {
		for (FlowElement flowElement : stream) {
			flowElement.draw(g2d);
		}
		String txt = "(" + BreakCounts+")";
		g2d.setColor(Color.CYAN);
		g2d.drawString(txt, length + 10, y+15);
		g2d.fillRect(length + 100, y, throughPut, 15);
		g2d.setColor(Color.black);
		g2d.drawString("" + throughPut, length + 100, y+15);

	}

	private void fillStream() {
		// fill stream randomly over time
		// if (Util.randInt(addRateMin, addRateMax) == addRateMax)
		if (stream.size() < maxCount) {
			FlowElement newE = generateNewFlowElement();
			if (stream.size() > 0 && testImpact(newE, stream.get(0)) == false) {
				stream.add(0, newE);
				// System.out.println("Added new Element : " +
				// stream.size());
			}

		}
	}

	private boolean collision(FlowElement f1, FlowElement f2) {
		// A--------> ( Anew ) ------------------------------------------= false
		// A ----------------------------------> ( Anew ) ---------------= true
		// A -----------------> ( Anew ) --------------------------------= true
		// --------------------( B )
		// ---------------------------------------------

		double aNew = f1.locX + f1.acc + (f2.size / 2);
		double b = f2.locX - (f2.size / 2);

		boolean retval = true;

		if (aNew <= b)
			retval = false;
		if (aNew > b)
			retval = true;

		return retval;

	}

	private boolean testImpact(FlowElement a, FlowElement b) {
		boolean impact = collision(a, b);

		boolean retVal = false;

		if (impact) {
			a.speedDown(b.acc * 0.9);
			retVal = true;
		} else {
			a.speedUp(maxAcc);
			retVal = false;
		}
		return retVal;
	}
	
	private int getLengthInElementBetween(FlowElement a, FlowElement b){
		int retVal = 0;
		retVal = (b.locX - a.locX) / a.size;	
		return retVal;
	}

	private void adjustSpeed() {
		int spaceBetween = 0;
		for (int j = 0; j < stream.size() - 1; j++) {
			FlowElement a = stream.get(j);
			
			// Test for impact
			if (j < stream.size()) {
				FlowElement b = stream.get(j + 1);
				testImpact(a, b);
				// test for adjust speed 
				if (this.getStreamId() > 16){
					spaceBetween = getLengthInElementBetween(a, b);
					if (spaceBetween > 2 && spaceBetween < 10)
					{
						a.speedDown(b.acc);
					}
					
				}

			}
			
			

			// setup random breaks
			if (canRandomBreake && activeBreakeInStream == false && a.locX > length / 4)
				if (Util.randInt(0, 10000) == 0) {
					a.speedBreake();
					activeBreakeInStream = true;
					this.BreakCounts += 1;
				}
		}
		stream.lastElement().cForward = Color.PINK;
		stream.lastElement().speedUp(maxAcc);

	}

	private void moveElements() {
		// move elements
		for (FlowElement flowElement : stream) {

			flowElement.move();
		}
	}

	private void removeElements() {

		if (stream.size() > 0) {
			int i = 0, delIndx = 0;
			for (FlowElement flowElement : stream) {
				if (flowElement.locX > length)
					delIndx = i;
				i++;
			}
			if (delIndx > 0) {
				if (stream.get(delIndx).didBreak == true) {
					activeBreakeInStream = false;
				}
				this.throughPut += 1;
				stream.remove(delIndx);

			}
		}

	}

	public void clickStream() {

		fillStream();
		adjustSpeed();
		moveElements();
		removeElements();

	}

	@SuppressWarnings("unused")
	private void debugClickStream() {
		for (FlowElement flowElement : stream) {
			System.out.print(flowElement.locX + " ");
		}
		System.out.println();
	}

}
