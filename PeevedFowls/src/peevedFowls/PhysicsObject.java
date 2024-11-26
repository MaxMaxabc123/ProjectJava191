package peevedFowls;

import java.util.ArrayList;

public abstract class PhysicsObject
{
	protected Vector objectPosistion;
	protected Vector objectVelocity;
	protected int objectMass;
	protected int xCoordinate;
	protected int yCoordinate;
	protected int biggestHeight;
	protected int objectSize;
	protected Coordinate coordinatePhysics;
	double newCollisionTime=0;
	double momentumX=0;
	double momentumY=0;
	int[][] coordinateBox;
	protected boolean hitObjectXAxis=false;
	protected boolean hitObjectYAxis=false;
	protected boolean gotHit;
	
	public Coordinate getCoordinate()
	{
		coordinatePhysics = new Coordinate(xCoordinate,yCoordinate);
		return coordinatePhysics;
	}
	public void changeOrientaionX()
	{
		if(hitObjectXAxis)
		{
			hitObjectXAxis=false;
		}
		else
		{
			hitObjectXAxis=true;
		}
	}
	public void changeOrientaionYTrue()
	{
		hitObjectYAxis=true;
	}
	public void Move(double time)
	{
		if(hitObjectXAxis)
		{
			if(biggestHeight>=yCoordinate)
			{
				biggestHeight=yCoordinate;
				System.out.println(biggestHeight+"***");
			}
			if(hitObjectYAxis&&yCoordinate>biggestHeight)
			{
				yCoordinate-=(int) objectPosistion.evaluateVectorYAbsValue(time);
				if(yCoordinate-(int) objectPosistion.evaluateVectorYAbsValue(time)<biggestHeight)
				{
					yCoordinate=biggestHeight;
				}
			}
			else
			{
				hitObjectYAxis=false;
				yCoordinate+=(int) objectPosistion.evaluateVectorY(time);
			}
			xCoordinate-=(int) objectPosistion.evaluateVectorX(time);
		}
		else
		{
			xCoordinate+=(int) objectPosistion.evaluateVectorX(time);
			if(biggestHeight>=yCoordinate)
			{
				biggestHeight=yCoordinate;
				System.out.println(biggestHeight+"***");
			}
			if(hitObjectYAxis&&yCoordinate>biggestHeight)
			{
				yCoordinate-=(int) objectPosistion.evaluateVectorYAbsValue(time);
				if(yCoordinate-(int) objectPosistion.evaluateVectorYAbsValue(time)<biggestHeight)
				{
					yCoordinate=biggestHeight;
				}
			}
			else
			{
				hitObjectYAxis=false;
				yCoordinate+=(int) objectPosistion.evaluateVectorY(time);
			}
		}
		
	}
	public void getCollided(Vector incomingPosistion, Vector incomingVelocity)
	{
		String incomingPosistionXString; 
		String incomingPosistionYString;
		String currentPosistionXString;
		String currentPosistionYString;
		String incomingVelocityXString; 
		String incomingVelocityYString;
		String currentVelocityXString;
		String currentVelocityYString;
		ArrayList<AlgebraicEquation> newStoragePosistionVector = new ArrayList<AlgebraicEquation>();
		ArrayList<AlgebraicEquation> newStorageVeloctiyVector = new ArrayList<AlgebraicEquation>();
		incomingPosistionXString = incomingPosistion.vectorAlgebraicEquation.get(0).toString().substring(0, incomingPosistion.vectorAlgebraicEquation.get(0).toString().length());
		incomingPosistionYString = incomingPosistion.vectorAlgebraicEquation.get(1).toString().substring(0, incomingPosistion.vectorAlgebraicEquation.get(1).toString().length());
		currentPosistionXString = objectPosistion.vectorAlgebraicEquation.get(0).toString().substring(0, objectPosistion.vectorAlgebraicEquation.get(0).toString().length());
		currentPosistionYString = objectPosistion.vectorAlgebraicEquation.get(1).toString().substring(0, objectPosistion.vectorAlgebraicEquation.get(1).toString().length());
		incomingVelocityXString = incomingVelocity.vectorAlgebraicEquation.get(0).toString().substring(0, incomingVelocity.vectorAlgebraicEquation.get(0).toString().length());
		incomingVelocityYString = incomingVelocity.vectorAlgebraicEquation.get(1).toString().substring(0, incomingVelocity.vectorAlgebraicEquation.get(1).toString().length());
		currentVelocityXString = objectVelocity.vectorAlgebraicEquation.get(0).toString().substring(0, objectVelocity.vectorAlgebraicEquation.get(0).toString().length());
		currentVelocityYString = objectVelocity.vectorAlgebraicEquation.get(1).toString().substring(0, objectVelocity.vectorAlgebraicEquation.get(1).toString().length());
		// new posistion vector
		if(incomingPosistionXString.charAt(0)=='-')
		{
			newStoragePosistionVector.add(GameMain.generateAlgebraicEquation(currentPosistionXString+incomingPosistionXString+":"));
		}
		else
		{
			newStoragePosistionVector.add(GameMain.generateAlgebraicEquation(currentPosistionXString+"+"+incomingPosistionXString+":"));
		}
		if(incomingPosistionYString.charAt(0)=='-')
		{
			newStoragePosistionVector.add(GameMain.generateAlgebraicEquation(currentPosistionYString+incomingPosistionYString+":"));
		}
		else
		{
			newStoragePosistionVector.add(GameMain.generateAlgebraicEquation(currentPosistionYString+"+"+incomingPosistionYString+":"));
		}
		// new velocity vectors
		if(incomingVelocityXString.charAt(0)=='-')
		{
			newStorageVeloctiyVector.add(GameMain.generateAlgebraicEquation(currentVelocityXString+incomingVelocityXString+":"));
		}
		else
		{
			newStorageVeloctiyVector.add(GameMain.generateAlgebraicEquation(currentVelocityXString+"+"+incomingVelocityXString+":"));
		}
		if(incomingVelocityYString.charAt(0)=='-')
		{
			newStorageVeloctiyVector.add(GameMain.generateAlgebraicEquation(currentVelocityYString+incomingVelocityYString+":"));
		}
		else
		{
			newStorageVeloctiyVector.add(GameMain.generateAlgebraicEquation(currentVelocityYString+"+"+incomingPosistionYString+":"));
		}
		objectPosistion = new Vector(newStoragePosistionVector);
		objectVelocity = new Vector(newStorageVeloctiyVector);
		
	}
}
	
	

