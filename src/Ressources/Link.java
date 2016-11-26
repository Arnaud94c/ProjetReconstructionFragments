package Ressources;

public class Link
{
	
	private int sourceId ;
	private int destinationId;
	private int value=0;
	private boolean chaineSourceCompl;
	private boolean chaineDestinationCompl;
	
	public Link (int source, int destination,boolean chaineSourceCompl,boolean chaineDestinationCompl, int value)
	{
		this.sourceId=source;
		this.destinationId=destination;
		this.value=value;
		this.chaineSourceCompl=chaineSourceCompl;
		this.chaineDestinationCompl=chaineDestinationCompl;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public int getSourceId()
	{
		return this.sourceId;
	}
	
	public int getDestinationId()
	{
		return this.destinationId;
	}

	
	
	
	

}
