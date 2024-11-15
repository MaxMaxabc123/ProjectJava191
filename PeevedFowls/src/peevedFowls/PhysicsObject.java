package peevedFowls;

public abstract class PhysicsObject
{
	protected Vector objectPosistion;
	protected Vector objectVelocity;
	protected int objectMass;
	protected int xCoordinate;
	protected int yCoordinate;
	protected int objectSize;
	protected Coordinate coordinatePhysics;
	public Coordinate getCoordinate()
	{
		coordinatePhysics = new Coordinate(xCoordinate,yCoordinate);
		return coordinatePhysics;
	}	
}
	
	

