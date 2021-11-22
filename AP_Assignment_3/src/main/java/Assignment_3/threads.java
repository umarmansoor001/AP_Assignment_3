package Assignment_3;

import java.util.Scanner;
import java.io.FileReader;
import java.util.TreeSet;
import java.util.Vector;
import java.io.BufferedReader;



public class threads extends Thread {

	String file_name;
	public static TreeSet<String> ts;//=new TreeSet<String>();//using API to create AVL tree
	public static Vector<String> vs;//=new Vector<String>();//using API to create Vectors
	
	threads(String file)
	{
		file_name=file;
		if(file.equals("vocabulary.txt"))
			ts=new TreeSet<String>();
		else
			vs=new Vector<String>();
	}
	
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
			
		}
	}
	synchronized public void make_vectors()
	{
		//Creating FileReader and BufferedReader to read file 
				FileReader FS;
				BufferedReader bf;
				try {
					//Making objects
					FS=new FileReader(file_name);
					bf=new BufferedReader(FS);
					String aline;//data will store in it after reading file
					//Vector<String> vs=new Vector<String>();
					//reading file
					while((aline=bf.readLine()) != null)
					{
						String[] words=aline.split(" ");//Splitting the whole line 
						//Adding words into vectors from words array
						for(int i=0; i < words.length; ++i)
							vs.add(words[i]);
					}
					//When one file will be completely read and stored in vector then
					//putting ( . ) period in Vector to differentiate files contents
					vs.add(".");
					
					//closing file as it is very necessary
					FS.close();
					
					//System.out.println(vs);
					//for(String word : vs)
						//System.out.print(word+" ");
					//System.out.println();
				}
				catch(Exception e) 
				{
					
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
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		threads t[]=new threads[args.length];
		System.out.println("Name of Files:- ");
		for(int i=0; i < args.length; ++i)
		{
			//creating thread named it as file's name 
			t[i]=new threads(args[i]);
			t[i].setName(args[i]);//setting name
			System.out.println(args[i]);
		}
		System.out.println("No. of files: " + args.length);//displaying number of files
		//and Starting all threads
		for(int i=args.length-1; i >= 0 ; --i)
			t[i].start();
		//main thread should have to wait for some time so threads will complete their task
		sleep(1000);
		int choice;
		//Menu
		System.out.println("Enter 1 to Display BST from Vocabulary file");
		System.out.println("Enter 2 to Display Vectors from Input files");
		System.out.println("Enter 3 to Exit");
		System.out.println(t[1].vs);
		System.out.println(t[0].ts);
	//	Scanner input=new Scanner(System.in);
//		System.out.println(t[1].file_name);
//		System.out.println(t[0].file_name);
//		
//		System.out.println(t[2].file_name);
//		try 
//		{
//			choice=input.nextInt();
//			//if(choice < 0 || choice > 3)
//				//throw new OutofLimit("Out of Limit");
//		}
//		catch(Exception e)
//		{
//			System.out.print(e);
//		}
//		
	}
};
