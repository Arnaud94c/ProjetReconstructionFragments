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
		for(Link l: graphe.getLink())
		{	
			source=graphe.getNode(l.getDestinationId());
			dest=graphe.getNode(l.getSourceId());
			// pas de compl
			if(l.getChaineSourceCompl()==false && l.getChaineDestinationCompl()==false){
				if(source.getCompl()==false && dest.getCompl()==false){ //verfification qu'ils n'ont pas ete deja choisi en inverse
					if(l.getChaineSourceInverse()==false && l.getChaineDestinationInverse()==false ){ // n n
						if((dest.getIn()==false)&&(source.getOut()==false))
						{
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isOut();
							graphe.getNode(l.getDestinationId()).isIn();
				
							// FUSIONNER LES DEUX ENSEMBLES 
						}
					}
					else if(l.getChaineSourceInverse()==true && l.getChaineDestinationInverse()==true ){ // i i
						if((dest.getOut()==false)&&(source.getIn()==false))
						{
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isIn();
							graphe.getNode(l.getDestinationId()).isOut();
				
							// FUSIONNER LES DEUX ENSEMBLES 
						}
					}
					else if(l.getChaineSourceInverse()==false && l.getChaineDestinationInverse()==true ){ // n i
						if((dest.getOut()==false)&&(source.getOut()==false))
						{
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isOut();
							graphe.getNode(l.getDestinationId()).isOut();
				
							// FUSIONNER LES DEUX ENSEMBLES 
						}
					}
					else if(l.getChaineSourceInverse()==true && l.getChaineDestinationInverse()==false ){ // i n
						if((dest.getIn()==false)&&(source.getIn()==false))
						{
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isIn();
							graphe.getNode(l.getDestinationId()).isIn();
				
							// FUSIONNER LES DEUX ENSEMBLES 
						}
					}
				}
			}
			// source compl
			else if(l.getChaineSourceCompl()==true && l.getChaineDestinationCompl()==false){
				if((source.getCompl()==true || (source.getOut()==false && source.getIn()==false) )&& dest.getCompl()==false){ //verification source n'a pas deja ete choisie en standard
					if(l.getChaineSourceInverse()==false && l.getChaineDestinationInverse()==false ){ // c n
						if((dest.getIn()==false)&&(source.getOut()==false))
						{
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isOut();
							graphe.getNode(l.getDestinationId()).isIn();
							graphe.getNode(l.getSourceId()).isCompl();
				
							// FUSIONNER LES DEUX ENSEMBLES 
						}
					}
					else if(l.getChaineSourceInverse()==true && l.getChaineDestinationInverse()==true ){ // ci i
						if((dest.getOut()==false)&&(source.getIn()==false))
						{
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isIn();
							graphe.getNode(l.getDestinationId()).isOut();
							graphe.getNode(l.getSourceId()).isCompl();
				
							// FUSIONNER LES DEUX ENSEMBLES 
						}
					}
					else if(l.getChaineSourceInverse()==false && l.getChaineDestinationInverse()==true ){ // c i
						if((dest.getOut()==false)&&(source.getOut()==false))
						{
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isOut();
							graphe.getNode(l.getDestinationId()).isOut();
							graphe.getNode(l.getSourceId()).isCompl();
				
							// FUSIONNER LES DEUX ENSEMBLES 
						}
					}
					else if(l.getChaineSourceInverse()==true && l.getChaineDestinationInverse()==false ){ // ci n
						if((dest.getIn()==false)&&(source.getIn()==false))
						{
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isIn();
							graphe.getNode(l.getDestinationId()).isIn();
							graphe.getNode(l.getSourceId()).isCompl();
				
							// FUSIONNER LES DEUX ENSEMBLES 
						}
					}
				}
			}
			// dest compl
			else if(l.getChaineSourceCompl()==false && l.getChaineDestinationCompl()==true){
				if(source.getCompl()==false && (dest.getCompl()==true || (dest.getOut()==false && dest.getIn()==false))){ //verification dest pas deja ete choisie en standard
					if(l.getChaineSourceInverse()==false && l.getChaineDestinationInverse()==false ){ // n c
						if((dest.getIn()==false)&&(source.getOut()==false))
						{
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isOut();
							graphe.getNode(l.getDestinationId()).isIn();
							graphe.getNode(l.getDestinationId()).isCompl();
				
							// FUSIONNER LES DEUX ENSEMBLES 
						}
					}
					else if(l.getChaineSourceInverse()==true && l.getChaineDestinationInverse()==true ){ // i ci
						if((dest.getOut()==false)&&(source.getIn()==false))
						{
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isIn();
							graphe.getNode(l.getDestinationId()).isOut();
							graphe.getNode(l.getDestinationId()).isCompl();
				
							// FUSIONNER LES DEUX ENSEMBLES 
						}
					}
					else if(l.getChaineSourceInverse()==false && l.getChaineDestinationInverse()==true ){ // n ci
						if((dest.getOut()==false)&&(source.getOut()==false))
						{
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isOut();
							graphe.getNode(l.getDestinationId()).isOut();
							graphe.getNode(l.getDestinationId()).isCompl();
				
							// FUSIONNER LES DEUX ENSEMBLES 
						}
					}
					else if(l.getChaineSourceInverse()==true && l.getChaineDestinationInverse()==false ){ // i c
						if((dest.getIn()==false)&&(source.getIn()==false))
						{
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isIn();
							graphe.getNode(l.getDestinationId()).isIn();
							graphe.getNode(l.getDestinationId()).isCompl();
				
							// FUSIONNER LES DEUX ENSEMBLES 
						}
					}
				}
			}
			// 2 compl
			else if(l.getChaineSourceCompl()==true && l.getChaineDestinationCompl()==true){
				if((source.getCompl()==true || (source.getOut()==false && source.getIn()==false) )&& (dest.getCompl()==true || (dest.getOut()==false && dest.getIn()==false))){ //verification pas deja ete choisi en standard
					if(l.getChaineSourceInverse()==false && l.getChaineDestinationInverse()==false ){ // c c
						if((dest.getIn()==false)&&(source.getOut()==false))
						{
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isOut();
							graphe.getNode(l.getDestinationId()).isIn();
							graphe.getNode(l.getSourceId()).isCompl();
							graphe.getNode(l.getDestinationId()).isCompl();
				
							// FUSIONNER LES DEUX ENSEMBLES 
						}
					}
					else if(l.getChaineSourceInverse()==true && l.getChaineDestinationInverse()==true ){ // ci ci
						if((dest.getOut()==false)&&(source.getIn()==false))
						{
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isIn();
							graphe.getNode(l.getDestinationId()).isOut();
							graphe.getNode(l.getSourceId()).isCompl();
							graphe.getNode(l.getDestinationId()).isCompl();
				
							// FUSIONNER LES DEUX ENSEMBLES 
						}
					}
					else if(l.getChaineSourceInverse()==false && l.getChaineDestinationInverse()==true ){ // c ci
						if((dest.getOut()==false)&&(source.getOut()==false))
						{
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isOut();
							graphe.getNode(l.getDestinationId()).isOut();
							graphe.getNode(l.getSourceId()).isCompl();
							graphe.getNode(l.getDestinationId()).isCompl();
				
							// FUSIONNER LES DEUX ENSEMBLES 
						}
					}
					else if(l.getChaineSourceInverse()==true && l.getChaineDestinationInverse()==false ){ // ci c
						if((dest.getIn()==false)&&(source.getIn()==false))
						{
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isIn();
							graphe.getNode(l.getDestinationId()).isIn();
							graphe.getNode(l.getSourceId()).isCompl();
							graphe.getNode(l.getDestinationId()).isCompl();
				
							// FUSIONNER LES DEUX ENSEMBLES 
						}
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
