package Ressources;

public class Alignement
{
	public boolean confondu;
	public String s1;
	public String s2;
	public String s3;
	public String t1 ;
	public String t2;
	public int i;
	public int j;
	
	/**
	 *Constructeur
	 */
	public Alignement(boolean confondu, String s1, String s2, String s3, String t1, String t2, int i, int j)
	{
		this.confondu =confondu;
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.t1 = t1;
		this.t2 = t2;
		this.i=i;
		this.j=j;
	}
}