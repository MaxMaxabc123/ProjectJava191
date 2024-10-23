package peevedFowls;

import java.util.ArrayList;

public class AlgebraicEquation
{
	private ArrayList<Variable> equationVariable= new ArrayList<>();
	private double equationNumber=0;
	public AlgebraicEquation(ArrayList<Variable> variables,double number)
	{
		equationVariable=variables;
		equationNumber = number;
	}
	public double evaluate(int evaluateNumber)
	{
		int resultSum = 0;
		for(int i = 0; i < equationVariable.size();i++)
		{
			resultSum+=equationVariable.get(i).evaluate(evaluateNumber);
		}
		return resultSum+equationNumber;
	}
	
}
