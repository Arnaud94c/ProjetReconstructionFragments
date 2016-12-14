package Ressources;

public class Node
{
	// id du noeud.
	private int id=0;
	// indique si on est deja entre dans ce noeud.
	private boolean in;
	//indique si on est deja sortit de ce noeud.
	private boolean out;
	// indique si la chaine du noeud est complementaire inverse.
	private boolean compl;
	
	
	// Constructeur
	public Node(int id)
	{
		this.id=id;
		this.in=false;
		this.out=false;
		this.compl=false;
	}
	
	// Constructeur par defaut
	public Node ()
	{
		this.in=false;
		this.out=false;
	}
	
	// Indique si on est deja entre dans le noeud.
	public boolean getIn()
	{
		return this.in;
	}
	// Indique si on est deja sortit du noeud.
	public boolean  getOut()
	{
		return this.out;
	}
	
	// Donne le complementaire inverse de la chaine.
	public boolean getCompl()
	{
		return this.compl;
	}
	
	// Definit que l'on entre dans le noeud.
	public void isIn()
	{
		this.in=true;
	}
	
	// Definit que l'on sort du noeud.
	public void isOut()
	{
		this.out=true;
	}

	// Definit que la chaine est complementaire inverse.
	public void isCompl()
	{
		this.compl=true;
	}

	

}
