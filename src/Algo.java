import java.lang.Math;

public class Algo {
	
	/**
	 * algorithme semi global
	 * @param s segment
	 * @param t segment
	 * @param m taille segment s
	 * @param n taille segment t
	 * @return matrice de l alignement
	 */
	public static int[][] semiGlobal(String s, String t, int m, int n){
		int matrice[][]= new int[m][n];
		int g=2; //penalite de gap
		for (int i=0; i<m; i++){ //initialisation a 0
			matrice[i][0]=0;
		}
		for (int j=0; j<n; j++){ //initialisation a 0
			matrice[0][j]=0;
		}
		for (int i=1; i<m; i++){
			for (int j=1; j<n ; j++){
				matrice[i][j]= Math.max(Math.max(matrice[i][j-1]-g, matrice[i-1][j]-g), matrice[i-1][j-1]+match(s.charAt(i-1),t.charAt(j-1)));
			}
		}
		return matrice;
	}
	
	/**
	 * algo de match
	 * @param si symbole a comparer
	 * @param si symbole a comparer
	 * @return 1 si match, -1 si mismatch
	 */
	public static int match(char si,char ti){
		if (si==ti) return 1;
		else return -1;
	}
	
	/**
	 * recherche du maximum sur la ligne
	 * @param ligne la ligne
	 * @return max
	 */
	public static int findMaxRow(int[] ligne){
		int max=0;
		for (int i =1; i<ligne.length; i++){
			if (ligne[i]>max){
				max=ligne[i];
			}
		}
		return max;
	}
	/**
	 * recherche du maximum sur la derniere colone de la matrice
	 * @param matrice
	 * @return max
	 */
	public static int findMaxColumn(int[][] matrice){
		int max=0;
		int length=matrice[0].length;
		for (int i =1; i<matrice.length; i++){
			if (matrice[i][length-1]>max){
				max=matrice[i][length-1];
			}
		}
		return max;
	}
	
}
