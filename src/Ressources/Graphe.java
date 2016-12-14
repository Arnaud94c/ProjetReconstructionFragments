package Ressources;

import java.util.ArrayList;

public class Graphe 
{
	// Ensemble des noeuds du graphe.
	private ArrayList<Node> listNode= new ArrayList<Node>();
	// Ensemble des liens du graphe.
	private ArrayList<Link> listLink= new ArrayList<Link>();
	
	
	/**
	 *Constructeur
	 * @param listNode ensemble des noeuds.
	 * @param listLink ensemble des liens.
	 */
	public Graphe(ArrayList<Node> listNode,ArrayList<Link> listLink)
	{
		this.listNode=listNode;
		this.listLink=listLink;
	}
	

  /**
   * Effectue un tri par tas sur les liens du graphe.
   */
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
	
	
	/**
	 * Retourne ensemble des liens.
	 * @return liste de liens.
	 */
	
	public ArrayList<Link> getLink()
	{
		return this.listLink;
	}
	
	/**
	 * Retourne ensemble des noeuds.
	 * @return liste de noeuds.
	 */
	
	public ArrayList<Node> getNode()
	{
		return this.listNode;
	}
	
	/**
	 * Donne le lien correspondant a index donne
	 * @param index numero de index.
	 * @return  Le lien correspondant a index.
	 */
	
	public Link getLink(int index)
	{
		return this.listLink.get(index);
	}
	
	/**
	 * Donne le noeud correspondant a  index 
	 * @param index numero de index
	 * @return Le noeud correspondant a index.
	 */
	
	public Node getNode(int index)
	{
		return this.listNode.get(index);
	}
	
	
	

	

}
