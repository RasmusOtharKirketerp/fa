package flowAnalyzer;

import java.util.ArrayList;

import flowAnalyzer.Util;

public class Stream {
	int maxSpeed = 3;
	int minSpeed = 1;

	int addRateMin = 1;
	int addRateMax = 15;
	int maxCount = 3;

	int timeClick = 0;

	int x = 1;
	int y = 100;
	public int length = 1600;

	int angel = 0;
	public ArrayList<FlowElement> stream = new ArrayList<FlowElement>();

	private FlowElement generateNewFlowElement() {
		return new FlowElement((float) x, (float) y, (float) Util.randInt(minSpeed, maxSpeed), (float) angel);

	}

	public Stream() {
		FlowElement newE = generateNewFlowElement();
		stream.add(newE);
	}



	public void clickStream() {
		// fill stream randomly over time
		if (Util.randInt(addRateMin, addRateMax) == addRateMax)
			if (stream.size() < maxCount) {
				FlowElement newE = generateNewFlowElement();
				stream.add(newE);

			}

		// Empty stream when flowelement reach end
		for (int idx = 0; idx < stream.size(); idx++) {

			if (stream.get(idx).locX > this.length)
				stream.remove(idx);
		}

		// move elements
		for (int idx = 0; idx < stream.size(); idx++) {
				stream.get(idx).move();
		}

	}

	public FlowElement getFlowElement(int idx) {
		return stream.get(idx);
	}

	public int getSize() {
		return stream.size();
	}

}
