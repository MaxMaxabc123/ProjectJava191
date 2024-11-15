package peevedFowls;

public class Fowl extends PhysicsObject
{
	
	public void launch(Vector newVelocity,Vector newPosistionVector)
	{
		objectVelocity = newVelocity;
		objectPosistion=newPosistionVector;
	}
	public void launch(Vector newPosistionVector)
	{
		objectPosistion=newPosistionVector;
	}
	public Fowl(int xCord,int yCord,int size,int mass)
	{
		xCoordinate = xCord;
		yCoordinate = yCord;
		objectSize = size;
		objectMass = mass;
	}
}
