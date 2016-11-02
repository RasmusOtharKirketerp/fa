package flowAnalyzer;

import java.util.ArrayList;

import flowAnalyzer.Util;

public class Stream {
	int maxSpeed;
	
	//Point center = new Point(700,600);
	
    public ArrayList<FlowElement> stream = new ArrayList<FlowElement>();
	
	public Stream(int count)
	{
		for (int i = 0; i < count; i++) {
			//FlowElement newE = new FlowElement((float)700,(float)600, (float) Util.randInt(1,20),(float)Util.randInt(0,360));
			FlowElement newE = new FlowElement((float)1,(float)100, (float) Util.randInt(1,20),(float) 0);

			stream.add(newE);			
		}
	
	}
	
	public FlowElement getFlowElement(int idx){
		return stream.get(idx);
	}
	
	public int getSize()
	{
		return stream.size();
	}
	
	
}
	
