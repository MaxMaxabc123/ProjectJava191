package peevedFowls;

import java.util.ArrayList;

public class Level 
{
	PhysicsObject[] physicsListLevel;
	public static int levelSizeX;
	public static int levelSizeY;
	Coordinate[][] coordinateLevelPlane;
	ArrayList<Fowl> levelsBirdList;
	ArrayList<Block> levelsBlockList;
	public void makePhysicsListLevel()
	{
		int i;
		physicsListLevel = new PhysicsObject[levelsBirdList.size()+levelsBlockList.size()];
		for(i = 0; i < levelsBirdList.size();i++)
		{
			physicsListLevel[i] = levelsBirdList.get(i);
		}
		for (int j = 0; j < levelsBlockList.size();j++)
		{
			physicsListLevel[j+i]=levelsBlockList.get(j);
		}
	}
	public void makeCoordinateLevelPlane()
	{
		coordinateLevelPlane= new Coordinate[levelSizeX][levelSizeY];
		for(int i = 0; i < levelSizeY;i++)
		{
			for(int j = 0; j < levelSizeX;j++)
			{
				coordinateLevelPlane[i][j] = new Coordinate(j,i);
			}
		}
	}
	
	public Level(int sizeX,int sizeY,ArrayList<Fowl> birdList,ArrayList<Block> blockList)
	{
		levelsBirdList = birdList;
		levelsBlockList = blockList;
		makePhysicsListLevel();
		levelSizeX=sizeX;
		levelSizeY=sizeY;
		makeCoordinateLevelPlane();
		
	}
	
}
