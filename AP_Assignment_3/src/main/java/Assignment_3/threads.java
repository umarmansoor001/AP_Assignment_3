package Assignment_3;

import java.util.Scanner;
import java.io.FileReader;
import java.util.TreeSet;
import java.util.Vector;
import java.io.BufferedReader;



public class threads extends Thread {

	String file_name;
	
	threads(String file)
	{
		file_name=file;
	}
	public void run()
	{
		FileReader FS;
		BufferedReader bf;
		try {
			FS=new FileReader(file_name);
			bf=new BufferedReader(FS);
			//Scanner bf=new Scanner(FS);
			String aline;
			if(file_name=="vocabulary.txt") 
			{
				TreeSet<String> ts=new TreeSet<String>();
				
				while((aline=bf.readLine()) != null)
				{
					System.out.println(aline);
					ts.add(aline);
				}
				//Now creating BST
						FS.close();
						System.out.println(ts);
			}
			else
			{
				Vector<String> vs=new Vector<String>();
				while((aline=bf.readLine()) != null)
				{
					System.out.println(aline);
					vs.add(aline);
				}
				//Now creating BST
						FS.close();
						System.out.print(vs);
			}
		}
		catch(Exception e)
		{
			System.out.println("File not Found");
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		//threads t[args.length];
		for( i=0; i < args.length; ++i)
		{
			//creating thread named it as filesname 
			threads t1=new threads(args[i]);
			t1.setName(args[i]);
			t1.start();
			System.out.println(args[i]);
		}
		System.out.println("No. of files: " + i);
		
		
	}
};
