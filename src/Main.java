import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Ressources.CollectionFragments;
import Ressources.Fragment;


public class Main {

	public static  void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		
		CollectionFragments collection = new CollectionFragments();
		System.out.println("Bonjour");
		collection=readFile();
		System.out.println("Collection de fragments encodées");
		
		for(int j=0;j<collection.giveNumberFragments();j++)
		{

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
			//	applyAlgoSemiGlobal(collection,4);
				collection.incrementIndexCompare();
			}
			
			collection.incrementIndexComparateur();
			collection.adjustIndexCompare();
		}
		
		
		
		
		
	}
	
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
			ficTexte= new BufferedReader(new FileReader(new File("C:\\Users\\Arnaud\\Documents\\ProjetReconstructionFragments\\src\\collection2.fasta")));
				
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

	
	//1 standard standard
	//2
	//3
	//4
	
	public static void applyAlgoSemiGlobal(CollectionFragments collection,int mode)
	{
		try
		{
		Fragment frag1 = collection.giveFirstFragment();
		Fragment frag2 =collection.giveSecondFragment();
		
		String chaine1="";
		String chaine2="";
		
		switch(mode)
		{
		case 1: // standard & standard
			 chaine1=frag1.getChaine();
			 chaine2=frag2.getChaine();
		
		break;
		case 2: // standard & Compl.inversé
			 chaine1=frag1.getChaine();
			 chaine2=frag2.getComplementaire();
		
		break;
		
		case 3: // Compl.inversé & standard
			 chaine1=frag1.getChaine();
			 chaine2=frag2.getComplementaire();
		
			break;
			
		case 4: //inversé & inversé
			chaine1=frag1.getComplementaire();
			chaine2=frag2.getComplementaire();
			break;
		default:
			 chaine1=frag1.getChaine();
			 chaine2=frag2.getChaine();
		break;
		}
				
		frag1.actualiseSize();
		frag2.actualiseSize();		
		int[][] matrice=Algo.semiGlobal(chaine1, chaine2, frag1.getSize()+1, frag2.getSize()+1);
//		for (int i=0;i<frag1.getSize();i++)
//		{
//			for(int j=0;j<frag2.getSize();j++)
//			{
//				System.out.print(matrice[i][j]+" ");
//			}
//			System.out.println();
//		}
		
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
