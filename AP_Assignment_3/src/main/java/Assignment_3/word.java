package Assignment_3;

public class word {
	//member variables
	String name_of_word;
	int frequency;
	//Constructor
	word(String name)
	{
		this.name_of_word=name;
		this.frequency=1;
	}
	//member methods
	public void increment_frequency()
	{
		++frequency;
	}
	//setter function
	//getter functions
	public String get_word()
	{
		return name_of_word;
	}
	public int get_frequency()
	{
		return frequency;
	}
	public void display()
	{
		System.out.print(this.name_of_word+"	");
		System.out.println(this.frequency);
	}
};
