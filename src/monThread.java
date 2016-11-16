import Ressources.CollectionFragments;
import Ressources.Fragment;


public class monThread extends Thread
{
	private CollectionFragments collection=null;
	private int mode=0;
	public monThread(CollectionFragments collection,int mode)
	{
		this.collection=collection;
		this.mode=mode;
	}
	public void run()
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
