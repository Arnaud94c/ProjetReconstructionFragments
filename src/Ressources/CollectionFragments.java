package Ressources;

import java.util.ArrayList;
import java.util.List;

public class CollectionFragments
{
	private List<Fragment> listFragments;
	private int index=0;
	
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
		return listFragments.get(index);
	}
	public Fragment giveSecondFragment()
	{
		return listFragments.get(index+1);
	} 
	public void incrementIndex()
	{
		this.index++;
	}
	public int giveNumberFragments()
	{
		return listFragments.size();
	}
	

}
