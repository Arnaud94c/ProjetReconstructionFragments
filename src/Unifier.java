import Ressources.Node;
import Ressources.Alignement;
import Ressources.AlignementChain;
import Ressources.AlignementNode;
import Ressources.CollectionFragments;

import java.util.ArrayList;

public class Unifier{
	
	public AlignementChain alignement;
	public  ArrayList<Integer> chemin;
	public  ArrayList<Node> nodes;
	public  CollectionFragments collection;
	public AlignementNode tmp;
	public String concensus;
	
	public Unifier(ArrayList<Integer> ch, ArrayList<Node> n, CollectionFragments co){
		System.out.println("new unifier");
		this.chemin=ch;
		this.nodes=n;
		this.collection=co;
		this.concensus="";
		System.out.println("chemin " + chemin.size());
		unify3();
		buildconcensus();
	}
	
	private void buildconcensus() {
		System.out.println("debut concensus");
		AlignementNode n1, n2;
		char c;
		short[] tab= {0,0,0,0,0}; //0=-, 1=c, 2=g, 3=t, 4=a
		n1= this.alignement.getf();
		while(n1!=null){ //on se deplace caractere par caractere pour l'ajouter au consencus
			n2=n1;
			while(n2!=null){ //on descend pour compter les occurences
				switch(n2.value){
					case '-': tab[0]++; break;
					case 'c': tab[1]++; break;
					case 'g': tab[2]++; break;
					case 't': tab[3]++; break;
					case 'a': tab[4]++; break;
				}	
				n2=n2.down;
			}
			c=max(tab);
			if(c!='-'){
				this.concensus+=c; //on ajoute le caractere au concensus
			}
			else{
				System.out.println("gap");
			}
			for(short i=0;i<=4;i++){ //reinitialise les compteurs
				tab[i]=0;
			}
			if(n1.r!=null){ // si il y a encore un caractere a droite on le prend
				n1=n1.r;
			}
			else{ //si plus de caractere a droite sur ce segment
				do{ //on cherche le prochain segment ou il y a un caractere a droite
					n1=n1.down;
					if(n1==null)break;
				}while(n1.r==null );
				if(n1!=null)n1=n1.r;
			}
		}
		return ;
	}
	
	private char max(short[] tab){
		short indice=0, max=0;
		char car;
		for(short i=0; i<=4; i++){
			if(tab[i]>max){
				indice=i;
				max=tab[i];
			}
		}
		switch(indice){
			case 0 :  car='-'; break;
			case 1 :  car='c'; break;
			case 2 :  car='g'; break;
			case 3 :  car='t'; break;
			case 4 :  car='a'; break;
			default: car='-'; System.out.println("default"); break;
		}	
		return car;
	}

