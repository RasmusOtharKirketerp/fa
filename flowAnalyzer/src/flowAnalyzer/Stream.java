package flowAnalyzer;

import java.util.ArrayList;

import flowAnalyzer.Util;

public class Stream {
	int maxSpeed = 11;
	int minSpeed = 1;
	
	int addRateMin = 1;
	int addRateMax = 5;
	int maxCount = 5;
	
	int timeClick = 0;

    public ArrayList<FlowElement> stream = new ArrayList<FlowElement>();
    
    private FlowElement generateNewFlowElement()
    {
		return new FlowElement((float)1,(float)100, (float) Util.randInt(minSpeed, maxSpeed),(float) 0);
    	
    }
	
	public Stream()
	{
			FlowElement newE = generateNewFlowElement();
			stream.add(newE);			
	}
	
	
	public void clickStream(){
		FlowElement newE = generateNewFlowElement();
		stream.add(newE);	
		
	}
	
	public FlowElement getFlowElement(int idx){
		return stream.get(idx);
	}
	
	public int getSize()
	{
		return stream.size();
	}
	
	
}
	
