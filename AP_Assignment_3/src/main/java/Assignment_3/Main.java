package Assignment_3;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		
		for( i=0; i < args.length; ++i)
		{
			System.out.println(args[i]);
		}
		System.out.println("No. of files: " + i);
		
		//creating thread named it as filesname 
		threads t1=new threads(args[0],args[0]);
		t1.start();
	}
};
