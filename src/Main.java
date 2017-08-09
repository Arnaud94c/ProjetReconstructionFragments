import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import Ressources.CollectionFragments;
import Ressources.Fragment;
import Ressources.Node;
import Ressources.Link;
import Ressources.Graphe;


public class Main {
	
	    public static ArrayList<Node> listNode= new ArrayList<Node>();
		public static ArrayList<Link> listLink= new ArrayList<Link>();
		public static String filePath;
		public static String s;
		public static String sic;
		public static String fileName;
		public static String outfile;
		public static String outicfile;
		
		public static long time;

	public static  void main(String[] args) throws IOException, InterruptedException 
	{
		// TODO Auto-generated method stub
		
		 CollectionFragments collection = new CollectionFragments();
		filePath = args[0];
//		filePath="C:\\Users\\Arnaud\\Documents\\FragmentsReconstruction\\ReconstructionFragments\\files\\Collection2S.FASTA";
		collection=readFile(filePath);
		
		final CollectionFragments collection2= collection;
		
		
		System.out.println("Collection de fragments encodees");
		time=System.currentTimeMillis();
		
		short processeurs=(short) Runtime.getRuntime().availableProcessors();
			
		/*
		for(int j=0;j<collection.giveNumberFragments();j++)
		{
			Node node = new Node(j);
			listNode.add(node);
			
			while(collection.giveAdjustedIndex()<(collection.giveNumberFragments()))
			{	
				//applyAlgoSemiGlobal(collection,1);
				//applyAlgoSemiGlobal(collection,2);
				applyAlgoSemiGlobal(collection.getFragment(j),collection.getFragment(collection.giveAdjustedIndex()),1);
				applyAlgoSemiGlobal(collection.getFragment(j),collection.getFragment(collection.giveAdjustedIndex()),2);
				collection.incrementIndexCompare();
			}
				
			collection.incrementIndexComparateur();
			collection.adjustIndexCompare();		
		}
		*/
		
		
		for (int j=0;j<collection2.giveNumberFragments();j++)
		{
			Node node =new Node(j);
			listNode.add(node);
			ExecutorService executor= new ThreadPoolExecutor(4,processeurs,60,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
			final int comparateur=j;
			
			for( int k=collection2.giveAdjustedIndex();k<collection2.giveNumberFragments();k++)
			{
					executor.submit(new Runnable()
					{
					@Override
					public void run()
					{	
				
						try
						{							
									applyAlgoSemiGlobal(collection2.getFragment(comparateur),collection2.getFragment(collection2.giveAdjustedIndex()),1);
									applyAlgoSemiGlobal(collection2.getFragment(comparateur),collection2.getFragment(collection2.giveAdjustedIndex()),2);	
								//	System.out.println("taches effectues"+collection2.giveAdjustedIndex());	
									
									synchronized(collection2)
									{
										collection2.incrementIndexCompare();
									}
														
						}
						catch(Exception e)
						{
							System.out.println(e);
						}
					
					}
				    });	
						
				//comparateur et compare		
			}
				
				    executor.shutdown();
					executor.awaitTermination(500,TimeUnit.SECONDS);
					collection2.incrementIndexComparateur();
					collection2.adjustIndexCompare();
				
			
		}
		
		
		
	
		
//		System.out.println("fin");
		time=(System.currentTimeMillis()-time)/1000;
		System.out.println("executer en :"+time+" secondes");
		Graphe graphe = new Graphe(listNode,listLink);
		System.out.println("Tri decroissant en cours ...");
		graphe.triHeap();
		Greedy greedy = new Greedy(graphe);
		ArrayList<Integer> chemin = greedy.applyAlgo();
		s = Unifier.unify(chemin, graphe.getNode(), collection);
		System.out.println("Chemin calcule");
		String fastaPath=CreateFastaFile(args[2],s);
		System.out.println("Le fichier de resultat se situe:"+fastaPath);
		sic =s;
		for (int i=0; i<s.length();i++)
		{
			char caractere=s.charAt(i);
			switch(caractere)
			{
			case 'a':
				sic=replaceChar(sic,i,'t');
			break;
			case 'c':
				sic=replaceChar(sic,i,'g');
				break;
			case 'g':
				sic=replaceChar(sic,i,'c');
				break;
			case 't':
				sic=replaceChar(sic,i,'a');
			break;
			default:
				break;
			
			}
			StringBuilder buff=new StringBuilder(sic);
			sic= buff.reverse().toString();
			
		}
		fastaPath=CreateFastaFile(args[4],sic);
		System.out.println("Le fichier de resultat se situe:"+fastaPath);
		
		
	}
	public static String replaceChar(String s,int position, char c)
	{
		return s.substring(0,position)+ c+ s.substring(position+1);
	}
	/**
	 * Lecture du fichier
	 * @return Collection de fragments
	 * @throws IOException
	 */
	
	public static  CollectionFragments readFile (String name) throws IOException
	{
		String ligne="";
		BufferedReader ficTexte;
		int id=0;
		Fragment fragment=new Fragment();
		boolean first=false;
		CollectionFragments collection= new CollectionFragments();
		
		try
		{	

			File file= new File(name);
	     	fileName=file.getName();
			ficTexte= new BufferedReader(new FileReader(file));
		
			
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
								
								
								if(first)
								{
									fragment.calculComplementaire();
									collection.addFragments(fragment);
								}else
								{
									first=true;
								}
								fragment = new Fragment(id);
								id++;
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
						fragment.calculComplementaire();
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
	 * Ecriture du fichier Fasta
	 */
	
	public static String CreateFastaFile(String fileN, String data)
	{
		File file = new File(fileN);
		FileWriter writer;
		
		try
		{
			writer= new FileWriter(file);
			// > Groupe-num_groupe Collection num_collection Longueur longueur_sequence_cible
			writer.write(">"+"Groupe-10"+" "+"Collection "+fileName+" "+"Longueur "+data.length()+"\r\n");
			writer.write(data);
			writer.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
		 String path=file.getAbsolutePath();
		return path;
	}
	
	
	
	/**
	 * Application de l'algorithme semi-global.
	 * @param collection collection comprenant les fragments
	 * @param mode definit si les fragments sont inverses complementaires ou pas.
	 */
	
	//public static void applyAlgoSemiGlobal(CollectionFragments collection,int mode)
	public static void applyAlgoSemiGlobal(Fragment frag1,Fragment frag2,int mode )
	{
		try
		{
	//	Fragment frag1 = collection.giveFirstFragment();
	//	Fragment frag2 =collection.giveSecondFragment();
		
		
		String chaine1="";
		String chaine2="";
		
		Object link =  new Object();
		
		switch(mode)
		{
		case 1: // standard et standard
			
				chaine1=frag1.getChaine();
			 chaine2=frag2.getChaine(); 
			 
		break;
		case 2: // standard et Compl.inverse
			 chaine1=frag1.getChaine();
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

			int max1= Algo.findMaxRow(matrice[frag1.getSize()])[0]; 
			int max2=Algo.findMaxColumn(matrice)[0];

		switch(mode)
		{
		case 1: // standard et standard
			// n n
			synchronized(listLink)
			{
			link=new Link(frag1.getId(),frag2.getId(),false,false,max1); 
			listLink.add((Link) link);
			link=new Link(frag2.getId(),frag1.getId(),false,false,max2);
			listLink.add((Link) link);
			}
		
			
			// ci ci
			//link=new Link(frag1.getId(),frag2.getId(),true,true,max2); 
			//link=new Link(frag2.getId(),frag1.getId(),true,true,max1);
		break;
		case 2: // standard et Compl.inverse
			// n ci
			synchronized(listLink)
			{
			link=new Link(frag1.getId(),frag2.getId(),false,true,max1);
			listLink.add((Link) link);
			link=new Link(frag2.getId(),frag1.getId(),true,false,max2);
			listLink.add((Link) link);
			}
			
			// ci n
			//link=new Link(frag1.getId(),frag2.getId(),true,false,max2);
			//link=new Link(frag2.getId(),frag1.getId(),false,true,max1);
			
		break;
		
		default:
		break;
		}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	
}
