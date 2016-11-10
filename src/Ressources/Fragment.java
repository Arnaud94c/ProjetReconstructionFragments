package Ressources;


import java.util.ArrayList;
import java.util.List;

public class Fragment 
{
	private List<String> chaineCaracteres;
	private List<String>chaineComplementaire;
	private String chaineString;
	private int id= 0;
	private int size;
	private String chaineCompl;

	public Fragment()
	{}
	
	public Fragment(int identifiant)
	{
		chaineString="";
		chaineCompl="";
		id=identifiant;
		chaineComplementaire=new ArrayList<String>();
		chaineCaracteres=new ArrayList<String>();
		size=0;
	}
	
	public String getChaine()
	{
		return this.chaineString;
	}
	
	public String getComplementaire()
	{
		return this.chaineCompl;
	}
	public void addChaine(String chaine)
	{
		chaineCaracteres.add(chaine);
		chaineString+=chaine;
		size=chaineCaracteres.size();
	}
	public void calculComplementaire()
	{
		chaineCompl=chaineString;
		for (int i=0; i<size;i++)
		{
			char caractere=chaineString.charAt(i);
			switch(caractere)
			{
			case 'a':
				chaineCompl=replaceChar(chaineCompl,i,'t');
			break;
			case 'c':
				chaineCompl=replaceChar(chaineCompl,i,'g');
				break;
			case 'g':
				chaineCompl=replaceChar(chaineCompl,i,'c');
				break;
			case 't':
				chaineCompl=replaceChar(chaineCompl,i,'a');
			break;
			default:
				break;
			
			}
		
			
		}
	}
	public void actualiseSize()
	{
		this.size=chaineString.length();
	}
	public int getSize()
	{
		return this.size;
	}
	
	public String replaceChar(String s,int position, char c)
	{
		return s.substring(0,position)+ c+ s.substring(position+1);
	}
	
	

}
