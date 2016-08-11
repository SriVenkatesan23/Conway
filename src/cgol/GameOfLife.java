package cgol;

import java.awt.Color;
import javax.swing.*;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameOfLife extends JFrame implements MouseListener,KeyListener{

	private static final long serialVersionUID = 1L;
	static int size = 25;
	static Board gameBoard = new Board(size); //creating gameBoard  ************MAKE VOLATILE????**********
	static volatile boolean runningSim = false; //will be used to determine if simulation is running,
	//volatile means the value will be same across all threads
	
	public static void main(String[] args){
		GameOfLife m = new GameOfLife();
		m.getContentPane().setBackground(Color.darkGray);
		m.setSize(700,700);
		m.setVisible(true);
		
		while(true){ 
			while(runningSim){ 
				try{
					m.simulate();
					gameBoard.board = gameBoard.setAllNeighborCounts();
					Thread.sleep(300);
				}catch (InterruptedException e){
					Thread.currentThread().interrupt();
				}
			}

		}
	}
	/**
	 * Use current gen array and game rules to make future array
	 * set current gen array to future array
	 * repaint
	 */
	public void simulate(){ //not being accessed
		Board future = new Board(size);
		for(int r=0;r<size;r++){
			for(int c=0;c<size;c++){
				if(gameBoard.board[r][c].alive){
					if(gameBoard.board[r][c].liveNeighbors<2) future.board[r][c].alive=false;
					else if(gameBoard.board[r][c].liveNeighbors==2 || gameBoard.board[r][c].liveNeighbors==3) future.board[r][c].alive=true;
					else if(gameBoard.board[r][c].liveNeighbors>3) future.board[r][c].alive=false;
				}
				else{ //dead
					if(gameBoard.board[r][c].liveNeighbors==3) future.board[r][c].alive=true;
				}	
			}	
		}
		gameBoard.board=future.board;
		repaint();
	}
	/**
	 * Initializes window in which the game will be played
	 */
	public GameOfLife() 
	{
		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * Displays gameBoard, as well as the MINESWEEPER title at beginning
	 * and information pertinent to the player
	 * Prints win/lose statements
	 */
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setFont(new Font("Chiller", Font.PLAIN, 45));
		gameBoard.display(g);
		g.setColor(Color.GRAY.brighter());
		g.drawString("CONWAY'S GAME OF LIFE", 55, 90);
	}

	public void keyPressed(KeyEvent e ){ //press space to start/stop simulation

		int code = e.getKeyCode(); 
		if (code == KeyEvent.VK_SPACE){
			if(runningSim){
				runningSim=false;
				System.out.println(runningSim); //debug code: value IS being set for runningSim
			}
			else{
				runningSim=true;
				System.out.println(runningSim);
			}
		}
	}
	public void mouseClicked(MouseEvent e) {
		if(!runningSim){ //can only click cells when not running sim
			gameBoard=gameBoard.updateBoard(e.getX(), e.getY());
			repaint();
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyTyped(KeyEvent e) {

	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}




}
