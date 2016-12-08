
public class Test {
	public static  void main(String[] args) {
		String s = "CAGCACTTGGATTCTCGG";
		String t = "CAGCGTGG";
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
		int tab1[] = Algo.traceWayColumn(s, t, matrice, column[1]);
		int tab2[] = Algo.traceWayRow(s, t, matrice, row[1]);
		System.out.println("confondu, coord. deb, coord. fin");
		System.out.println("column ");
		for (int i=0; i<tab1.length; i++){
			System.out.print(tab1[i]+" ");
		}System.out.println();
		System.out.println("row");
		for (int i=0; i<tab2.length; i++){
			System.out.print(tab2[i]+" ");
		}
		System.out.println();
	}
}
