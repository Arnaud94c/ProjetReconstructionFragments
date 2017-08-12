import java.lang.Math;

import Ressources.Alignement;


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
			if (ligne[i]>=max){
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
			if (matrice[i][length-1]>=max){
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
	public static Alignement traceWayColumn(String s, String t, int[][] matrice, int indice){
		String s1 ="";
		String s2 ="";
		String s3 =null;
		String t1 ="";
		String t2 ="";
		int g = 2;
		int i = indice;
		int j = t.length();
		boolean confondu=false;
		
		s1 += s.charAt(i-1);
		t2 += t.charAt(j-1);
		while(i>1 && j>1){
			if (match(s.charAt(i-1),t.charAt(j-1))==1){
				if( matrice[i][j] - 1 == matrice[i-1][j-1] ){
					j--;
					i--;
					s1 = s.charAt(i-1) + s1;
					t2 = t.charAt(j-1) + t2 ;
				}
			}
			else {
				if( matrice[i][j] + 1 == matrice[i-1][j-1] ){ //mismatch
					j--;
					i--;
					s1 = s.charAt(i-1) + s1;
					t2 = t.charAt(j-1) + t2 ;
				}
				else if(matrice[i][j] + g == matrice[i][j-1]){ //gap sur s
					j--;
					//s1 = "-" + s1;
					s1=s1.charAt(0)+"-"+s1.substring(1);
					t2 = t.charAt(j-1) + t2;
				}
				else if (matrice[i][j] + g == matrice[i-1][j]){ //gap sur t
					i--;
					s1 = s.charAt(i-1) + s1;
					//t2 = "-" + t2 ;
					t2=t2.charAt(0)+"-"+t2.substring(1);
				}
			}
		}
		if (j ==1) { // t confondu a s
			confondu=true;
			System.out.println("Confondu");
			if (indice<s.length()) s3=s.substring(indice);
			s2=s1;
			t1=t2;
			t2=null;
			if(i>1) s1=s.substring(0, i-1);
			else s1=null;
		}
		else {
			t1=t.substring(0, j-1);
			s2=s.substring(indice);
		}
		
		Alignement a = new Alignement(confondu, s1, s2, s3, t1, t2, i, j);
		return a;
	}
	
}
