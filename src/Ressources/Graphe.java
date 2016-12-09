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
	


	public void triHeap()
	{
		Heap heap = new Heap(listLink);
		
		for(Link k:  listLink)
		{
			System.out.print(k.getValue()+" ");
		}
		this.listLink=heap.sort();
		System.out.println("");
		System.out.println("valeurs triées");
		for (Link l: listLink)
		{
			System.out.print(l.getValue()+" ");
		}
		System.out.println("");
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
	
	
	

	

}
