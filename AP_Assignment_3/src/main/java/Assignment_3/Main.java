package Assignment_3;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.TreeSet;
import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		
		for( i=0; i < args.length; ++i)
		{
			System.out.println(args[i]);
		}
		System.out.println("No. of files: " + i);
		//Reading file and displaying its content
		String filename=args[0];
		FileReader FS;
		BufferedReader bf;
		try {
		FS=new FileReader(filename);
		bf=new BufferedReader(FS);
		String aline;
		TreeSet<String> ts=new TreeSet<String>();
		Vector<String> vs=new Vector<String>();
		while((aline=bf.readLine()) != null)
		{
			System.out.println(aline);
			ts.add(aline);
			vs.add(aline);
		}
		//Now creating BST
				FS.close();
				System.out.println(ts);
				System.out.print(vs);
		}
		catch(Exception e)
		{
			System.out.println("File not Found");
		}
		
		
	}
};
