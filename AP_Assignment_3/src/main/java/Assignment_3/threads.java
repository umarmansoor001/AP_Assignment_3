package Assignment_3;

import java.util.Scanner;
import java.io.FileReader;
import java.util.TreeSet;
import java.util.Vector;
import java.io.BufferedReader;
import java.util.LinkedList;



public class threads extends Thread {

	String file_name;
	public TreeSet<String> ts;//using API to create AVL tree
	public Vector<String> vs;//using API to create Vectors
	
	threads(String file)
	{
		file_name=file;
		if(file.equals("vocabulary.txt"))
			ts=new TreeSet<String>();
		else
			vs=new Vector<String>();
	}
	//getter functions
	public String get_filename()
	{
		return this.file_name;
	}
	//BST creation function 
	public void make_BST()
	{
		//Creating FileReader and BufferedReader to read file 
		FileReader FS;
		BufferedReader bf;
		try {
			//Making objects
			FS=new FileReader(file_name);
			bf=new BufferedReader(FS);
			String aline;//data will store in it after reading file
			//IF file is vocabulary.txt then i need to make BST of that file's content
			if(this.file_name.equals("vocabulary.txt")) 
			{			
				//reading file 
				while((aline=bf.readLine()) != null)
				{
					ts.add(aline);//adding in BST
				}
				//closing file as it is very necessary
				FS.close();
			}
		}
		catch(Exception e)
		{
			System.out.println("Error! File not Found");
		}
	}
	//Vector creation function
	public void make_vectors()
	{
		//Creating FileReader and BufferedReader to read file 
		FileReader FS;
		BufferedReader bf;
		synchronized (this)
		{
			try {
				//Making objects
				FS=new FileReader(file_name);
				bf=new BufferedReader(FS);
				String aline;//data will store in it after reading file
				//reading file
				while((aline=bf.readLine()) != null)
				{
					String[] words=aline.split(" ");//Splitting the whole line 
					//Adding words into vectors from words array
					for(int i=0; i < words.length; ++i)
						vs.add(words[i]);
				}
				
				//closing file as it is very necessary
				FS.close();
			}
			catch(Exception e) 
			{
				System.out.println("Error! File not Found");
			}
		}
	}
	//Run method for Threads
	public void run()
	{
			//IF file is vocabulary.txt then i need to make BST of that file's content
			if(this.file_name.equals("vocabulary.txt")) 
			{	
				//Calling this function to make BST for vocabulary.txt file
				this.make_BST();
			}
			else
			{
				this.make_vectors();
			}
	}
	//Menu method
	public static void menu()
	{
		//Menu
		System.out.println("Enter 1 to Display BST from Vocabulary file");
		System.out.println("Enter 2 to Display Vectors from Input files");
		System.out.println("Enter 3 to View match words and frequency");
		System.out.println("Enter 4 to Search a query-> it should display all the files query found in");
		System.out.println("Enter 5 to Exit");
	}
	//This method is use to check occurrence of words in Linked List 
	public static boolean isalreadyPresent(LinkedList<word> L, String word)
	{
		for(word w: L)//Traversing Linked List using for each loop
		{
			if(w.get_word().equalsIgnoreCase(word))//if word found in Linked List then returns True
				return true;
		}
		return false;//if word not found in whole Linked List then returns false so we need to make this word instance
	}
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		LinkedList<word> match_words=new LinkedList<word>();
		
		
		//threads creation
		threads t[]=new threads[args.length];
		System.out.println("Name of Files:- ");
		for(int i=0; i < args.length; ++i)
		{
			//creating thread and named it as file's name 
			t[i]=new threads(args[i]);
			t[i].setName(args[i]);//setting name
			System.out.println(t[i].getName());//Printing name of thread which is actually file's name
		}
		System.out.println("No. of files: " + args.length);//displaying number of files
		//and Starting all threads
		for(int i=0; i < args.length ; ++i)
			t[i].start();//go on and do your job
		//main thread should have to sleep for some time so other threads will complete their task
		sleep(1000);
		int choice = 0;
		
		
		Scanner input=new Scanner(System.in);

		while(choice != 5)
		{
			try 
			{
				//calling menu function to display menu
				threads.menu();
				//taking input from user
				choice=input.nextInt();
				//if user enters value out of limit then throw exception
				if(choice < 0 || choice > 5)
					throw new OutofLimit("Out of Limit");//custom exception
				//User entered number's operation will perform 
				if(choice == 1)
				{
					System.out.println("Binary search tree from Vocabulary file: ");
					System.out.println(t[0].ts);
				}
				else if(choice == 2)
				{
					for(int i=1; i < args.length; ++i)
					{
						System.out.print("Vector from "+ t[i].file_name +" file is ");
						System.out.println(t[i].vs);
					}
				}	
				else if(choice == 3)
				{
					//For Vector 1
					for(String str: t[1].vs)//traversing vector 1
					{
						for(String str_ts: t[0].ts)//Traversing BST 
						{
							if(str.equalsIgnoreCase(str_ts))//if words match each other
							{
								
								if(!threads.isalreadyPresent(match_words, str))//if word's object is not present in linked list then need to create its object
								{	
									word w=new word(str);//make an instance of match word
									match_words.add(w);//add that matched word instance in Linked list
								}
								else 
									//w.increment_frequency();
								break;//and leave loop to match another word
							}
						}
					}
					//For Vector 2
					for(String str: t[2].vs)//traversing vector 2
					{
						for(String str_ts: t[0].ts)//Traversing BST
						{
							if(str.equalsIgnoreCase(str_ts))//if words match each other
							{
								if(!threads.isalreadyPresent(match_words, str))//if word's object is not present in linked list then need to create its object
								{	
									word w=new word(str);//make an instance of match word
									match_words.add(w);//add that matched word instance in Linked list
								}
								else 
									//w.increment_frequency();
								break;//and leave loop to match another word
							}
						}
					}
				}
				else if(choice == 5)
					break;
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		for(word wl: match_words)
		{
			
			System.out.println(wl.get_word());
		}
	}
};
