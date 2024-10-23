package peevedFowls;
import java.util.ArrayList;

public class Vector 
{
	ArrayList<AlgebraicEquation> vectorAlgebraicEquation= new ArrayList<>();
	public Vector(ArrayList<AlgebraicEquation> AlgebraicEquation)
	{
		vectorAlgebraicEquation=AlgebraicEquation;
	}
	
	public double evaluateVelocity(int time)
	{
		double sum = 0;
		for(int i = 0; i <vectorAlgebraicEquation.size();i++)
		{
			sum+=vectorAlgebraicEquation.get(i).evaluate(time)*vectorAlgebraicEquation.get(i).evaluate(time);
		}
		return Math.sqrt(sum);
	}
}
