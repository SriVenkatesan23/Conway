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
	static Board gameBoard = new Board(50); //creating gameBoard
	static boolean runningSim = false; //will be used to determine when to stop

	public static void main(String[] args){
		GameOfLife m = new GameOfLife();
		m.getContentPane().setBackground(Color.darkGray);
		m.setSize(700,700);
		m.setVisible(true);
		while(runningSim){
			m.simulate();
		}
	}
	public void simulate(){
		gameBoard.setAllNeighborCounts();
		repaint();	
	}
	/**
	 * Initializes window in which the game will be played
	 */
	public GameOfLife() 
	{
		addMouseListener(this);
		setFocusable(true);
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



	public void mouseClicked(MouseEvent e) {
		gameBoard=gameBoard.updateBoard(e.getX(), e.getY());
		repaint();
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
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
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
	
	


}
