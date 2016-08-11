package cgol;

import java.awt.Color;
import java.awt.Graphics;
public class Cell{

	public boolean alive; //whether or not this tile contains a mine
	public int liveNeighbors; //keeps track of how many neighbors are alive
	public int x,y,width,height; //determined by board dimensions divided by # of tiles
	public Cell right,left,up,down,tright,tleft,dright,dleft; //pointers to neighbors; will be used to increment bombsNearBy
	
	public Cell(int startX, int startY, int startWidth, int startHeight) //constructor for each tile
	{
		x = startX;
		y = startY;
		width = startWidth;
		height = startHeight;
		alive=false;
		right=left=up=down=tright=tleft=dright=dleft=null; //neighbor pointers are set by method in Board.java
		liveNeighbors=0;
	}
	/**
	 * Gets called when a Cell is clicked, to see which tile has been clicked
	 */
	public boolean contains(int checkX, int checkY){
		return (checkX > x) && (checkY > y) && (checkX < x + width) && (checkY < y + height);
	}
	/**
	 * increments number of neighbors that are alive
	 */
	public void display(Graphics g) //used to actually print out the Cell
	{
		if(!this.alive){//dead
			g.setColor(Color.BLACK); 
			g.fillRect(x, y, width, height); //background is black
			g.setColor(Color.GRAY.darker());
			g.drawRect(x, y, width, height);
		}
		else{//alive
			g.setColor(Color.WHITE); 
			g.fillRect(x, y, width, height); 
			g.setColor(Color.GRAY.darker());
			g.drawRect(x, y, width, height);
		}
	}
}
