package Assignment_3;

import java.util.Scanner;
import java.io.FileReader;
import java.util.TreeSet;
import java.util.Vector;
import java.io.BufferedReader;



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
			System.out.print("Error! File not Found");
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
				System.out.print("Error! File not Found");
			}
		}
	}
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
	public void menu()
	{
		//Menu
		System.out.println("Enter 1 to Display BST from Vocabulary file");
		System.out.println("Enter 2 to Display Vectors from Input files");
		System.out.println("Enter 3 to View match words and frequency");
		System.out.println("Enter 4 to Search a query-> it should display all the files query found in");
		System.out.println("Enter 5 to Exit");
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
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
//		System.out.println(t[1].file_name);
//		System.out.println(t[0].file_name);
//		
//		System.out.println(t[2].file_name);
		while(choice != 5)
		{
			try 
			{
				choice=input.nextInt();
				//if(choice < 0 || choice > 3)
					//throw new OutofLimit("Out of Limit");
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
				else if(choice == 5)
					break;
			}
			catch(Exception e)
			{
				System.out.print(e);
			}
		}
	}
};
