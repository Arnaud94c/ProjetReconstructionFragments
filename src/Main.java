import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Ressources.CollectionFragments;
import Ressources.Fragment;


public class Main {

	public static  void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		CollectionFragments collection = new CollectionFragments();
		System.out.println("Bonjour");
		collection=readFile();
		System.out.println("Collection de fragments encodées");
		
		for(int i=0;i<collection.giveNumberFragments();i++)
		{
			applyAlgoSemiGlobal(collection);
			collection.incrementIndex();
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

	
	public static void applyAlgoSemiGlobal(CollectionFragments collection)
	{
		Fragment frag1 = collection.giveFirstFragment();
		Fragment frag2 =collection.giveSecondFragment();
				
		
		// pour l'instant prend juste en compte les fragments tel quel ,  encore les complémentaires et les inversés à prendre en compte
		
		frag1.actualiseSize();
		frag2.actualiseSize();		
		int[][] matrice=Algo.semiGlobal(frag1.getChaine(), frag2.getChaine(), frag1.getSize()+1, frag2.getSize()+1);
		for (int i=0;i<frag1.getSize();i++)
		{
			for(int j=0;j<frag2.getSize();j++)
			{
				System.out.print(matrice[i][j]+" ");
			}
			System.out.println();
		}
		
		int max1= Algo.findMaxRow(matrice[frag1.getSize()]);
		System.out.println(max1);
		int max2=Algo.findMaxColumn(matrice);
		System.out.println(max2);
	}
	
	
}
