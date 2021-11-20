package Assignment_3;

import java.io.FileReader;
import java.util.TreeSet;
import java.util.Vector;
import java.io.BufferedReader;

public class threads extends Thread {

	private String word_file;
	private String content_file;
	threads(String file1,String file2)
	{
		word_file=file1;
		content_file=file2;
	}
	public void run()
	{
		FileReader FS;
		BufferedReader bf;
		try {
		FS=new FileReader(word_file);
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
}
