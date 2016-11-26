import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Ressources.CollectionFragments;
import Ressources.Fragment;
import Ressources.Node;
import Ressources.Link;
import Ressources.Graphe;


public class Main {
	
	    public static ArrayList<Node> listNode= new ArrayList<Node>();
		public static ArrayList<Link> listLink= new ArrayList<Link>();

	public static  void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		
		CollectionFragments collection = new CollectionFragments();
		System.out.println("Bonjour");
		collection=readFile();
		System.out.println("Collection de fragments encodées");
		
		
		
		
		for(int j=0;j<collection.giveNumberFragments();j++)
		{
			Node node = new Node(j);
			listNode.add(node);
			while(collection.giveAdjustedIndex()<(collection.giveNumberFragments())-1)
			{
				System.getProperty("line.separator");
				System.out.printf("comparateur n°: ");
				System.out.println(j);
				System.getProperty("line.separator");
				System.out.printf("compare n°: ");
				System.out.println(collection.giveAdjustedIndex());
				System.getProperty("line.separator");
				
				applyAlgoSemiGlobal(collection,1);
				applyAlgoSemiGlobal(collection,2);
				applyAlgoSemiGlobal(collection,3);
				
//				long test1=System.currentTimeMillis();
//				applyAlgoSemiGlobal(collection,1);
//				long test2= System.currentTimeMillis()-test1;
//				long test3=System.currentTimeMillis();
//				applyAlgoSemiGlobal(collection,2);
//				long test4= System.currentTimeMillis()-test3;
//				long test5=System.currentTimeMillis();
//				applyAlgoSemiGlobal(collection,3);
//				long test6= System.currentTimeMillis()-test5;
				
				
				
//				ThreadGroup tg= new ThreadGroup("monGroupe");
//				
//				monThread t1= new monThread(collection,1);
//				monThread t2= new monThread(collection,2);
//				monThread t3= new monThread(collection,3);
//				
//				t1.start();
//				t2.start();
//				t3.start();
//				
//				try
//				{
//					Thread.currentThread().sleep(5);
//				}catch(InterruptedException e)
//				{
//					e.printStackTrace();
//				}
//				tg.interrupt();
				
				collection.incrementIndexCompare();
				
			
				
			}
			
			collection.incrementIndexComparateur();
			collection.adjustIndexCompare();
			
			
		}
		
		Graphe graphe = new Graphe(listNode,listLink);
		System.out.print("Tri décroissant en cours ...");
		graphe.triArcDecroissant();
		Greedy greedy = new Greedy(graphe);
		greedy.applyAlgo();
		
		
	}
	
	/**
	 * Lecture du fichier
	 * @return Collection de fragments
	 * @throws IOException
	 */
	
	public static  CollectionFragments readFile () throws IOException
	{
		String ligne="";
		BufferedReader ficTexte;
		int id=0;
		Fragment fragment=new Fragment();
		boolean first=false;
		CollectionFragments collection= new CollectionFragments();
		
		try
		{	
		//	ficTexte= new BufferedReader(new FileReader(new File(".\\src\\collection2.fasta")));
				
			ficTexte= new BufferedReader(new FileReader(new File(".\\src\\Ressources\\Collection1-Simplifiée.FASTA")));
			
			
			while (ficTexte !=null)
			{
					ligne=ficTexte.readLine();
					
					if(  ligne!=null )
					{
						if(ligne.isEmpty()==false)
						{
						try 
						{
							String firstLetter= ligne.substring(0,1);
							int asciiCode=(int) firstLetter.charAt(0);
							
							if (asciiCode==62)
							{
								id++;
								
								if(first)
								{
									fragment.calculComplementaire();
									collection.addFragments(fragment);
								}else
								{
									first=true;
								}
								fragment = new Fragment(id);
							}else
							{
								
								fragment.addChaine(ligne);
							}
						}catch (Exception e)
						{
							System.out.println(e.getMessage());
						}	
					
				     }
					}else
					{
						collection.addFragments(fragment);
						ficTexte=null;
					}
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		
		
		return collection;
		
	}

	/**
	 * Application de l'algorithme semi-global.
	 * @param collection collection comprenant les fragments
	 * @param mode définit si les fragments sont inversés complémentaires ou pas.
	 */
	
	public static void applyAlgoSemiGlobal(CollectionFragments collection,int mode)
	{
		try
		{
		Fragment frag1 = collection.giveFirstFragment();
		Fragment frag2 =collection.giveSecondFragment();
		
		String chaine1="";
		String chaine2="";
		
		Object link =  new Object();
		
		switch(mode)
		{
		case 1: // standard et standard
			 chaine1=frag1.getChaine();
			 chaine2=frag2.getChaine();
			 
		//	 Node source, Node destination,boolean chaineSourceCompl,boolean chaineDestinationCompl, int value
			 
			 link=new Link(frag1.getId(),frag2.getId(),false,false,1);
			 
		
		break;
		case 2: // standard et Compl.inversé
			 chaine1=frag1.getChaine();
			 chaine2=frag2.getComplementaire();
			 link=new Link(frag1.getId(),frag2.getId(),false,true,2);
		
		break;
		
		case 3: // Compl.inversé et standard
			 chaine1=frag1.getChaine();
			 chaine2=frag2.getComplementaire();
			 link = new Link(frag1.getId(),frag2.getId(),true,false,3);
		
			break;
			
		case 4: //inversé et inversé
			chaine1=frag1.getComplementaire();
			chaine2=frag2.getComplementaire();
			link  =new Link(frag1.getId(),frag2.getId(),false,false,4);
			break;
		default:
			 chaine1=frag1.getChaine();
			 chaine2=frag2.getChaine();
			 link = new Link(frag1.getId(),frag2.getId(),false,false,5);
		break;
		}
				
		
		listLink.add((Link) link);
		
		frag1.actualiseSize();
		frag2.actualiseSize();		
		int[][] matrice=Algo.semiGlobal(chaine1, chaine2, frag1.getSize()+1, frag2.getSize()+1);

		
			int max1= Algo.findMaxRow(matrice[frag1.getSize()]);
			System.out.println(max1);
			int max2=Algo.findMaxColumn(matrice);
			System.out.println(max2);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	
}
