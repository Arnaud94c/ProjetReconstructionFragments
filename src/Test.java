
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
		int max1 = Algo.findMaxRow(matrice[s.length()]);
		System.out.println(max1);
		int max2= Algo.findMaxColumn(matrice);
		System.out.println(max2);
	}
}
