import Ressources.Node;
import Ressources.CollectionFragments;
import java.util.ArrayList;

public class Unifier{
	
	public static String unify(ArrayList<Integer> chemin, ArrayList<Node> nodes, CollectionFragments collection){
		String s = "";
		String previous="";
		String current;
		int index;
		int imax[];
		int matrice[][];
		
		if (nodes.get(chemin.get(0)).getCompl()) s += collection.getFragment(chemin.get(0)).getComplementaire();
		else s += collection.getFragment(chemin.get(0)).getChaine();
		
		previous=s;
		chemin.remove(0);
		
		for( int i : chemin){
			if (nodes.get(i).getCompl()) current = collection.getFragment(i).getComplementaire();
			else current = collection.getFragment(i).getChaine();
			matrice = Algo.semiGlobal(current, previous, current.length(), previous.length());
			imax = Algo.findMaxColumn(matrice);
			//int[] traceWayRow(String s, String t, int[][] matrice, int indice){
			index = imax[1];
			s += current.substring(index);
			previous = current;
		}
		//System.out.println(s);
		return s;
	}
}