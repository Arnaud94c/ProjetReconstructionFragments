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
		int matrice[][]= new int[m][n]; // s vertical, t horizontal
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
	 * @return tab, couple de max et son indice
	 */
	public static int[] findMaxRow(int[] ligne){ // debut de t s'alligne avec fin de s
		int max=0;
		int indice = 0;
		for (int i =1; i<ligne.length; i++){
			if (ligne[i]>max){
				max=ligne[i];
				indice=i;
			}
		}
		int tab[]= {max, indice};
		return tab;
	}
	/**
	 * recherche du maximum sur la derniere colone de la matrice
	 * @param matrice
	 * @return tab, couple de max et son indice
	 */
	public static int[] findMaxColumn(int[][] matrice){ // debut de s s'alligne avec fin de t
		int max=0;
		int indice = 0;
		int length=matrice[0].length;
		for (int i =1; i<matrice.length; i++){
			if (matrice[i][length-1]>max){
				max=matrice[i][length-1];
				indice=i;
			}
		}
		int tab[]= {max, indice};
		return tab;
	}
	/**
	 * recherche du chemin a partir du maximum sur la derniere colone de la matrice
	 * @param s, t, matrice, indice (si indice = 0, ne sert a rien d'effectuer cet algo car les fragments ne s'alignent pas du tout)
	 * @return tab, indice 0 : si t confondu a s, indices 1 et 2: coordonnees de debut d'alignement, indices 3 et 4: coordonnees de fin d'alignement
	 */
	public static int[] traceWayColumn(String s, String t, int[][] matrice, int indice){
		int g = 2;
		int i = indice;
		int j = matrice[0].length-1;
		int confondu=0;
		while(i>1 && j>1){
			if (match(s.charAt(i-1),t.charAt(j-1))==1){
				if( matrice[i][j] - 1 == matrice[i-1][j-1] ){
					j--;
					i--;
				}
			}
			else {
				if(matrice[i][j] + g == matrice[i][j-1]){
					j--;
				}
				else if (matrice[i][j] + g == matrice[i-1][j]){
					i--;
				}
				else if( matrice[i][j] + 1 == matrice[i-1][j-1] ){
					j--;
					i--;
				}
			}
		}
		if (j ==1) { // t confondu a s
			confondu=1;
		}
		int tab[]= {confondu, i, j, indice, matrice[0].length-1};
		return tab;
	}
	/**
	 * recherche du chemin a partir du maximum sur la derniere ligne de la matrice
	 * @param s, t, matrice, indice (si indice = 0, ne sert a rien d'effectuer cet algo)
	 * @return tab, indice 0 : si s confondu a t, indices 1 et 2: coordonnees de debut d'alignement, indices 3 et 4: coordonnees de fin d'alignement
	 */
	public static int[] traceWayRow(String s, String t, int[][] matrice, int indice){
		int g = 2;
		int i = matrice.length-1;
		int j = indice;
		int confondu=0;
		while(i>1 && j>1){
			if (match(s.charAt(i-1),t.charAt(j-1))==1){
				if( matrice[i][j] - 1 == matrice[i-1][j-1] ){
					j--;
					i--;
				}
			}
			else {
				if(matrice[i][j] + g == matrice[i][j-1]){
					j--;
				}
				else if (matrice[i][j] + g == matrice[i-1][j]){
					i--;
				}
				else if( matrice[i][j] + 1 == matrice[i-1][j-1] ){
					j--;
					i--;
				}
			}
		}
		if (i ==1) { // s confondu a t
			confondu=1;
		}
		int tab[]= {confondu, i, j, matrice.length-1, indice };
		return tab;
	}
}
