package Ressources;

import java.util.ArrayList;

public class Graphe 
{
	private ArrayList<Node> listNode= new ArrayList<Node>();
	private ArrayList<Link> listLink= new ArrayList<Link>();
	
	public Graphe(ArrayList<Node> listNode,ArrayList<Link> listLink)
	{
		this.listNode=listNode;
		this.listLink=listLink;
	}
	
	
	public void triArcDecroissant()
	{
		int numbersLink=listLink.size();
		for (int i=0;i<numbersLink;i++)
		{
			if(i==numbersLink-1)  // cas dernier element
			{
				int k=1;
				if(compare(listLink.get(i),listLink.get(i-k)))
				{
					//int value= listLink.get(i).getValue();
					boolean find=false;
					while( find==false)
					{
						k++;
						boolean research=compare(listLink.get(i),listLink.get(i-k));
						if(research)
						{
							Link tmp=listLink.get(i);
							listLink.set(i,listLink.get(i-k));
							listLink.set(i-k, tmp);
							find=true;
						}
						
					}
				}
			}else
			{
					boolean test=compare (listLink.get(i),listLink.get(i+1));
					if(test)
					{
						Link tmp=listLink.get(i);
						listLink.set(i, listLink.get(i+1));
						listLink.set(i+1,tmp);
						i=0;
					}
			}
			
			
		
			
			
		}
		
		
	}

	public ArrayList<Link> getLink()
	{
		return this.listLink;
	}
	
	public ArrayList<Node> getNode()
	{
		return this.listNode;
	}
	
	public Link getLink(int index)
	{
		return this.listLink.get(index);
	}
	
	public Node getNode(int index)
	{
		return this.listNode.get(index);
	}
	
	
	
	
	private boolean compare(Link first,Link second)
	{
		int valueFirst=first.getValue();
		int valueSecond=second.getValue();
		
		 if(valueFirst==valueSecond)
		 {
			 return false;
		 }else
		 {
			 if(valueFirst<valueSecond)
			 {
				 return true;
			 }else
			 {
				 return false;
			 }
			 
		 }
		
		
	}
	

}
