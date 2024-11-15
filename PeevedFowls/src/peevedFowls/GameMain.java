package peevedFowls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
public class GameMain
{
	static Level gameLevel;
	static double time=0;
	boolean loadState;
	boolean firingState;
	static Timer timer;
	
	public static void hitDetection()
	{
		// sets the physics plane with the coordinates of all the levels physics objects
		for(int t = 0; t<gameLevel.physicsListLevel.length;t++)
		{
			gameLevel.physicsLevelPlane[(int)gameLevel.physicsListLevel[t].yCoordinate][(int)gameLevel.physicsListLevel[t].xCoordinate] = gameLevel.physicsListLevel[t];
		}
		// goes through all the physics objects and gets their x cord and y cord
		for(int t = 0; t < gameLevel.physicsListLevel.length;t++)
		{
			// two starting coordinates that are the physics objects xCoord and yCoord which is where in the physicsLevelPlane we will start to iterate
			int startingCordX,startingCordY;
			startingCordX=gameLevel.physicsListLevel[t].xCoordinate;
			startingCordY=gameLevel.physicsListLevel[t].yCoordinate;
			// checks the surrounding blocks in the physics list level based on the size of the object 
			for(int i = 0;i<gameLevel.physicsListLevel[t].objectSize;i++)
			{
				for(int j = 0;j<gameLevel.physicsListLevel[t].objectSize;j++)
				{
					// checks if the object in the range of the physics object is a block 
					if(gameLevel.physicsLevelPlane[(int) startingCordY+i][(int) startingCordX+j]instanceof Block)
					{
						// damage the block according to how hard it was hit by the physics object
						((Block) gameLevel.physicsLevelPlane[(int) startingCordY+i][(int) startingCordX+j]).damageTheBlock(gameLevel.physicsListLevel[t].objectVelocity,time);
						//if the block is damaged to the point where the strength of the block is 0 or less set the object in the plane to null and it goes poof, bye bye
						if(((Block) gameLevel.physicsLevelPlane[(int) startingCordY+i][(int) startingCordX+j]).returnBlocksBreakingJoule()<=0)
						{
							gameLevel.physicsLevelPlane[(int) startingCordY+i][(int) startingCordX+j]=null;
						}
						// if its not broken then set the collided objects with the velocity of the physics object being checked in the list
						else
						{
							((Block) gameLevel.physicsLevelPlane[(int) startingCordY+i][(int) startingCordX+j]).getHit(gameLevel.physicsListLevel[t].objectVelocity,gameLevel.physicsListLevel[t].objectPosistion);
						}
					}
				}
			}
		}
	}
	public static boolean PiggiesInLevel()
	{
		for(int i = 0; i < gameLevel.physicsListLevel.length;i++)
		{
			if(gameLevel.physicsListLevel[i] instanceof Pig)
			{
				return true;
			}
		}
		return false;
	}
	
	public static AlgebraicEquation generateAlgebraicEquation(String string)
	{
		AlgebraicEquation equation = null;
		ArrayList<Variable> arrayList = new ArrayList<Variable>();
		int number=1;
		boolean negNum=false;
		int i = 0;
		while (true)
		{
		switch(string.charAt(i))
		{
			case ':':
			{
				
				equation = new AlgebraicEquation(arrayList,number);
				return equation;
			
			}
			case '1','2','3','4','5','6','7','8','9','0':
			{
				number=0;
				do
				{
					number = number * 10 + ((int)string.charAt(i)-48);
					i++;
				}while(string.charAt(i)<='9'&&string.charAt(i)>='0');
				if(negNum)
				{
					number*=-1;
				}
				break;
			}
			case't':
			{
				if(string.charAt(i+1)=='^')
				{
					arrayList.add(new Variable(string.charAt(i),number,string.charAt(i+2)-48));
					number=0;
					i+=3;
				}
				else
				{
				arrayList.add(new Variable(string.charAt(i),number));
				number=0;
				i++;
				}
				break;
			}
			case'+':
			{
				negNum=false;
				i++;
				break;
			}
			case'-':
			{
				negNum=true;
				number*=-1;
				i++;
			}
			
		}
		}
	}
	public static Level LevelBuilder(String string,int LEVEL_WIDTH,int LEVEL_HEIGHT)
	{
		ArrayList<Fowl> fowlList = new ArrayList<Fowl>();
		ArrayList<Block> blockList = new ArrayList<Block>();
		Level returnLevel=null;
		int i = 0;
		int posX=0;
		int posY = 0;
		int breakingJoule=0;
		int size = 0;
		int mass = 0;
		int numPos=0;
		int numTemp = 0;
		
		while(true)
		{
			switch(string.charAt(i))
			{
				case '(':
				{
					i++;
					break;
				}
				case '1','2','3','4','5','6','7','8','9','0':
				{
					numTemp=0;
					do
					{
						numTemp = numTemp * 10 + ((int)string.charAt(i)-48);
						i++;
					}while(string.charAt(i)<='9'&&string.charAt(i)>='0');
					if(numPos==0)
					{
						posX=numTemp;
					}
					if(numPos==1)
					{
						posY=numTemp;
					}
					if(numPos == 2)
					{
						breakingJoule = numTemp;
					}
					if(numPos == 3)
					{
						size = numTemp;
					}
					if(numPos==4)
					{
						mass = numTemp;
					}
					break;
					}
				case ',':
				{
					i++;
					numPos++;
					break;
				}
				case')':
				{
					numPos=0;
					i++;
					break;
				}
				case'P':
				{
					blockList.add(new Pig(posX,posY,breakingJoule,size,mass));
					i++;
					break;
				}
				case'+':
				{
					
					posX=0;
					posY=0;
					mass=0;
					breakingJoule=0;
					i++;
					break;
				}
				case'B':
				{
					blockList.add(new Block(posX,posY,breakingJoule,size,mass));
					i++;
					break;
				}
				case 'F':
				{
					fowlList.add(new Fowl(posX,posY,breakingJoule,size));
					i++;
					break;
				}
				case ':':
				{
					returnLevel = new Level(LEVEL_WIDTH,LEVEL_HEIGHT,fowlList,blockList);
					return returnLevel;
				}
			}
		}
	}
	public static void GravityGenerator()
	{
		String gravityStringCompY = "5t^2:";
		String gravityStringCompX = "0t:";
		ArrayList<AlgebraicEquation> gravVector = new ArrayList<>();
		gravVector.add(generateAlgebraicEquation(gravityStringCompX));
		gravVector.add(generateAlgebraicEquation(gravityStringCompY));
		for(int i = 0;i < gameLevel.physicsListLevel.length;i++)
		{
			gameLevel.physicsListLevel[i].objectPosistion = new Vector(gravVector);
		}
	}
		
	
	public static void main(String[] args) throws InterruptedException
	{
//		String posistionY = "-10t^2-10t+100:";
//		System.out.println(generateAlgebraicEquation(posistionY).evaluate(2));
		gameLevel = LevelBuilder("(100,100,14,13,123)B+(200,200,24,12)F+(300,300,31,12,15)P:",800,800);
		System.setProperty("sun.java2d.opengl", "true");
		GravityGenerator();
		timer = new Timer();
		new MyFrame();
		
//		 where the game happens
		do
		{
			hitDetection();
			time+=.033;
		}while(PiggiesInLevel());
	}
	
	
}

