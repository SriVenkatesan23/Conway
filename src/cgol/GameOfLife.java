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
	static int size = 15;
	static Board gameBoard = new Board(size); //creating gameBoard 
	static volatile boolean runningSim = false; //will be used to determine if simulation is running,
	//volatile means the value will be fetched from memory every time

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
	public void simulate(){
		Board future = new Board(size);
		boolean changed=false;
		for(int r=0;r<size;r++){
			for(int c=0;c<size;c++){
				
				/**
				 * Rules:
				 * 
				 * 1) Any living cell with <2 neighbors dies, as if from underpopulation
				 * 2) Any living cell with exactly 2 or 3 neighbors lives
				 * 3) Any living cell with 4 or more neighbors dies, as if from overpopulation
				 * 4) Any dead cell with exactly 3 neighbors becomes alive, as if from reproduction
				 * 
				 */
				
				if(gameBoard.board[r][c].alive){
					if(gameBoard.board[r][c].liveNeighbors<2) {
						future.board[r][c].alive=false;
						changed=true;
					}
					else if(gameBoard.board[r][c].liveNeighbors<4) future.board[r][c].alive=true;
					
					else if(gameBoard.board[r][c].liveNeighbors>3){
						future.board[r][c].alive=false;
						changed=true;
					}
				}
				else{ //dead
					if(gameBoard.board[r][c].liveNeighbors==3){
						future.board[r][c].alive=true;
						changed = true;
					}
				}	
				
				
				
			}	
		}

		if(changed){
			gameBoard.board=future.board;
			repaint();
		}
	}
	/**
	 * Initializes window in which the sim will be played
	 */
	public GameOfLife() 
	{
		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * Displays gameBoard
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
			}
			else{
				runningSim=true;
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
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
	}




}
