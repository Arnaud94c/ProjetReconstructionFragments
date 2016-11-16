package Ressources;

import java.util.ArrayList;
import java.util.List;

/**
 * Collection de fragments
 *
 */

public class CollectionFragments
{
	
	
	private List<Fragment> listFragments;
	private int indexComparateur=0;
	private int indexCompare=1;
	
	
	/**
	 * Constructeur
	 */
	public CollectionFragments()
	{
		listFragments= new ArrayList<Fragment>();
	}
	
	/**
	 * Ajout d'un fragment.
	 * @param fragment fragment à ajouter.
	 */
	
	public void addFragments(Fragment fragment)
	{
		listFragments.add(fragment);
	}
	
	/**
	 * Retourne le fragment correspondant a l'index du fragment comparateur..
	 * @return fragment
	 */
	
	public Fragment giveFirstFragment()
	{
		return listFragments.get(indexComparateur);
	}
	
	/**
	 * Retourne le fragment correspondant à l'index du fragment compare.
	 * @return
	 */
	
	public Fragment giveSecondFragment()
	{
		return listFragments.get(indexCompare);
	} 
		
	/**
	 * Incremente l'index du comparateur
	 */
	
	public void incrementIndexComparateur()
	{
		this.indexComparateur++;
	}
	/**
	 * Incremente l'index du fragment a comparer
	 */
	
	public void incrementIndexCompare()
	{
		this.indexCompare++;
	}
	
	/**
	 * Reinitialise l'index du framgent a comparer
	 */
	
	public void reinistialiseIndexCompare()
	{
		this.indexCompare=0;
	}
	/**
	 * Donne le nombre de fragment
	 * @return nombre de fragments.
	 */
	
	public int giveNumberFragments()
	{
		return listFragments.size();
	}
	
	/**
	 * Ajuste l'index du fragment a comparer
	 */
	
	public void adjustIndexCompare()
	{
		this.indexCompare=this.indexComparateur+1;
	}
	
	/**
	 * Donne l'index du fragment a comparer.
	 * @return
	 */
	
	public int giveAdjustedIndex()
	{
		return  this.indexCompare;
	}
	

}
