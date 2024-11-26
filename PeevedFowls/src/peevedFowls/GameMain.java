package peevedFowls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

public class GameMain
{
	static Level gameLevel;
	static double time=0;
	boolean loadState;
	boolean firingState;
	static Timer timer;
	
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
	public static boolean BirdsInLevel()
	{
		for(int i = 0; i < gameLevel.physicsListLevel.length;i++)
		{
			if(gameLevel.physicsListLevel[i] instanceof Fowl)
			{
				return true;
			}
		}
		return false;
	}
	public static Vector generateVector(String string)
	{
		String xCordEquation="";
		String yCordEquation="";
		Vector VectorReturn;
		ArrayList<AlgebraicEquation> arrayList = new ArrayList<AlgebraicEquation>();
		int i = 0;
		while(string.charAt(i)!='&')
		{
			xCordEquation+=string.charAt(i);
			i++;
		}
		i++;
		while(i<string.length())
		{
			yCordEquation+=string.charAt(i);
			i++;
		}
//		System.out.println(xCordEquation);
//		System.out.println(yCordEquation);
		arrayList.add(generateAlgebraicEquation(xCordEquation));
		arrayList.add(generateAlgebraicEquation(yCordEquation));
		VectorReturn = new Vector(arrayList);
		return VectorReturn;
	}
	public static AlgebraicEquation generateAlgebraicEquation(String string)
	{
		AlgebraicEquation equation = null;
		ArrayList<Variable> arrayList = new ArrayList<Variable>();
		int number=1;
		boolean negNum=false;
		int i = 0;
		ArrayList<Integer> freeIntList = new ArrayList<Integer>();
		int finalIntNum=0;
//		"-10t^2-10t+100+10t^2+10t-100:";
		while (true)
		{
		switch(string.charAt(i))
		{
			case ':':
			{
				for(int j = 0; j < freeIntList.size();j++)
				{
					finalIntNum+=freeIntList.get(j);
				}
				equation = new AlgebraicEquation(arrayList,finalIntNum);
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
				if(string.charAt(i)!='t')
				{
					freeIntList.add(number);
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
		String gravityPosStringCompY = "5t^2:";
		String gravityPosStringCompX = "0t:";
		String gravityVelStringCompY="10t:";
		String gravityVelStringCompX = "0t:";
		ArrayList<AlgebraicEquation> gravPosVector = new ArrayList<>();
		gravPosVector.add(generateAlgebraicEquation(gravityPosStringCompX));
		gravPosVector.add(generateAlgebraicEquation(gravityPosStringCompY));
		ArrayList<AlgebraicEquation> gravVelVector = new ArrayList<>();
		gravVelVector.add(generateAlgebraicEquation(gravityVelStringCompX));
		gravVelVector.add(generateAlgebraicEquation(gravityVelStringCompY));
		ArrayList<AlgebraicEquation> blankPosVector = new ArrayList<>();
		blankPosVector.add(generateAlgebraicEquation(gravityPosStringCompX));
		blankPosVector.add(generateAlgebraicEquation(gravityPosStringCompX));
		ArrayList<AlgebraicEquation> blankVelVector = new ArrayList<>();
		blankVelVector.add(generateAlgebraicEquation(gravityPosStringCompX));
		blankVelVector.add(generateAlgebraicEquation(gravityPosStringCompX));
		for(int i = 0;i < gameLevel.physicsListLevel.length;i++)
		{
			if(gameLevel.physicsListLevel[i] instanceof Block&& !gameLevel.physicsListLevel[i].gotHit)
			{
				gameLevel.physicsListLevel[i].objectPosistion = new Vector(blankPosVector);
				gameLevel.physicsListLevel[i].objectVelocity = new Vector(blankVelVector);
			}
			else
			{
				gameLevel.physicsListLevel[i].objectPosistion = new Vector(gravPosVector);
				gameLevel.physicsListLevel[i].objectVelocity = new Vector(gravVelVector);
			}
		}
	}
	public static void SetObjectsVectors(String vectorX,String vectorY)
	{
		ArrayList<AlgebraicEquation> gravPosVector = new ArrayList<>();
		gravPosVector.add(generateAlgebraicEquation(vectorX));
		gravPosVector.add(generateAlgebraicEquation(vectorY));
		for(int i = 0;i < gameLevel.physicsListLevel.length;i++)
		{
			gameLevel.physicsListLevel[i].objectPosistion = new Vector(gravPosVector);
		}
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		String posistionY = "-200t^2-10t-100:";
		String posistionX = "10t+10:";
//		String s = "(205,205,14000,13,123)B+(200,200,24,12)F+(300,300,31000,12,15)P:";
		gameLevel = LevelBuilder("(20,20,24,12)F+(600,788,14000000,12,123)B+(600,776,14000000,12,123)B+(600,764,14000000,12,123)B:",800,800);
		System.setProperty("sun.java2d.opengl", "true");
		GravityGenerator();
//		Vector v = generateVector("10t+10:&10t^2+10t+100:");
//		System.out.println(gameLevel.physicsListLevel[0].objectPosistion.getAt(0).toString());
//		String inputEquation;
//		Scanner posistionInput = new Scanner(System.in);
//		inputEquation=posistionInput.nextLine();
//		GameMain.gameLevel.physicsListLevel[0].objectPosistion=GameMain.generateVector(inputEquation);
//		timer = new Timer();
		new MyFrame();
		
		System.out.println(gameLevel.coordinateLevelPlane[32][20].occupyingPhysicsObject);
//		System.out.println(generateAlgebraicEquation(posistionY).evaluateAbsValue(2));
//		
//		
////		 where the game happens
//		do
//		{
//			time+=.033;
//		}while(PiggiesInLevel());
	}
	
	
}

