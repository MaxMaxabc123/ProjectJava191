package peevedFowls;

public class Block extends PhysicsObject
{
	 double blocksBreakingJoule;
	
	
	public void getHit(Vector incomingVelocty,Vector incomingPosistion)
	{
		objectVelocity=incomingVelocty;
		objectPosistion=incomingPosistion;
	}
	public double returnBlocksBreakingJoule()
	{
		return blocksBreakingJoule;
	}
	public void damageTheBlock(Vector incomingVelocty,double time)
	{
		blocksBreakingJoule-=(incomingVelocty.evaluateVelocity(time)*incomingVelocty.evaluateVelocity(time)*1.0/2*objectMass);
	}
	public Block(int xCord,int yCord,double breakingJoule,int size,int mass)
	{
		xCoordinate=xCord;
		yCoordinate=yCord;
		blocksBreakingJoule=breakingJoule;
		objectSize = size;
		objectMass = mass;
	}
	
	
	
}
