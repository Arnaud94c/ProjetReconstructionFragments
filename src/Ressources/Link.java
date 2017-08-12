package Ressources;

public class Link
{
	
	// id du noeud source
	private int sourceId ;
	//id du noeud de destination
	private int destinationId;
	//valeur du lien (score alignement)
	private int value=0;
	private int[] max={0,0};
	// indique si le noeud source est inverse et complement dans ce cas true,false sinon.
	private boolean chaineSourceCompl;
	// indique si le noeud destination est inverse et complement dans ce cas true,false sinon.
	private boolean chaineDestinationCompl;
	
	
	/**
	 * Constructeur.
	 * @param source id de la source.
	 * @param destination id de la destination.
	 * @param chaineSourceCompl noeud source est complementaire inverse
	 * @param chaineDestinationCompl noeud destination est complementaire inverse
	 * @param value
	 */
	public Link (int source, int destination,boolean chaineSourceCompl,boolean chaineDestinationCompl, int[] value)
	{
		this.sourceId=source;
		this.destinationId=destination;
		this.value=value[0];
		this.max= value;
		this.chaineSourceCompl=chaineSourceCompl;
		this.chaineDestinationCompl=chaineDestinationCompl;
	}
	
	/**
	 * Retourne la valeur (score) du lien.
	 * @return valeur.
	 */ 
	
	public int getValue()
	{
		return this.value;
	}
	
	public int[] getMax()
	{
		return this.max;
	}
	/**
	 * Donne id du noeud source.
	 * @return id
	 */
	public int getSourceId()
	{
		return this.sourceId;
	}
	
	/**
	 * Donne id du noeud destination.
	 * @return id
	 */
	public int getDestinationId()
	{
		return this.destinationId;
	}
	
	/**
	 * Indique si le noeud source est une chaine complementaire inverse
	 * @return
	 * true si oui
	 * false sinon
	 */
	public boolean getChaineSourceCompl()
	{
		return this.chaineSourceCompl;
	}
	
	/**
	 * Indique si le noeud destination est une chaine complementaire inverse
	 * @return
	 * true si oui
	 * false sinon
	 */
	
	public boolean getChaineDestinationCompl()
	{
		return this.chaineDestinationCompl;
	}
	
	public String toString(){
		return "Link value: "+this.getValue()+" Source: "+this.sourceId+" Dest: "+this.destinationId+"\n";
	}

}
