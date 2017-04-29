package flowAnalyzer;

import java.awt.Graphics2D;
import java.util.Vector;

import flowAnalyzer.Util;

public class Stream {
	int maxSpeed = 2;
	int minSpeed = 1;

	int addRateMin = 1;
	int addRateMax = 5;
	int maxCount = 20;

	int spaceBetweenElement;

	int timeClick = 0;

	int x = 1;
	int y;
	public int length = 2000;

	int angel = 0;
	public Vector<FlowElement> stream = new Vector<FlowElement>(10, 10);

	private FlowElement generateNewFlowElement() {
		return new FlowElement(x, y, (float) Util.randInt(minSpeed, maxSpeed), (float) angel);

	}

	public Stream(int spaceBetweenElement, int yPosition) {
		this.spaceBetweenElement = spaceBetweenElement;
		this.y = yPosition;
		stream.add(0, generateNewFlowElement());
	}

	public void draw(Graphics2D g2d) {

		for (FlowElement flowElement : stream) {
			flowElement.draw(g2d);

		}

	}

	private void fillStream() {
		// fill stream randomly over time
		if (Util.randInt(addRateMin, addRateMax) == addRateMax)
			if (stream.size() < maxCount) {
				FlowElement newE = generateNewFlowElement();
				if (stream.size() > 0 && testImpact(newE, stream.get(0)) == false) {
					stream.add(0, newE);
					// System.out.println("Added new Element : " +
					// stream.size());
				}

			}
	}

	private boolean testImpact(FlowElement f1, FlowElement f2) {
		if (f1.nextX() + (spaceBetweenElement / 2) > f2.nextX()) {
			f1.speedDown();
			f1.speedBreake();
			return true;
		} else {
			f1.speedUp();
			return false;
		}
	}

	private void adjustSpeed() {
		for (int j = 0; j < stream.size(); j++) {
			FlowElement fe = stream.get(j);
			if (j < stream.size() - 1) {
				FlowElement fe2 = stream.get(j + 1);

				testImpact(fe, fe2);
			}

			if (j == stream.size() - 1) {
				fe.speedUp();

			}

			if (Util.randInt(0, 1000) == 0)
				fe.speedBreake();
		}

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
				i++;
				if (flowElement.nextX() > length)
					delIndx = i;
			}
			if (delIndx > 0)
				stream.remove(delIndx - 1);
		}

	}

	public void clickStream() {

		fillStream();
		adjustSpeed();
		moveElements();
		removeElements();
		sortStream();

		// debugClickStream();

	}

	private void sortStream() {
		stream.sort(null);

	}

	@SuppressWarnings("unused")
	private void debugClickStream() {
		for (FlowElement flowElement : stream) {
			System.out.print(flowElement.locX + " ");
		}
		System.out.println();
	}

}
