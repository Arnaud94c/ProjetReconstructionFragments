import java.util.ArrayList;

import Ressources.Graphe;
import Ressources.Link;
import Ressources.Node;
import Ressources.Set;

public class Greedy 
{
	
	private Graphe graphe;
	private ArrayList<Set> listSet;
	private ArrayList<Link> chainLink;
	public ArrayList<Link>hamiltonienWay;
	Set tmp, tmp2;
	
	
	
	public Greedy(Graphe graphe)
	{	
		this.graphe=graphe;	
		this.hamiltonienWay=new ArrayList<Link>();
		
	}
	
	public void applyAlgo()
	{
		
		Node source,dest;
		Link link;
		setInit();
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
				if(l.getChaineSourceCompl() && l.getChaineDestinationCompl() ){
					if((source.getCompl() || (!source.getOut() && !source.getIn())) && (dest.getCompl() || (!dest.getOut() && !dest.getIn()))) // ci ci : on doit inverser la source et la destination pour avoir le bon max correspondant
					{
						link =new Link(l.getDestinationId(),l.getSourceId(),true,true,l.getValue()); 
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
					if(!source.getCompl() && (dest.getCompl() || (!dest.getOut() && !dest.getIn()))){
						link =new Link(l.getDestinationId(),l.getSourceId(),true,false,l.getValue()); 
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
					if((source.getCompl() || (!source.getOut() && !source.getIn())) && !dest.getCompl() ){
						link =new Link(l.getDestinationId(),l.getSourceId(),false,true,l.getValue()); 
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
				System.out.println("Set");
				for (Set s :listSet){
					for (int i : s.getlistId()){
						System.out.print(i+" ");
					}
					System.out.println();
				}
				System.out.println("link");
				for (Link lien :hamiltonienWay){
					System.out.println(lien);
				}
				break;
				
			}
		}	
	}
	
	public void setInit(){
		listSet = new ArrayList<Set>();
		chainLink = new ArrayList<Link>();
		chainLink.add(graphe.getLink(0));
		tmp = null;
		tmp2 = null;
	}
	

	
	public boolean loop(Link l)
	{
		for (Set s :listSet){
			if(s.isFirstLast(l)) {
				System.out.println("loop");
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
			System.out.println("fusion");
			listSet.get(listSet.indexOf(tmp2)).union(tmp);
			listSet.remove(listSet.indexOf(tmp));
			tmp=null;
			tmp2=null;
		}
		else if (tmp==null && tmp2==null){
			System.out.println("creation");
			listSet.add(new Set(l));
		}
		else if (tmp==null){
			System.out.println("mis derriere");
			listSet.get(listSet.indexOf(tmp2)).putLast(l.getDestinationId());
			tmp2=null;
		}
		else {
			System.out.println("mis devant");
			listSet.get(listSet.indexOf(tmp)).putFirst(l.getSourceId());
			tmp=null;
		}
		
	}
	
	

}
