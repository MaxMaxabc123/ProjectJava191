package peevedFowls;

public class Variable 
{
	// algebra has a char
	private char algebraVariable;
	// algebra has a number
	private double algebraNumber;
	// algebra has a exponent
	private int algebraExponent;
	public Variable(char variable,double number)
	{
		algebraVariable=variable;
		algebraNumber=number;
		algebraExponent = 1;
	}
	public Variable(char variable, double number,int exponent)
	{
		algebraVariable=variable;
		algebraNumber=number;
		algebraExponent = exponent;
	}
	public double evaluate(int number)
	{
		for(int i = 1; i < algebraExponent;i++)
		{
			number*=number;
		}
		return algebraNumber*number;
		
	}
	
	public String toString()
	{
		String s = "";
		if(algebraExponent>1)
		{
			s="^"+algebraExponent;
		}
		return algebraNumber+""+algebraVariable+s;
	}
}
