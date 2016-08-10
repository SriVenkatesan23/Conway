package cgol;

import java.awt.Graphics;
import java.util.*;

public class Board {

	Cell[][] board;

	final int x = 59;  //board position should never change
	final int y = 110;
	final int width = 560;
	final int height = 560; 
	int hw; //number of tiles on the board

	/**
	 * 
	 * @param x = x position of board
	 * @param y = y position of board
	 * @param width 
	 * @param height
	 * @param size = the height/width of the board i.e. size of 12 leads to 12x12 sim
	 * initializes tile pointers, adds bombs to the board, and increments the tiles' 
	 * 
	 */

	public Board(int size){
		hw=size;
		this.board = new Cell[hw][hw];
		this.board = setCellNeighbors(); //sets each tile's right up down etc neighbor
	}

	/**
	 * maybe make hash function for constant access? 
	 * 
	 * @param checkX - x coord of click
	 * @param checkY - y coord of click
	 * @return board after click
	 */
	public Board updateBoard(int checkX, int checkY){
		for(int r = 0; r < hw; r++){
			for(int c = 0; c < hw; c++){
				if(board[r][c].contains(checkX, checkY)){
					board[r][c].alive=!board[r][c].alive; //if alive, make dead; vice versa
					return this;
				}
			}
		}
		return this;
	}

	public void display(Graphics g) { //prints out the board so the players can see it
		for(int r = 0; r < hw; r++){
			for (int c = 0; c < hw; c++){
				board[r][c].display(g);
			}
		}
	}
	public Cell[][] setCellNeighbors(){
		for(int r = 0; r < hw; r++){
			for (int c = 0; c < hw; c++){
				board[r][c] = new Cell(x + c*width/hw, y + r*height/hw, width / hw, height / hw);
			}
		}
		for(int r = 0; r < hw; r++){
			for (int c = 0; c < hw; c++){
				if(c+1<hw){ //right is set if not the last column
					board[r][c].right=board[r][c+1];
				}
				if(c-1>-1){ //left
					board[r][c].left=board[r][c-1];
				}
				if(r-1>-1){ //up
					board[r][c].up=board[r-1][c];
				}
				if(r+1<hw){ //down
					board[r][c].down=board[r+1][c];
				}
				if(c+1<hw && r-1>-1){ //top-right
					board[r][c].tright=board[r-1][c+1];
				}
				if(c-1>-1 && r-1>-1 ){ //top-left 
					board[r][c].tleft=board[r-1][c-1];
				}
				if(c+1<hw && r+1<hw ){ //down-right 
					board[r][c].dright=board[r+1][c+1];
				}
				if(c-1>-1 && r+1<hw  ){ //down-left 
					board[r][c].dleft=board[r+1][c-1];
				}
			}
		}
		return board;
	}
	public Cell[][] setAllNeighborCounts(){

		for(int r=0;r<hw;r++){
			for(int c=0;c<hw;c++){
				this.board[r][c].liveNeighbors();
				this.board[r][c].setStatus();
			}
		}
		return this.board;	
	}
	


}
