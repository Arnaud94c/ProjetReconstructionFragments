package Ressources;

import java.util.ArrayList;
import java.util.Collections;

public class Heap
{
	// Ensemble des liens 
	private ArrayList<Link> listeLink;
	// Taille (nombre liens)
	private int size;
	// id de la partie gauche du max
	private int left;
	// id de la partie droite du max
	private int right;
	// max du tas
	private int largest;
	
	/**
	 * Constructeur
	 * @param listeLink liste de liens.
	 */
	public Heap(ArrayList<Link> listeLink)
	{
		this.listeLink=listeLink;
	}
	
	/**
	 * Construction de l'arbre.
	 * @param liste liste des liens.
	 */
	
	private void buildheap()
	{
		size=this.listeLink.size()-1;
		
		for(int i=size/2;i>=0;i--)
		{
			maxheap(this.listeLink,i);
		}
	}
	/**
	 * Fonction permettant d'echanger deux liens de place.
	 * @param i id du lien 1
	 * @param j id du lien 2
	 */
	
	private void exchange(int i,int j)
	{
		Link k = this.listeLink.get(i);
		this.listeLink.set(i,this.listeLink.get(j));
		this.listeLink.set(j,k);
	}
	
	/**
	 * Tri du tas par ordre decroissant.
	 * @return ensemble de liens tries.
	 */
	
	public ArrayList<Link> sort ()
	{
		buildheap();
		
		for(int i=size;i>0;i--)
		{
			exchange(0,i);
			size=size-1;
			maxheap(this.listeLink,0);
		}
		
		
		Collections.reverse(this.listeLink);
		return this.listeLink;
	}
	
	/**
	 * Definit le max du tas
	 * @param liste liste de liens
	 * @param max actuel du tas
	 */
	
	private void maxheap(ArrayList<Link>liste,int i)
	{
		left=2*i;
		right=2*i+1;
		
		
		if(left <=size && (liste.get(left).getValue()> liste.get(i).getValue()))
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
