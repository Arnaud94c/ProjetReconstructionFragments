import Ressources.Alignement;


public class Test {
	public static  void main(String[] args) {
		
		String s = "CCAGTTTT";
		String t = "CAGT";
		//String s = "AGCC";
		//String t = "AGCC";
		int[][] matrice = Algo.semiGlobal( s, t, s.length()+1, t.length()+1);
		for (int i=0; i<s.length()+1; i++){
			for (int j=0; j<t.length()+1 ; j++){
				System.out.print(matrice[i][j]+" ");
			}
			System.out.println();
		}
		int row[] = Algo.findMaxRow(matrice[s.length()]);
		int max1 = row[0];
		System.out.println(max1);
		int column[] = Algo.findMaxColumn(matrice);
		int max2= column[0];
		System.out.println(max2);
		Alignement tab1 = Algo.traceWayColumn(s, t, matrice, column[1]);
		System.out.println("utile");
		System.out.println(tab1.s1);
		System.out.println(tab1.s2);
		System.out.println(tab1.s3);
		System.out.println(tab1.t1);
		System.out.println(tab1.t2);
	
	}
}
