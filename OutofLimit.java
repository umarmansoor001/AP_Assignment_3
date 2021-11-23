package Assignment_3;


//Custom Exception class
public class OutofLimit extends RuntimeException {
	public OutofLimit(String msg)
	{
		super(msg);
	}
}
