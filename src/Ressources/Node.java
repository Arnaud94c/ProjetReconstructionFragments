package Ressources;

public class Node
{
	
	private int id=0;
	private boolean in;
	private boolean out;
	private boolean compl;
	
	public Node(int id)
	{
		this.id=id;
		this.in=false;
		this.out=false;
		this.compl=false;
	}
	
	public Node ()
	{
		this.in=false;
		this.out=false;
	}
	
	public boolean getIn()
	{
		return this.in;
	}
	public boolean  getOut()
	{
		return this.out;
	}
	public boolean getCompl()
	{
		return this.compl;
	}
	public void isIn()
	{
		this.in=true;
	}
	public void isOut()
	{
		this.out=true;
	}

	public void isCompl()
	{
		this.compl=true;
	}
//	public String getChaine()
//	{
//		return this.chaine;
//	}
	

}
