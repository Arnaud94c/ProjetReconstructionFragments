package Ressources;

import java.util.ArrayList;
import java.util.Collections;

public class Heap
{
	private ArrayList<Link> listeLink;
	private int size;
	private int left;
	private int right;
	private int largest;
	
	
	public Heap(ArrayList<Link> listeLink)
	{
		this.listeLink=listeLink;
	}
	
	private void buildheap(ArrayList<Link>liste)
	{
		size=listeLink.size()-1;
		
		for(int i=size/2;i>=0;i--)
		{
			maxheap(listeLink,i);
		}
	}
	
	private void exchange(int i,int j)
	{
		Link k = this.listeLink.get(i);
		this.listeLink.set(i,listeLink.get(j));
		listeLink.set(j,k);
	}
	
	
	
	public ArrayList<Link> sort ()
	{
		buildheap(this.listeLink);
		
		for(int i=size;i>0;i--)
		{
			exchange(0,i);
			size=size-1;
			maxheap(listeLink,0);
		}
		
		
		Collections.reverse(this.listeLink);
		return this.listeLink;
	}
	
	private void maxheap(ArrayList<Link>liste,int i)
	{
		left=2*i;
		right=2*i+1;
		
		if(left <=size && liste.get(left).getValue()> liste.get(i).getValue())
		{
			largest=left;
		}else
		{
			largest=i;
		}
		if(right <= size && liste.get(right).getValue()>liste.get(largest).getValue())
		{
			largest=right;
		}
		if(largest !=i)
		{
			exchange(i,largest);
			maxheap(liste,largest);
		}
	}
	
	
	
	

}
