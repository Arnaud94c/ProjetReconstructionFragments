package Ressources;
import java.util.ArrayList;

public class Set {
	
	private ArrayList<Integer> listId;
	private int first, last;
	
	public Set(){
		listId = new ArrayList<Integer>();
	}
	
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
	
	public boolean includes(Link l){
		if(listId.contains(l.getSourceId()) && listId.contains(l.getDestinationId())) return true;
		return false;
	}
		
	public boolean findSet(Link l){
		if(listId.contains(l.getSourceId()) || listId.contains(l.getDestinationId())) return true;
		return false;
	}
	
	
	public Set union(Set set){
		last=set.getLast();
		listId.addAll(set.getlistId());
		return set;	
	}
	
	public int getLast(){
		return last;	
	}
	
	public int getFirst(){
		return first;	
	}
		
	public boolean isFirstLast(Link l){
		return ( (first==l.getSourceId() && last==l.getDestinationId()) || (last==l.getSourceId() && first==l.getDestinationId()) );
	}
	
	public void putFirst(int i){
		first=i;
		listId.add(0,i);
	}
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

