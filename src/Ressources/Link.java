package Ressources;

public class Link
{
	
	private int sourceId ;
	private int destinationId;
	private int value=0;
	private boolean chaineSourceCompl;
	private boolean chaineDestinationCompl;
	private boolean chaineSourceInverse;
	private boolean chaineDestinationInverse;
	
	public Link (int source, int destination,boolean chaineSourceCompl, boolean chaineSourceInverse,boolean chaineDestinationCompl,boolean chaineDestinationInverse, int value)
	{
		this.sourceId=source;
		this.destinationId=destination;
		this.value=value;
		this.chaineSourceCompl=chaineSourceCompl;
		this.chaineDestinationCompl=chaineDestinationCompl;
		this.chaineSourceInverse=chaineSourceInverse;
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
	public boolean getChaineSourceCompl()
	{
		return this.chaineSourceCompl;
	}
	public boolean getChaineSourceInverse()
	{
		return this.chaineSourceInverse;
	}
	public boolean getChaineDestinationCompl()
	{
		return this.chaineDestinationCompl;
	}
	public boolean getChaineDestinationInverse()
	{
		return this.chaineDestinationInverse;
	}

	

}
