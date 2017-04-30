package flowAnalyzer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Vector;

import flowAnalyzer.Util;

public class Stream {
	private double maxAcc = 2;
	private double minSpeed = 0;

	private boolean canRandomBreake = false;
	boolean activeBreakeInStream = false;

	int addRateMin = 1;
	int addRateMax = 5;
	int maxCount = 20;
	public int throughPut = 0;
	public int BreakCounts = 0;

	int spaceBetweenElement;

	int timeClick = 0;

	int x = 1;
	int y;
	public int length = 1500;
	public Vector<FlowElement> stream = new Vector<FlowElement>(10, 10);

	private FlowElement generateNewFlowElement() {
		
		double speed = Util.randInt((int)minSpeed*1000, (int)maxAcc*1000) / 100000;
		
		return new FlowElement(x, y, speed);

	}
	
	

	public double getMaxAcc() {
		return maxAcc;
	}



	public void setMaxAcc(double maxAcc) {
		this.maxAcc = maxAcc;
	}



	public Stream(int spaceBetweenElement, int yPosition, boolean CanBreake) {
		this.spaceBetweenElement = spaceBetweenElement;
		this.y = yPosition;
		this.canRandomBreake = CanBreake;
		stream.add(0, generateNewFlowElement());
	}

	public void draw(Graphics2D g2d) {

		for (FlowElement flowElement : stream) {
			flowElement.draw(g2d);
		}
		String txt = "| Breaks :"  + BreakCounts
				   + " MaxAcc : " + maxAcc;
		g2d.drawString(txt, length+10, y-15);
		g2d.setColor(Color.CYAN);
		//g2d.drawRect(length+200, y, throughPut, 10);
		
		g2d.fillRect(length+210, y, throughPut, 10);
		g2d.setColor(Color.black);
		g2d.drawString(""+throughPut,length+210, y-15);

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
			a.speedDown(b.acc);
			// f1.speedBreake();
			// f1.acc = f2.acc;
			retVal =  true;
		} else {
			a.speedUp(maxAcc);
			retVal = false;
		}
		return retVal;
	}
	
	private void adjustSpeed() {
		for (int j = 0; j < stream.size() - 1; j++) {
			FlowElement a = stream.get(j);
			if (j < stream.size()) {
				FlowElement b = stream.get(j + 1);
				testImpact(a,b);

			}
			

			// setup random breake
			if (canRandomBreake && activeBreakeInStream == false && a.locX > length / 4)
				if (Util.randInt(0, 5000) == 0) {
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
				if (stream.get(delIndx).didBreak == true)
				{
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
