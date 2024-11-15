package peevedFowls;

import java.util.ArrayList;

public class Level 
{
	PhysicsObject[] physicsListLevel;
	public static int levelSizeX;
	public static int levelSizeY;
	PhysicsObject[][] physicsLevelPlane;
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
	
	public Level(int sizeX,int sizeY,ArrayList<Fowl> birdList,ArrayList<Block> blockList)
	{
		levelsBirdList = birdList;
		levelsBlockList = blockList;
		makePhysicsListLevel();
		levelSizeX=sizeX;
		levelSizeY=sizeY;
		physicsLevelPlane = new PhysicsObject[sizeY][sizeX];
	}
	
}
