package Ressources;
import java.util.ArrayList;

public class Set {
	//liste des id appartenant au Set
	private ArrayList<Integer> listId;
	//premier et dernier id du Set
	private int first, last;
	
	public Set(){
		listId = new ArrayList<Integer>();
	}
	//definition d un nouveau Set a partir d un lien
	public Set(Link l){
		listId = new ArrayList<Integer>();
		this.listId.add(l.getSourceId());
		this.listId.add(l.getDestinationId());	
		this.first=l.getSourceId();
		this.last=l.getDestinationId();
	}
	
	public ArrayList<Integer> getlistId(){
		return listId;
	}
	
	// retourne vrai si la source et la destination du lien appartient au Set
	public boolean includes(Link l){
		if(listId.contains(l.getSourceId()) && listId.contains(l.getDestinationId())) return true;
		return false;
	}
	// retourne vrai si la source ou la destination du lien appartient au Set	
	public boolean findSet(Link l){
		if(listId.contains(l.getSourceId()) || listId.contains(l.getDestinationId())) return true;
		return false;
	}
	
	//fusion du Set avec un autre
	public Set union(Set set){
		last=set.getLast();
		listId.addAll(set.getlistId());
		return set;	
	}
	//retourne le dernier id du set
	public int getLast(){
		return last;	
	}
	//retourne le premier id du set
	public int getFirst(){
		return first;	
	}
	//retourne vrai si source et la destination sont les extremites du Set	
	public boolean isFirstLast(Link l){
		return ( (first==l.getSourceId() && last==l.getDestinationId()) || (last==l.getSourceId() && first==l.getDestinationId()) );
	}
	//Place sur le devant du Set un nouvel id
	public void putFirst(int i){
		first=i;
		listId.add(0,i);
	}
	//Place sur le derierre du Set un nouvel id
	public void putLast(int i){
		last=i;
		listId.add(i);
	}
	
	public int getSum(){
		return listId.size();
	}
	
	public void print(){
		for (int i : listId){
			System.out.println(i);
		}
		
	}

}

