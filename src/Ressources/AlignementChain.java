package Ressources;

public class AlignementChain
{
	public AlignementNode first;
	
	/**
	 *Constructeur
	 */
	public AlignementChain(AlignementNode f)
	{
		this.first=f;
	}
	
	public AlignementNode getf(){
		return first;
	}
	
}