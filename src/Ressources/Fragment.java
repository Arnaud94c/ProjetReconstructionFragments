package Ressources;




/**
 * Fragment
 *
 */

public class Fragment 
{
	// chaine
	private String chaineString;
	// identifiant
	private int id= 0;
	//taille de la chaine
	private int size;
	//chaine complemente
	private String chaineCompl;

	/**
	 * Constructeur par defaut.
	 */
	public Fragment()
	{}
	
	/**
	 * Constructeur
	 * @param identifiant
	 */
	public Fragment(int identifiant)
	{
		chaineString="";
		chaineCompl="";
		id=identifiant;
		size=0;
	}
	
	/**
	 * Retourne la chaine.
	 * @return chaine
	 */
	public String getChaine()
	{
		return this.chaineString;
	}
	
	/**
	 * Retourne la chaine inverse complemente
	 * @return
	 */
	
	public String getInverseComplementaire()
	{
		StringBuilder buff=new StringBuilder(this.chaineCompl);
		String inverse= buff.reverse().toString();
		return inverse;
	}

	/**
	 * Retourne la chaine complemente
	 * @return
	 */
	
	public String getComplementaire()
	{
		return this.chaineCompl;
	}
	
	/**
	 * Retourne la chaine inverse
	 * @return
	 */
	
	public String getInverse()
	{
		StringBuilder buff=new StringBuilder(this.chaineString);
		String inverse= buff.reverse().toString();
		return inverse;
	}
	/**
	 * Ajoute un caractere a la chaine.
	 * @param chaine caractere
	 */
	public void addChaine(String chaine)
	{
		
		chaineString+=chaine;
		
	}
	/**
	 * Calcul du complementaire
	 */
	
	public void calculComplementaire()
	{
		chaineCompl=chaineString;
		this.actualiseSize();
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
	
	/**
	 * Actualise la taille de la chaine.
	 */
	
	public void actualiseSize()
	{
		this.size=chaineString.length();
	}
	
	/**
	 * Retourne la taille de la chaine.
	 * @return
	 */
	
	public int getSize()
	{
		return this.size;
	}
	
	/**
	 * Remplace un caratere dans la chaine.
	 * @param s chaine
	 * @param position  postion du caractere a remplacer
	 * @param c  caractere remplacant
	 * @return
	 */
	
	public String replaceChar(String s,int position, char c)
	{
		return s.substring(0,position)+ c+ s.substring(position+1);
	}
	
	public int getId()
	{
		return this.id;
	}
	

}
