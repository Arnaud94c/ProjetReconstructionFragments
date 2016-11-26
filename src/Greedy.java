import java.util.ArrayList;

import Ressources.Graphe;
import Ressources.Link;


public class Greedy 
{
	
	private Graphe graphe;
	
	public Greedy(Graphe graphe)
	{	
		this.graphe=graphe;	
	}
	
	public void applyAlgo()
	{
		ArrayList<Link>hamiltonienWay=new ArrayList<Link>();
		for(Link l: graphe.getLink())
		{
			if((graphe.getNode(l.getDestinationId()).getIn()==false)&&(graphe.getNode(l.getSourceId()).getOut()==false))
			{
				hamiltonienWay.add(l);			
				graphe.getNode(l.getSourceId()).isOut();
				graphe.getNode(l.getDestinationId()).isIn();
				
				// FUSIONNER LES DEUX ENSEMBLES 
			}
			
			if(hamiltonienWay.size()==(graphe.getNode().size()-2)) // si tous les noeuds sont atteints.
			{
				break;
			}
		}
		
		
	}
	
	
	// initialise l'ensemble {i}
	public void makeSet()
	{
		
	}
	
	// retourne l'ensemble contenant f
	public void findSet()
	{
		
	}
	
	// fusionne les 2 ensembles E1 et E2
	public void union()
	{
		
	}
	
	// choisit arc(f,g)
	
	public void select()
	{
		
	}
	

}
