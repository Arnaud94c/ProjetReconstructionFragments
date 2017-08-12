package Ressources;

public class AlignementNode
{
	public char value;
	public AlignementNode up;
	public AlignementNode down;
	public AlignementNode r;
	public AlignementNode l;
	
	/**
	 *Constructeur
	 */
	public AlignementNode(char v)
	{
		this.value=v;
		this.up=null;
		this.down=null;
		this.r=null;
		this.l=null;
	}
	//creation d une chaine de AlignementNode a partir d un string
	public AlignementNode chainNode(String s)
	{
		AlignementNode current, previous;
		previous = this;
		current= this;
		for(int i=1; i<s.length();i++){
			current= new AlignementNode(s.charAt(i));
			current.l=previous;
			previous.r=current;
			previous=current;
		}
		return current;
	}
	//creation d une chaine double de AlignementNode a partir de 2 string (s et t de meme longueur)
	//t au dessus, s en dessous
	//retourne dernier node de s
	public AlignementNode chainNode2(String s, String t)
	{
		AlignementNode ns1, ns2, nt1, nt2;
		ns1 = new AlignementNode(s.charAt(0));
		nt1= this;
		ns1.up=nt1;
		nt1.down=ns1;
		
		for(int i=1; i<s.length();i++){
			ns2= new AlignementNode(s.charAt(i));
			nt2= new AlignementNode(t.charAt(i));
			ns2.l=ns1;
			ns1.r=ns2;
			nt1.r=nt2;
			nt2.l=nt1;
			ns2.up=nt2;
			nt2.down=ns2;
			ns1=ns2;
			nt1=nt2;
		}
		return ns1;
	}
	
	public void addr(AlignementNode n){
		this.r = n;
	}
	public void addl(AlignementNode n){
		this.l = n;
	}
	public void addd(AlignementNode n){
		this.down = n;
	}
	public void addu(AlignementNode n){
		this.up = n;
	}
}