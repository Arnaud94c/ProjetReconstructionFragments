import java.util.ArrayList;

import Ressources.Graphe;
import Ressources.Link;
import Ressources.Node;


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
		Node source,dest;
		Link link;
		for(Link l: graphe.getLink())
		{	
			// rappel : pour un lien source n-> destination n, alors le noeud de la source est Out et celui de la destination est In. 
			// Si un noeud est pris inverse alors on inverse In Out. Dans le cas d'un complémentaire ca ne change pas. (on regarde ici quel bout du fragment a deja ete choisi)
			source=graphe.getNode(l.getDestinationId());
			dest=graphe.getNode(l.getSourceId());
			//case 1 : n n
			if(l.getChaineSourceCompl()==false && l.getChaineDestinationCompl()==false ){ 
				if((dest.getIn()==false)&&(source.getOut()==false)){
					if(source.getCompl()==false && dest.getCompl()==false){  // n n 
						hamiltonienWay.add(l);			
						graphe.getNode(l.getSourceId()).isOut();
						graphe.getNode(l.getDestinationId()).isIn();
				
						// FUSIONNER LES DEUX ENSEMBLES 
					}
					else if((source.getCompl()==true || (source.getOut()==false && source.getIn()==false) )&& (dest.getCompl()==true || (dest.getOut()==false && dest.getIn()==false))) // ci ci : on doit inverser la source et la destination pour avoir le bon max correspondant
					{
						link =new Link(l.getDestinationId(),l.getSourceId(),true,true,l.getValue()); 
						hamiltonienWay.add(link);
						//hamiltonienWay.add(l);			
						graphe.getNode(l.getSourceId()).isOut();
						graphe.getNode(l.getDestinationId()).isIn();
						graphe.getNode(l.getSourceId()).isCompl();
						graphe.getNode(l.getDestinationId()).isCompl();
				
						// FUSIONNER LES DEUX ENSEMBLES 
					}
				}
			}
			//case 2 max1 : n ci
			else if(l.getChaineSourceCompl()==false && l.getChaineDestinationCompl()==true ){ 
				if((dest.getOut()==false)&&(source.getOut()==false)){
					if(source.getCompl()==false && (dest.getCompl()==true || (dest.getOut()==false && dest.getIn()==false))){ // n ci 
						hamiltonienWay.add(l);			
						graphe.getNode(l.getSourceId()).isOut();
						graphe.getNode(l.getDestinationId()).isOut();
						graphe.getNode(l.getDestinationId()).isCompl();
				
						// FUSIONNER LES DEUX ENSEMBLES 
					}
					else if( (source.getCompl()==true || (source.getOut()==false && source.getIn()==false) ) && dest.getCompl()==false){  // ci n : attention on doit echanger source et destination pour avoir le bon lien correspondant a ce cout de lien
						link =new Link(l.getDestinationId(), l.getSourceId(),false,true,l.getValue());
						hamiltonienWay.add(link);
						//hamiltonienWay.add(l);		
						graphe.getNode(l.getSourceId()).isOut();
						graphe.getNode(l.getDestinationId()).isOut();
						graphe.getNode(l.getSourceId()).isCompl();
					}
				}
			}
			//case 2 max2 : ci n
			else if(l.getChaineSourceCompl()==true && l.getChaineDestinationCompl()==false ){ 
				if ((dest.getIn()==false)&&(source.getIn()==false)) {
					if ( (source.getCompl()==true || (source.getOut()==false && source.getIn()==false) ) && dest.getCompl()==false){ // ci n
						hamiltonienWay.add(l);			
						graphe.getNode(l.getSourceId()).isIn();
						graphe.getNode(l.getDestinationId()).isIn();
						graphe.getNode(l.getSourceId()).isCompl();
					}
					else if (source.getCompl()==false && (dest.getCompl()==true || (dest.getOut()==false && dest.getIn()==false))){  // n ci : echanger source et destination
						link =new Link(l.getDestinationId(), l.getSourceId(),true,false,l.getValue());
						hamiltonienWay.add(link);
						//hamiltonienWay.add(l);			
						graphe.getNode(l.getSourceId()).isIn();
						graphe.getNode(l.getDestinationId()).isIn();
						graphe.getNode(l.getSourceId()).isCompl();
					}
				}
			}
			

			if(hamiltonienWay.size()==(graphe.getNode().size()-1)) // si tous les noeuds sont atteints.
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
