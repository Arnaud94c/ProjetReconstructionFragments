import java.util.ArrayList;

import Ressources.Graphe;
import Ressources.Link;
import Ressources.Node;
import Ressources.Set;

public class Greedy 
{
	
	private Graphe graphe;
	private ArrayList<Set> listSet;
	public ArrayList<Link>hamiltonienWay;
	Set tmp, tmp2;
	
	
	
	public Greedy(Graphe graphe)
	{	
		this.graphe=graphe;	
		this.hamiltonienWay=new ArrayList<Link>();
		
	}
	
	public ArrayList<Integer> applyAlgo()
	{
		
		Node source,dest;
		Link link;
		setInit();
		System.out.println(graphe.getLink().size());
		for(Link l: graphe.getLink())
		{	
			
			
			dest=graphe.getNode(l.getDestinationId());
			source=graphe.getNode(l.getSourceId());
			
			//on prend le lien tel quel
			if(!dest.getIn() && !source.getOut()){
			
				if(!l.getChaineSourceCompl() && !l.getChaineDestinationCompl()){ 
					if(!source.getCompl() && !dest.getCompl()){  // n n
						if (!loop(l)){
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isOut();
							graphe.getNode(l.getDestinationId()).isIn();
							union(l);
						}
					}
				}
				else if(!l.getChaineSourceCompl() && l.getChaineDestinationCompl() ){  // n ci 
					if(!source.getCompl() && (dest.getCompl() || (!dest.getOut() && !dest.getIn()))){
						if (!loop(l)){
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isOut();
							graphe.getNode(l.getDestinationId()).isIn();
							graphe.getNode(l.getDestinationId()).isCompl();
							union(l);
						}
					}
				}
				else if(l.getChaineSourceCompl() && !l.getChaineDestinationCompl() ){  // ci n
					if((source.getCompl() || (!source.getOut() && !source.getIn())) && !dest.getCompl() ){
						if (!loop(l)){
							hamiltonienWay.add(l);			
							graphe.getNode(l.getSourceId()).isOut();
							graphe.getNode(l.getDestinationId()).isIn();
							graphe.getNode(l.getSourceId()).isCompl();
							union(l);
						}
					}
				}
			}		
			// on prend le complementaire du lien
			else if(!dest.getOut() && !source.getIn()){
				if(!l.getChaineSourceCompl() && !l.getChaineDestinationCompl() ){
					if((source.getCompl() || (!source.getOut() && !source.getIn())) && (dest.getCompl() || (!dest.getOut() && !dest.getIn()))) // n n -> ci ci : on doit inverser la source et la destination pour avoir le bon max correspondant
					{
						link =new Link(l.getDestinationId(),l.getSourceId(),true,true,l.getMax()); 
						if (!loop(link)){
							hamiltonienWay.add(link);									
							graphe.getNode(l.getSourceId()).isIn();
							graphe.getNode(l.getDestinationId()).isOut();
							graphe.getNode(l.getSourceId()).isCompl();
							graphe.getNode(l.getDestinationId()).isCompl();
							union(link);
						}
					}
				}
				else if(!l.getChaineSourceCompl() && l.getChaineDestinationCompl() ){  // n ci -> ci n 
					if((source.getCompl() || (!source.getOut() && !source.getIn())) && !dest.getCompl() ){
						link =new Link(l.getDestinationId(),l.getSourceId(),true,false,l.getMax()); 
						if (!loop(link)){
							hamiltonienWay.add(link);			
							graphe.getNode(l.getSourceId()).isIn();
							graphe.getNode(l.getDestinationId()).isOut();
							graphe.getNode(l.getDestinationId()).isCompl();
							union(link);
						}
					}
				}
				else if(l.getChaineSourceCompl() && !l.getChaineDestinationCompl() ){  // ci n -> n ci
					if(!source.getCompl() && (dest.getCompl() || (!dest.getOut() && !dest.getIn()))){
						link =new Link(l.getDestinationId(),l.getSourceId(),false,true,l.getMax()); 
						if (!loop(link)){
							hamiltonienWay.add(link);			
							graphe.getNode(l.getSourceId()).isIn();
							graphe.getNode(l.getDestinationId()).isOut();
							graphe.getNode(l.getSourceId()).isCompl();
							union(link);
						}
					}
				}
			}

			if(hamiltonienWay.size()==(graphe.getNode().size()-1)) // si tous les noeuds sont atteints.
			{
				break;
			}
		}
		System.out.println(graphe.getNode().size()-1);
		System.out.println(listSet.get(0).getlistId());
		System.out.println(listSet.size());
		System.out.println(this.hamiltonienWay.size());
		System.out.println(this.hamiltonienWay);
		
		return listSet.get(0).getlistId();
	}
	
	public void setInit(){
		listSet = new ArrayList<Set>();
		tmp = null;
		tmp2 = null;
	}
	

	//verification de boucle
	public boolean loop(Link l)
	{
		for (Set s :listSet){
			if(s.isFirstLast(l)) {
				
				return true;
				 
			}
		}
		return false;
	}
	
	// fusionne les 2 ensembles 
	public void union(Link l)
	{
		for (Set s :listSet){
			if(s.getFirst() == l.getDestinationId()) {tmp = s;}
			else if(s.getLast() == l.getSourceId()){ tmp2 = s;}
		}
		if (tmp!=null && tmp2!=null){
		
			listSet.get(listSet.indexOf(tmp2)).union(tmp);
			listSet.remove(listSet.indexOf(tmp));
			tmp=null;
			tmp2=null;
		}
		else if (tmp==null && tmp2==null){
			
			listSet.add(new Set(l));
		}
		else if (tmp==null){
			
			listSet.get(listSet.indexOf(tmp2)).putLast(l.getDestinationId());
			tmp2=null;
		}
		else {
			
			listSet.get(listSet.indexOf(tmp)).putFirst(l.getSourceId());
			tmp=null;
		}
		
	}
	
	

}
