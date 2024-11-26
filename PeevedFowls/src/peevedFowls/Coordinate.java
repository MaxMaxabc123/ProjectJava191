package peevedFowls;

public class Coordinate 
{
	private int xCoordinate;
	private int yCoordinate;
	protected boolean accessed=false;
	protected PhysicsObject occupyingPhysicsObject=null;
	public Coordinate(int x,int y)
	{
		xCoordinate=x;
		yCoordinate=y;
	}
	public int getXCoord()
	{
		return xCoordinate;
	}
	public int getYCoord()
	{
		return yCoordinate;
	}
	public String toString()
	{
		return xCoordinate+","+yCoordinate;
	}
}
