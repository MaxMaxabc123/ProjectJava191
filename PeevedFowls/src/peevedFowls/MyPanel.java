package peevedFowls;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class MyPanel extends JPanel implements ActionListener
{
	final int PANEL_WIDTH = 800;
	final int PANEL_HEIGHT = 800;
	Hashtable<PhysicsObject, Image> hashtable = new Hashtable<>();
	Image pig;
	Image bird;
	Image block;
	Timer timer;
	int xVelocity=1;
	int yVelocity=1;
	int NEW_WIDTH = 10;
	int NEW_HEIGHT = 10; 
	int x = 0;
	int y = 0;
	double timeDiff=0;
	MyPanel()
	{
		try
		{
			this.setBackground(Color.black);
			this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
			BufferedImage pigImg = ImageIO.read(new File("useThisPig.png"));
			BufferedImage birdImg = ImageIO.read(new File("useThisRed.png"));
			BufferedImage blockImg = ImageIO.read(new File("useThisBlock.png"));
			for(int i = 0; i < GameMain.gameLevel.physicsListLevel.length;i++)
			{
				if(GameMain.gameLevel.physicsListLevel[i] instanceof Pig)
				{
					pig = pigImg.getScaledInstance(GameMain.gameLevel.physicsListLevel[i].objectSize, GameMain.gameLevel.physicsListLevel[i].objectSize,BufferedImage.SCALE_SMOOTH);
					hashtable.put(GameMain.gameLevel.physicsListLevel[i],pig);
				}
				if(GameMain.gameLevel.physicsListLevel[i] instanceof Block)
				{
					block = blockImg.getScaledInstance(GameMain.gameLevel.physicsListLevel[i].objectSize, GameMain.gameLevel.physicsListLevel[i].objectSize,BufferedImage.SCALE_SMOOTH);
					hashtable.put(GameMain.gameLevel.physicsListLevel[i],block);
				}
				if(GameMain.gameLevel.physicsListLevel[i] instanceof Fowl)
				{
					bird = birdImg.getScaledInstance(GameMain.gameLevel.physicsListLevel[i].objectSize, GameMain.gameLevel.physicsListLevel[i].objectSize,BufferedImage.SCALE_SMOOTH);
					hashtable.put(GameMain.gameLevel.physicsListLevel[i],bird);
				}
			}
			timer = new Timer(33,this);
			Toolkit.getDefaultToolkit().sync();
			timer.start();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2D=   (Graphics2D) g;
		Enumeration<PhysicsObject> physicsEnum = hashtable.keys();
		Enumeration<Image> imageEnum = hashtable.elements();
		
		for(int i = 0; i < GameMain.gameLevel.physicsListLevel.length;i++)
		{
			Coordinate currentCoord = physicsEnum.nextElement().getCoordinate();
			x=currentCoord.getXCoord();
			y=currentCoord.getYCoord();
			g2D.drawImage((Image)imageEnum.nextElement(),x,y,null);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		for(int i = 0;i<GameMain.gameLevel.physicsListLevel.length;i++)
		{
			System.out.println(GameMain.gameLevel.physicsListLevel[i].getCoordinate().getXCoord()+" * "+GameMain.gameLevel.physicsListLevel[i].getCoordinate().getYCoord());
		}
		repaint();
		
	}
}