	public void unify3(){
		System.out.println("unify");
		String previous="";
		String current="";
		AlignementNode n1, n2, n3, tm;
		int matrice[][];
		int imax[];
		Alignement al ;
		
		//on prend le segment dans le bon sens
		if (nodes.get(chemin.get(0)).getCompl()) previous = collection.getFragment(chemin.get(0)).getComplementaire();
		else previous = collection.getFragment(chemin.get(0)).getChaine();
		System.out.println("chemin " + chemin.size());
		chemin.remove(0);
		System.out.println("chemin " + chemin.size());
		//on prend le segment dans le bon sens
		if (nodes.get(chemin.get(0)).getCompl()) current = collection.getFragment(chemin.get(0)).getComplementaire();
		else current = collection.getFragment(chemin.get(0)).getChaine();
		
		matrice = Algo.semiGlobal( current, previous, current.length()+1, previous.length()+1);
		imax = Algo.findMaxColumn(matrice);
		al=Algo.traceWayColumn(current,previous, matrice, imax[1]);
		
		n1 = new AlignementNode(previous.charAt(0)); //n1 = debut t1
		this.alignement=new AlignementChain(n1);
		if(al.s3==null && al.t1!=""){						//si configuration: t1____ t2____						
															//						   s1____ s2____
			n1 = n1.chainNode(al.t1);//n1=fin t1
			n2= new AlignementNode(al.t2.charAt(0)); //n2=debut t2
			n1.r= n2; //on colle debut t2 a t1
			n2.l=n1;
			n3 = n2.chainNode2(al.s1, al.t2); //on colle t2 et s1 ensemble. n3= fin s1 
			this.tmp= n2.down; //debut s1 A GARDER pour coller avec la suite!! tmp=debut s1
			n2= new AlignementNode(al.s2.charAt(0)); //n2=debut s2
			n3.r = n2; //on colle debut de s2 a s1
			n2.l=n3;
			n2.chainNode(al.s2);// on continue s2
		}
		else if (al.s3!=null ){							//si configuration:      t1_____
														// 					s1__ s2_____ s3____
			n2= new AlignementNode(al.t1.charAt(0)); //n2=debut t1
			n3 = n2.chainNode2(al.s2, al.t1); //on colle t1 et s2 ensemble. n3= fin s2 
			this.tmp= n2.down; //debut s1 A GARDER pour coller avec la suite!! tmp=debut s2
			n2= new AlignementNode(al.s3.charAt(0)); //n2=debut s3
			n3.r = n2; //on colle debut de s2 a s1
			n2.chainNode(al.s3);// on continue s3
			System.out.println("bizarre 1");
		}
		chemin.remove(0);
		
		//fin init, debut boucle
		do{
			previous = current;
			if (nodes.get(chemin.get(0)).getCompl()) current = collection.getFragment(chemin.get(0)).getComplementaire();
			else current = collection.getFragment(chemin.get(0)).getChaine();
			matrice = Algo.semiGlobal( current, previous, current.length()+1, previous.length()+1);
			imax = Algo.findMaxColumn(matrice);
			al=Algo.traceWayColumn(current,previous, matrice, imax[1]);
			
			n1 = new AlignementNode(previous.charAt(0));//debut t1
			this.tmp.down=n1; //on colle t1 en dessous de l alignement precedent
			if(al.s3==null){									//si configuration: t1____ t2____						
																//						   s1____ s2____
				n1 = n1.chainNode(al.t1);//n1=fin t1			
				n2= new AlignementNode(al.t2.charAt(0)); //n2=debut t2
				n1.r= n2; //on colle debut t2 a t1
				n2.l=n1;
				n3 = n2.chainNode2(al.s1, al.t2); //on colle t2 et s1 ensemble. n2= fin s1 
				tm= n2.down; //debut s1 A GARDER pour coller avec la suite!!
				n2= new AlignementNode(al.s2.charAt(0)); //n2=debut s2
				n3.r = n2; //on colle debut de s2 a s1
				n2.l=n3;
				n2.chainNode(al.s2);// on continue s2
				
			}
			else{											//si configuration:      t1_____
															// 					s1__ s2_____ s3____
				n2= new AlignementNode(al.t1.charAt(0)); //n2=debut t1
				n3 = n2.chainNode2(al.s2, al.t1); //on colle t1 et s2 ensemble. n3= fin s2 
				tm= n2.down; //debut s1 A GARDER pour coller avec la suite!! tm=debut s2
				System.out.println("s3 "+ al.s3);
				n2= new AlignementNode(al.s3.charAt(0)); //n2=debut s3
				n3.r = n2; //on colle debut de s2 a s1
				n2.chainNode(al.s3);// on continue s3
				System.out.println("bizarre");
			}
			chemin.remove(0);
			fusion(); 
			this.tmp=tm;
		}while(!chemin.isEmpty());
		
	}
	private void fusion() { //fusion des 2 segment identique mais avec des gaps peut etre differents
		AlignementNode n1, n2, n3, n4, n5;
		n1= this.tmp; 
		n2= this.tmp.down;
		while(n1.r!=null || n2.r!=null){
			if(n1.value==n2.value){ //si identique on colle le down de n2 sur n1 (n2 disparait), on se deplace sur les 2
				n1.down=n2.down;
				if(n1.down!=null){
					n1.down.up=n1;
				}
				n2=n2.r;
				n1=n1.r;
			}
			else{
				if(n1.value=='-'){ // on insert un gap sur n2.down, on avance sur n1
					if (n2.down!=null){
						n3=new AlignementNode('-');
						n1.down=n3;
						n3.up=n1;
						if(n2.down!=null){
							n3.r=n2.down;
							n3.r.l=n3;
							if(n2.down.l!=null){
								n3.l=n2.down.l;
								n3.l.r=n3;
							}
						}
					}
					n1=n1.r; //avance
				}
				else{ //on doit propager les gaps vers le haut
					n3=new AlignementNode('-');
					n3.r=n1;
					n3.l=n1.l;
					if(n3.l!=null){
						n3.l.r=n3;
					}
					n3.r.l=n3;
					n3.down=n2.down;
					n3.down.up=n3;
					n2=n2.r; //avance
					//propagation
					n4= n1.up;
					while(n4!=null){
						n5=new AlignementNode('-');
						n5.down=n3;
						n3.up=n5;
						n5.r=n4;
						n5.r.l=n5;
						if(n4.l!=null){
							n5.l=n4.l;
							n5.l.r=n5;
						}
						n4=n4.up;
					}
				}
			}
		}
	}


	
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
			index = imax[1];
			s += current.substring(index);
			previous = current;
		}
		return s;
	}
	
}