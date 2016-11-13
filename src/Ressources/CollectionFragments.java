package Ressources;

import java.util.ArrayList;
import java.util.List;

public class CollectionFragments
{
	private List<Fragment> listFragments;
	private int indexComparateur=0;
	private int indexCompare=1;
	
	public CollectionFragments()
	{
		listFragments= new ArrayList<Fragment>();
	}
	
	public void addFragments(Fragment fragment)
	{
		listFragments.add(fragment);
	}
	public Fragment giveFirstFragment()
	{
		return listFragments.get(indexComparateur);
	}
	public Fragment giveSecondFragment()
	{
		return listFragments.get(indexCompare);
	} 
		
	
	public void incrementIndexComparateur()
	{
		this.indexComparateur++;
	}
	public void incrementIndexCompare()
	{
		this.indexCompare++;
	}
	public void reinistialiseIndexCompare()
	{
		this.indexCompare=0;
	}
	public int giveNumberFragments()
	{
		return listFragments.size();
	}
	public void adjustIndexCompare()
	{
		this.indexCompare=this.indexComparateur+1;
	}
	
	public int giveAdjustedIndex()
	{
		return  this.indexCompare;
	}
	

}
